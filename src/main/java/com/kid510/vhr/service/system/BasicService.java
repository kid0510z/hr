package com.kid510.vhr.service.system;

import com.kid510.vhr.common.resp.ResultResp;
import com.kid510.vhr.mapper.PositionMapper;
import com.kid510.vhr.pojo.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Classname BasicService
 * @Description 基础信息设置
 * @Date 2020/1/6 10:18
 * @Author kid
 */
@Service
public class BasicService {
    @Autowired
    private PositionMapper positionMapper;

    /**
     * 得到职位信息所有列表
     *
     * @return
     */
    public List<Position> getAllPos() {
        return positionMapper.getAllPos();
    }

    /**
     * 增加职位--> 校验职位是否存在
     *
     * @param position
     * @return
     */
    public ResultResp addPos(Position position) {
        String name = position.getName();
        List<Position> allPos = positionMapper.getAllPos();
        AtomicBoolean flag = new AtomicBoolean(true);
        allPos.forEach(pos -> {
            if (pos.getName().equals(name)) {
                flag.set(false);
            }
        });
        // 职位名称不重复
        if (flag.get()) {
            position.setCreateDate(new Date());
            position.setEnabled(true);
            int result = positionMapper.insertSelective(position);
            if (result == 1) {
                return ResultResp.ok("添加成功！");
            } else {
                return ResultResp.error("添加失败！");
            }
        } else {
            return ResultResp.error("不能重复添加职位！");
        }
    }

    /**
     * 根据id删除职位
     *
     * @param id
     * @return
     */
    public ResultResp delPos(Integer id) {
        int result = positionMapper.deleteByPrimaryKey(id);
        if (result == 1) {
            return ResultResp.ok("删除成功！");
        } else {
            return ResultResp.error("删除失败！");
        }
    }

    /**
     * 根据ids批量删除职位
     *
     * @param id
     * @return
     */
    public ResultResp delPosMany(Integer[] ids) {
        int result = positionMapper.deleteByPrimaryKeyMany(ids);
        if (result == ids.length) {
            return ResultResp.ok("批量删除成功！");
        } else {
            return ResultResp.error("批量删除失败！");
        }
    }

    /**
     * 修改职位
     *
     * @param id
     * @param position
     * @return
     */
    public ResultResp updatePos(Integer id, Position position) {
        if (!position.getId().equals(id)) {
            return ResultResp.error("请求参数有误！");
        }
        int result = positionMapper.updateByPrimaryKeySelective(position);
        if (result == 1) {
            return ResultResp.ok("修改成功！");
        } else {
            return ResultResp.error("修改失败！");
        }

    }
}
