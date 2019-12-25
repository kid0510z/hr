package com.kid510.vhr.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kid510.vhr.common.resp.ResultResp;
import com.kid510.vhr.pojo.Hr;
import com.kid510.vhr.service.HrService;
import jdk.nashorn.internal.runtime.ECMAErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Classname SecurityConfig
 * @Description Spring Security配置类
 * @Date 2019/12/25 17:02
 * @Author kid
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private HrService hrService;

    /**
     * 设置用户身份校验的service
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(hrService);
    }


    /**
     * 配置 基本设置
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/doLogin")
                .loginPage("/login")
                .successHandler((request, response, authentication) -> {
                    response.setContentType("applicaton/json;charset-utf-8");
                    PrintWriter out = response.getWriter();
                    Hr hr = (Hr) authentication;
                    ResultResp ok = ResultResp.ok("登录成功!", hr);
                    // 将登录成功的结果相应前端
                    out.write(new ObjectMapper().writeValueAsString(ok));
                    out.flush();
                    out.close();

                })
                .failureHandler((request, response, exception) -> {
                    response.setContentType("applicaton/json;charset-utf-8");
                    PrintWriter out = response.getWriter();
                    ResultResp error = ResultResp.error("登录失败!");
                    if (exception instanceof LockedException) {
                        error.setMessage("账号已锁定，请联系管理员!");
                    } else if (exception instanceof DisabledException) {
                        error.setMessage("账号已禁用，请联系管理员!");
                    } else if (exception instanceof CredentialsExpiredException) {
                        error.setMessage("凭证已过期，请重新登录!");
                    } else if (exception instanceof AccountExpiredException) {
                        error.setMessage("账号已过期，请联系管理员!");
                    } else if (exception instanceof BadCredentialsException) {
                        error.setMessage("账号密码输入有误，请重新输入!");
                    }
                    // 将登录成功的结果相应前端
                    out.write(new ObjectMapper().writeValueAsString(error));
                    out.flush();
                    out.close();
                })
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler((request, response, authentication) -> {

                })
                .permitAll()
                .and()
                .csrf().disable();
    }

    /**
     * 加密方式
     *
     * @return
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
