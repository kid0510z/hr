package com.kid510.vhr.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @Classname CustomerDe
 * @Description 判断该url是否可以通过
 * @Date 2020/1/1 22:23
 * @Author kid
 */
@Component
@Slf4j
public class CustomerAccessDecisionManager implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        // 匿名用户访问 直接打回
        if (authentication instanceof AnonymousAuthenticationToken) {
            log.error("尚未登录！");
            throw new AccessDeniedException("尚未登录，请先登录再访问！");
        }
        // 遍历访问该接口所需要的角色
        for (ConfigAttribute configAttribute : collection) {
            String needRole = configAttribute.getAttribute();
            if (needRole.equals("ROLE_LOGIN")) {
                return;
            }
            // 当前登录人所拥有的角色
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority authority : authorities) {
                // 匹配上直接结束
                if (authority.getAuthority().equals(needRole)) {
                    return;
                }
            }
        }
        // 没有匹配
        log.error("访问该接口权限不足！");
        throw new AccessDeniedException("权限不足，请联系管理员！");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
