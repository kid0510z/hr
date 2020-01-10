package com.kid510.vhr.service.system.basic;

import com.kid510.vhr.common.enums.SystemConfigEnum;
import com.kid510.vhr.config.CustomException;
import com.kid510.vhr.mapper.RoleMapper;
import com.kid510.vhr.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

/**
 * @Classname AuthorityService
 * @Description 权限service
 * @Date 2020/1/9 11:43
 * @Author kid
 */
@Service
public class AuthorityService {

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private RoleMapper roleMapper;

    public List<Role> getAllRoles() {
        return roleMapper.getAllRoles();
    }

    @Transactional
    public void updateRole(Integer rid, Integer[] mids) {
        //先删除
        roleMapper.deleteAuthByRid(rid);
        // 再新增
        int result = roleMapper.insertAuth(rid, mids);
        if (mids.length != result) {
            // 手动强制回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new CustomException("修改角色权限失败！");
        }

        // 清除reids
        redisTemplate.delete(SystemConfigEnum.AllMenusWithRole.getName());
    }

    public void addRole(Role role) {
        // 添加 前缀
        role.setName("ROLE_" + role.getName());
        int result = roleMapper.insertSelective(role);
        if (result != 1) {
            throw new CustomException("新增角色失败！");
        }
    }

    @Transactional
    public void delRole(Integer rid) {
        //删除 角色权限中间表
        roleMapper.deleteAuthByRid(rid);
        //角色表
        roleMapper.deleteByPrimaryKey(rid);
        // 清除redis 菜单所需角色
        redisTemplate.delete(SystemConfigEnum.AllMenusWithRole.getName());
    }
}