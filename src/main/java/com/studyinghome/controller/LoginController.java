package com.studyinghome.controller;

import com.studyinghome.result.CodeMsg;
import com.studyinghome.result.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ${DESCRIPTION}
 *
 * @author Leslie
 * @email panxiang_work@163.com
 * @create 2019-05-01 21:45
 */
@RestController
public class LoginController {

    @RequestMapping(value = "/login_p")
    public Result login() {
        return Result.error(CodeMsg.ERROR);
    }
}
