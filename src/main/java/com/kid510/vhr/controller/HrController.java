package com.kid510.vhr.controller;

import com.kid510.vhr.model.Hr;
import com.kid510.vhr.service.HrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Classname HrController
 * @Description TODO
 * @Date 2019/12/25 16:11
 * @Author kid
 */
@RestController
public class HrController {
    @Autowired
    private HrService hrService;

    @GetMapping("/findAllHr")
    public List<Hr> findAllHr() {
        return hrService.findAllHr();
    }
}
