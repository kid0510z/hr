package com.kid510.vhr.mapper;

import com.kid510.vhr.pojo.Department;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DepartmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);

    List<Department> getAllDepartment(Integer pid);

    @Select("select * from department")
    List<Department> getAllDept();
}