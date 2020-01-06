package com.kid510.vhr.pojo;

import java.util.Date;

public class JObLevel {
    private Integer id;

    private String name;

    private String titlelevel;

    private Date createDate;

    private Boolean enabled;

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

    public String getTitlelevel() {
        return titlelevel;
    }

    public void setTitlelevel(String titlelevel) {
        this.titlelevel = titlelevel == null ? null : titlelevel.trim();
    }

    public Date getCreatedate() {
        return createDate;
    }

    public void setCreatedate(Date createDate) {
        this.createDate = createDate;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}