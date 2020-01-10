package com.kid510.vhr.controller.system.basic;

import com.kid510.vhr.common.resp.ResultResp;
import com.kid510.vhr.pojo.Department;
import com.kid510.vhr.service.system.basic.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Classname DepartmentController
 * @Description 部门
 * @Date 2020/1/10 10:47
 * @Author kid
 */
@RestController
@RequestMapping("/system/basic/dept")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/")
    public ResultResp getAllDepartment() {
        List<Department> departments =  departmentService.getAllDepartment();
        return ResultResp.ok("ok",departments);
    }

}