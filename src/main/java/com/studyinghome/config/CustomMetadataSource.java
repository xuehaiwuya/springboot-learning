package com.studyinghome.config;

import com.studyinghome.common.Const;
import com.studyinghome.model.Menu;
import com.studyinghome.model.Role;
import com.studyinghome.service.MenuService;
import com.studyinghome.utils.JsonUtil;
import com.studyinghome.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;

/**
 * 该类的主要功能就是通过当前的请求地址，获取该地址需要的用户角色
 *
 * @author Leslie
 * @email panxiang_work@163.com
 * @create 2019/4/29 13:24
 */
@Component
public class CustomMetadataSource implements FilterInvocationSecurityMetadataSource {
    @Autowired
    MenuService menuService;
    @Autowired
    RedisUtil redisUtil;
    AntPathMatcher antPathMatcher = new AntPathMatcher();

    /**
     * 1.一开始注入了MenuService，MenuService的作用是用来查询数据库中url pattern和role的对应关系，
     * 查询结果是一个List集合，集合中是Menu类，Menu类有两个核心属性，
     * 一个是url pattern，即匹配规则(比如/admin/**)，还有一个是List<Role>,即这种规则的路径需要哪些角色才能访问。
     * <p>
     * 2.我们可以从getAttributes(Object o)方法的参数o中提取出当前的请求url，
     * 然后将这个请求url和数据库中查询出来的所有url pattern一一对照，看符合哪一个url pattern，
     * 然后就获取到该url pattern所对应的角色，当然这个角色可能有多个，所以遍历角色，
     * 最后利用SecurityConfig.createList方法来创建一个角色集合。
     * <p>
     * 3.第二步的操作中，涉及到一个优先级问题，比如我的地址是/employee/basic/hello,
     * 这个地址既能被/employee/**匹配，也能被/employee/basic/**匹配，
     * 这就要求我们从数据库查询的时候对数据进行排序，将/employee/basic/**类型的url pattern放在集合的前面去比较。
     * <p>
     * 4.如果getAttributes(Object o)方法返回null的话，意味着当前这个请求不需要任何角色就能访问，甚至不需要登录。
     * 但是在我的整个业务中，并不存在这样的请求，我这里的要求是，所有未匹配到的路径，都是认证(登录)后可访问，
     * 因此我在这里返回一个ROLE_LOGIN的角色，这种角色在我的角色数据库中并不存在，
     * 因此我将在下一步的角色比对过程中特殊处理这种角色。
     *
     * @param o
     * @return
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) {
        //获取请求地址
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        //获取所有权限信息以及其对应的角色,根据需求可以将查询出的信息存进redis中
        List<Menu> allMenu = JsonUtil.string2Obj(redisUtil.get(Const.ALL_MENU), List.class, Menu.class);
        if (CollectionUtils.isEmpty(allMenu)) {
            allMenu = menuService.getAllMenu();
            redisUtil.setEx(Const.ALL_MENU, JsonUtil.obj2String(allMenu), Const.DEFAULT_REDIS_SESSION_TIMEOUT);
        }
        for (Menu menu : allMenu) {
            //如果不需要访问权限则默认登录访问
            if (menu.getRequireAuth() == 0) {
                return SecurityConfig.createList(Const.ROLE_LOGIN);
            }
            if (antPathMatcher.match(menu.getUrl(), requestUrl) && menu.getRoles().size() > 0) {
                List<Role> roles = menu.getRoles();
                int size = roles.size();
                String[] values = new String[size];
                for (int i = 0; i < size; i++) {
                    values[i] = roles.get(i).getName();
                }
                //返回网络请求对应的角色信息
                return SecurityConfig.createList(values);
            }
        }
        //没有匹配上的资源，都是登录访问
        return SecurityConfig.createList(Const.ROLE_LOGIN);
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}
