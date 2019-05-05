package com.ckgl.cg.config;

import com.ckgl.cg.security.CustomUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by DELL on 2019/4/28.
 */
@Configuration //必须加这个注解，用于生成一个配置类，
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true) //启用Security注解
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    UserDetailsService customUserService() { // 注册UserDetailsService 的bean
        return new CustomUserService();
    }

    /**
     * 添加我们自定义的user UserDetails
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        // 忽略URL
        web.ignoring().antMatchers("/**/*.js", "/lang/*.json", "/**/*.css", "/**/*.js", "/**/*.map", "/**/*.html",
                "/**/*.png");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserService()).passwordEncoder(new PasswordEncoder(){
            //使用MD5获取加密之后的密码
            @Override
            public String encode(CharSequence rawPassword) {
                return MD5Util.encode((String)rawPassword);
            }
            //验证密码
            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return MD5Util.encode(encodedPassword).equals(MD5Util.encode((String)rawPassword));
            }}); //user Details Service验证
    }



    /**
     * @param http
     * @throws Exception
     */

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(
                        HttpMethod.GET,
                        "/dist/**",
                        "/bower_components/**",
                        "/build/**",
                        "/plugins/**",
                        "/js/*"
                ).permitAll()
                .anyRequest().authenticated() //4  所有请求必须要登录后才能认证
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/mjfs",true)
                .failureUrl("/login?error")//登录失败访问的页面
                .permitAll() //5 定制登录页面行为登录页面可以任意访问
                .and()
                .logout()
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/login")
                .permitAll()//6 注销可以任意访问
                .and().formLogin();
        http.csrf().disable();


    }

}