package com.kid510.vhr.service.system.basic;

import com.kid510.vhr.mapper.DepartmentMapper;
import com.kid510.vhr.pojo.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Classname DepartmentService
 * @Description 部门service
 * @Date 2020/1/10 10:49
 * @Author kid
 */
@Service
public class DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    /**
     * 递归查询所有部门 TODO 待优化
     *
     * @return
     */
    public List<Department> getAllDepartment() {
        return departmentMapper.getAllDepartment(-1);
    }
}