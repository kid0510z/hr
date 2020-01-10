package com.kid510.vhr.service.system.basic;

import com.kid510.vhr.common.enums.SystemConfigEnum;
import com.kid510.vhr.common.resp.system.MenuResp;
import com.kid510.vhr.mapper.MenuMapper;
import com.kid510.vhr.pojo.Menu;
import com.kid510.vhr.utils.FastJsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname MenuService
 * @Description 菜单service
 * @Date 2020/1/9 15:00
 * @Author kid
 */
@Service
@Slf4j
public class MenuService {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private MenuMapper menuMapper;


    /**
     * 得到所有菜单 以及 该角色所对应的菜单
     *
     * @param rid
     * @return
     */
    public MenuResp getMenusAndSelectd(Integer rid) {
        MenuResp menuResp = new MenuResp();
        List<Menu> allMenus = new ArrayList<>();
        // 所有菜单
        BoundValueOperations<String, String> valueOps = redisTemplate.boundValueOps(SystemConfigEnum.AllMenusWithChildren.getName());
        String redisData = valueOps.get();
        if (redisData == null) {
            allMenus = menuMapper.getAllMenus();
            log.info("---------所有菜单从数据库加载---------");
            // 存入redis
            valueOps.set(FastJsonUtils.convertObjectToJson(allMenus));
        } else {
            log.info("---------所有菜单从Redis加载---------");
            allMenus = FastJsonUtils.toList(redisData, Menu.class);
        }
        menuResp.setMenus(allMenus);
        // 已选中的菜单
        String mids = menuMapper.getSelectdMenus(rid);
        menuResp.setMids(mids);
        return menuResp;
    }


}