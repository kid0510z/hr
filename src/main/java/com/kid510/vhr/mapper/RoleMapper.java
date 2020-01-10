package com.kid510.vhr.mapper;

import com.kid510.vhr.pojo.Role;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> loadRolesByHrId(@Param("hrid") Integer hrid);

    List<Role> getAllRoles();

    @Delete("DELETE FROM menu_role WHERE rid=#{rid}")
    void deleteAuthByRid(@Param("rid") Integer rid);

    int insertAuth(@Param("rid") Integer rid,@Param("mids") Integer[] mids);

}