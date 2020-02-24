package com.yc.C71S3Tzggmall.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
 
@Configuration
public class LoginConfigurer implements WebMvcConfigurer {
 
	@Bean
    public SecurityInterceptor getSecurityInterceptor(){
        return  new SecurityInterceptor();
    }

    @Override
    public void addViewControllers( ViewControllerRegistry registry ) {
        //默认到登陆页
        registry.addViewController("/").setViewName("forward:/back/login");
    }
 
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        InterceptorRegistration addInterceptor = registry.addInterceptor(getSecurityInterceptor());
        //排除配置
        addInterceptor.excludePathPatterns("/back/login");
        addInterceptor.excludePathPatterns("/back/login.do");
        addInterceptor.excludePathPatterns("/back/assets/**");
 
        //拦截配置
        addInterceptor.addPathPatterns("/back/**");
    }
 
    private class SecurityInterceptor extends HandlerInterceptorAdapter {
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException, ServletException {
            HttpSession session = request.getSession();
            //判断是否已有该用户登录的session
            if(session.getAttribute("admin") !=null){
                return true;
            }
            //跳转到登录页
            String url = "/back/login";
            response.sendRedirect(url);
            return false;
        }
    }
}
