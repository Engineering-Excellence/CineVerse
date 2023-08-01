package kr.co.dbcs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"kr.co.dbcs.controller", "kr.co.dbcs.exception"})
public class ServletConfig implements WebMvcConfigurer {
    // dispatcher-servlet.xml을 대신하는 java class

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        // 정적 요소 매핑
        registry.addResourceHandler("/static/**").addResourceLocations("/WEB-INF/static/");
        registry.addResourceHandler("/images/**").addResourceLocations("/WEB-INF/static/images/");
        registry.addResourceHandler("/css/**").addResourceLocations("/WEB-INF/static/css/");
        registry.addResourceHandler("/js/**").addResourceLocations("/WEB-INF/static/js/");
    }

    @Bean
    public InternalResourceViewResolver viewResolver() {

        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");

        return resolver;
    }
}
