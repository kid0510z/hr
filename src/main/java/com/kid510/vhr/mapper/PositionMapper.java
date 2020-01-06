package com.kid510.vhr.mapper;

import com.kid510.vhr.pojo.Position;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PositionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Position record);

    int insertSelective(Position record);

    Position selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Position record);

    int updateByPrimaryKey(Position record);

    List<Position> getAllPos();

    int deleteByPrimaryKeyMany(@Param("ids") Integer[] ids);
}