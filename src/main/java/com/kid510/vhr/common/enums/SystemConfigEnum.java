package com.kid510.vhr.common.enums;

/**
 * @Classname SystemConfig
 * @Description 系统枚举
 * @Date 2020/1/1 21:19
 * @Author kid
 */
public enum SystemConfigEnum {

    AllMenusWithRole("AllMenusWithRole","菜单列表以及所需的角色");

    private String name;
    private String desc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    SystemConfigEnum(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    SystemConfigEnum() {
    }
}
