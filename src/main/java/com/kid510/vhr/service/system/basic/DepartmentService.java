package com.kid510.vhr.service.system.basic;

import com.kid510.vhr.mapper.DepartmentMapper;
import com.kid510.vhr.pojo.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * 递归查询所有部门
     *
     * @return
     */
    public List<Department> getAllDepartment() {
        int topDept = -1;
        List<Department> allDept = departmentMapper.getAllDept();
        // key是parentId，val是该parentId对应的子部门
        Map<Integer, List<Department>> map = new HashMap<>();
        for (Department department : allDept) {
            Integer parentId = department.getParentId();
            // 为空表示 该 parentId是第一次
            List<Department> list = null;
            if (!CollectionUtils.isEmpty(map.get(parentId))) {
                list = map.get(parentId);
            } else {
                list = new ArrayList<>();
            }
            list.add(department);
            map.put(parentId, list);
        }

        List<Department> departments = map.get(topDept);
        // do
        recursive(departments, map);
        return departments;
    }


    private void recursive(List<Department> departments, Map<Integer, List<Department>> map) {
        if (!CollectionUtils.isEmpty(departments)) {
            for (Department department : departments) {
                Integer id = department.getId();
                List<Department> depts = map.get(id);
                recursive(depts, map);
                department.setChildren(depts);
            }
        }

    }
}