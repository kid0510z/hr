package com.kid510.vhr.config;

import com.kid510.vhr.pojo.Menu;
import com.kid510.vhr.pojo.Role;
import com.kid510.vhr.service.config.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * @Classname CustomerFilterInvocationSecurityMetadataSource
 * @Description 此类主要根据请求url分析出访问该资源需要的角色权限
 * @Date 2019/12/31 13:21
 * @Author kid
 */
@Component
public class CustomerFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private MenuService menuService;
    // 路径匹配
    AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        // 请求地址
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        // 所有菜单包含所需权限
        List<Menu> menus = menuService.getAllMenusWithRole();
        for (Menu menu : menus) {
            // 匹配成功，返回所需权限列表
            if (antPathMatcher.match(menu.getUrl(), requestUrl)) {
                List<Role> roles = menu.getRoles();
                String[] attributes = new String[roles.size()];
                for (int i = 0; i < roles.size(); i++) {
                    attributes[i] = roles.get(i).getName();
                }
                return SecurityConfig.createList(attributes);
            }
        }
        // 没有匹配成功，表明访问资源只需登录即可(使用权限【ROLE_LOGIN】作为标识)
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
