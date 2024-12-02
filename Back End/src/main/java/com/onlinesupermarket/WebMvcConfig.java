package com.onlinesupermarket;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig  implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // /images/**是静态映射， file:/root/images/是文件在服务器的路径
        // 此时，其他程序可以通过http://localhost:8080/images访问到D:/images/下的资源
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:D:/images/");
    }
}

