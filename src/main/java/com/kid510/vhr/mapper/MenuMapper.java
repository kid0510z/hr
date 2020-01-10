package com.kid510.vhr.mapper;

import com.kid510.vhr.pojo.Menu;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    List<Menu> getMenusByHrId(@Param("hrId") Integer hrId);

    List<Menu> getAllMenusWithRole();

    List<Menu> getAllMenus();

    @Select("SELECT group_concat(mr.mid) FROM menu_role mr WHERE mr.rid=#{rid}")
    String getSelectdMenus(@Param("rid") Integer rid);
}