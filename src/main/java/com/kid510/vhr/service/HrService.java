package com.kid510.vhr.service;

import com.kid510.vhr.mapper.HrMapper;
import com.kid510.vhr.mapper.RoleMapper;
import com.kid510.vhr.pojo.Hr;
import com.kid510.vhr.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Classname HrService
 * @Description hrService
 * @Date 2019/12/25 16:11
 * @Author kid
 */
@Service
public class HrService implements UserDetailsService {
    @Autowired
    private HrMapper hrMapper;

    @Autowired
    private RoleMapper roleMapper;

    /**
     * springSecurity调用该方法校验用户信息
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 根据用户名称找到该对象
        Hr hr = hrMapper.loadUserByUsername(username);
        if (hr == null) {
            throw new UsernameNotFoundException("用户未找到!");
        }
        // 根据id获取权限
        List<Role> roles = roleMapper.loadRolesByHrId(hr.getId());
        hr.setRoles(roles);
        return hr;
    }
}
