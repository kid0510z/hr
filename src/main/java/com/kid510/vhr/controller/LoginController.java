package com.kid510.vhr.controller;

import com.kid510.vhr.common.resp.ResultResp;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname LoginController
 * @Description 登录跳转
 * @Date 2019/12/25 21:56
 * @Author kid
 */
@RestController
public class LoginController {
    @GetMapping("/login")
    public ResultResp login() {
        return ResultResp.error("请先登录!");
    }
}
