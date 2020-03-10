package com.studyinghome.config;

import com.studyinghome.framework.result.CodeMsg;
import com.studyinghome.framework.result.Result;
import com.studyinghome.utils.JsonUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * AuthenticationAccessDeniedHandler
 *
 * @author Leslie
 * @email panxiang_work@163.com
 * @create 2019/4/29 11:50
 */
@Component
public class AuthenticationAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse resp,
                       AccessDeniedException e) throws IOException {
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        Result error = Result.error(CodeMsg.SECURITY_ERROR);
        out.write(JsonUtil.obj2String(error));
        out.flush();
        out.close();
    }
}
