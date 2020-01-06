package com.kid510.vhr.pojo;

import java.util.Date;

public class MsgContent {
    private Integer id;

    private String title;

    private String message;

    private Date createDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    public Date getCreatedate() {
        return createDate;
    }

    public void setCreatedate(Date createDate) {
        this.createDate = createDate;
    }
}