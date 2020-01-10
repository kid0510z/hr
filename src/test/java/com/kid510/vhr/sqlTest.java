package com.kid510.vhr;

import com.kid510.vhr.mapper.DepartmentMapper;
import com.kid510.vhr.pojo.Department;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Classname sqlTest
 * @Description TODO
 * @Date 2020/1/10 17:11
 * @Author kid
 */
@SpringBootTest
public class sqlTest {
    @Autowired
    private DepartmentMapper departmentMapper;

    @Test
    public void dept() {
        int topDept = -1;
        List<Department> allDept = departmentMapper.getAllDept();
        Department department = allDept.stream().filter(item -> item.getParentId() == topDept).collect(Collectors.toList()).get(0);
        recursive(department, allDept);

    }

    private void recursive(Department department, List<Department> allDept) {
        // 当前id
        Integer id = department.getId();
        // 集合中 还有当前部门的子部门
        List<Department> collect = allDept.stream().filter(item -> item.getParentId().equals(id)).collect(Collectors.toList());
        // 不为null的话递归并且设置进去
        if (!CollectionUtils.isEmpty(collect)) {
            for (Department department1 : collect) {
                recursive(department1, allDept);
            }
            department.setChildren(collect);

        }
    }

    @Test
    public void dept2() {
        int topDept = -1;
        List<Department> allDept = departmentMapper.getAllDept();
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

        Department department = map.get(topDept).get(0);
        recursive2(department, map);
    }

    private void recursive2(Department department, Map<Integer, List<Department>> map) {
        // 当前id
        Integer id = department.getId();
        // 集合中 还有当前部门的子部门
        List<Department> collect = map.get(id);
        // 不为null的话递归并且设置进去
        if (!CollectionUtils.isEmpty(collect)) {
            for (Department department1 : collect) {
                recursive2(department1, map);
            }
            department.setChildren(collect);
        }
    }


    @Test
    public void dept3() {
        int topDept = -1;
        List<Department> allDept = departmentMapper.getAllDept();
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
        recursive3(departments, map);
    }

    private void recursive3(List<Department> departments, Map<Integer, List<Department>> map) {
        if (!CollectionUtils.isEmpty(departments)) {
            for (Department department : departments) {
                Integer id = department.getId();
                List<Department> depts = map.get(id);
                recursive3(depts, map);
                department.setChildren(depts);
            }
        }

    }
}