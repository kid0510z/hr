package com.kid510.vhr.controller.config;

import com.kid510.vhr.common.resp.ResultResp;
import com.kid510.vhr.service.config.SysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname SysConfigController
 * @Description 系统配置controller
 * @Date 2019/12/29 15:33
 * @Author kid
 */
@RestController
@RequestMapping("/sys/config")
public class SysConfigController {

    @Autowired
    private SysConfigService sysConfigService;

    @GetMapping("/getMenus")
    public ResultResp getMenusByHrId() {
        return sysConfigService.getMenusByHrId();
    }

}
