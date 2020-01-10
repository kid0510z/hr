package com.kid510.vhr.pojo;

import javax.validation.constraints.NotNull;

public class Role {
    private Integer id;

    @NotNull(message = "角色名称不能为空")
    private String name;

    @NotNull(message = "角色中文名称不能为空")
    private String nameZh;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getNameZh() {
        return nameZh;
    }

    public void setNameZh(String nameZh) {
        this.nameZh = nameZh;
    }
}