package com.kid510.vhr.service;

import com.kid510.vhr.common.enums.SystemConfigEnum;
import com.kid510.vhr.mapper.MenuMapper;
import com.kid510.vhr.pojo.Menu;
import com.kid510.vhr.utils.FastJsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname MenuService
 * @Description 菜单相关操作
 * @Date 2019/12/31 15:19
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
     * 得到所有菜单用作权限校验
     * redis做缓存
     *
     * @return
     */
    public List<Menu> getAllMenusWithRole() {
        String redisData = redisTemplate.boundValueOps(SystemConfigEnum.AllMenusWithRole.getName()).get();
        List<Menu> menus = new ArrayList<>();
        // redis数据为空 数据库查询
        if (StringUtils.isEmpty(redisData)) {
            log.info("菜单对应权限----redis数据为空，数据库查询");
            menus = menuMapper.getAllMenusWithRole();
            String jsonStr = FastJsonUtils.convertObjectToJson(menus);
            redisTemplate.boundValueOps(SystemConfigEnum.AllMenusWithRole.getName()).set(jsonStr);
        } else {
            log.info("菜单对应权限----从redis读取");
            // 不为空，转换成list
            menus = FastJsonUtils.toList(redisData, Menu.class);
        }
        return menus;
    }

}
