package com.kid510.vhr.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname HelloController
 * @Description 测试专用
 * @Date 2020/1/2 12:35
 * @Author kid
 */
@RestController
public class HelloController {

    @GetMapping("/employee/basic/hello")
    public String test1() {
        return "/employee/basic/hello";
    }

    @GetMapping("/employee/advanced/hello")
    public String test2() {
        return "/employee/advanced/hello";
    }


}
