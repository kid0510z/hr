package com.kid510.vhr.mapper;

import com.kid510.vhr.pojo.Hr;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HrMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Hr record);

    int insertSelective(Hr record);

    Hr selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Hr record);

    int updateByPrimaryKey(Hr record);

    List<Hr> selectAll();

    Hr loadUserByUsername(@Param("username") String username);

}