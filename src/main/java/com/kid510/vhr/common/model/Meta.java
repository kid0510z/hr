package com.kid510.vhr.common.model;

import java.io.Serializable;

/**
 * @Classname Meta
 * @Description 前端菜单类所需，单独拆开
 * @Date 2019/12/29 15:21
 * @Author kid
 */
public class Meta implements Serializable {


    private static final long serialVersionUID = 4567968688640289383L;
    private Boolean keepAlive;

    private Boolean requireAuth;

    public Boolean getKeepAlive() {
        return keepAlive;
    }

    public void setKeepAlive(Boolean keepAlive) {
        this.keepAlive = keepAlive;
    }

    public Boolean getRequireAuth() {
        return requireAuth;
    }

    public void setRequireAuth(Boolean requireAuth) {
        this.requireAuth = requireAuth;
    }
}

