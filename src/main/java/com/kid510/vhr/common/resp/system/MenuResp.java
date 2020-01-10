package com.kid510.vhr.common.resp.system;

import com.kid510.vhr.pojo.Menu;

import java.util.List;

/**
 * @Classname RoleResp
 * @Description 菜单项以及该角色所对应的可访问资源
 * @Date 2020/1/9 15:32
 * @Author kid
 */
public class MenuResp {
    private List<Menu> menus;
    private String mids;

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public String getMids() {
        return mids;
    }

    public void setMids(String mids) {
        this.mids = mids;
    }
}