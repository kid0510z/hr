package com.kid510.vhr.service;

import com.kid510.vhr.common.resp.ResultResp;
import com.kid510.vhr.mapper.MenuMapper;
import com.kid510.vhr.pojo.Hr;
import com.kid510.vhr.pojo.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Classname SysConfigService
 * @Description 系统配置service
 * @Date 2019/12/29 15:34
 * @Author kid
 */
@Service
public class SysConfigService {

    @Autowired
    private MenuMapper menuMapper;

    /**
     * 根据用户id得到菜单列表
     *
     * @return
     */
    public ResultResp getMenusByHrId() {
        List<Menu> menuList = menuMapper.getMenusByHrId(((Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());

        return ResultResp.ok("ok", menuList);
    }
}
