package com.kid510.vhr.service;

import com.kid510.vhr.mapper.HrMapper;
import com.kid510.vhr.model.Hr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname HrService
 * @Description TODO
 * @Date 2019/12/25 16:11
 * @Author kid
 */
@Service
public class HrService {
    @Autowired
    private HrMapper hrMapper;


    /**
     * 查找hr 测试
     *
     * @return
     */
    public List<Hr> findAllHr() {
        return hrMapper.selectAll();
    }
}
