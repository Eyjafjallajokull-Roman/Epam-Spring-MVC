package epam.com.springBoot.api.config;

import epam.com.springBoot.convertor.fromDTO.ActivityConvertor;
import epam.com.springBoot.convertor.fromDTO.UserActivityConvertor;
import epam.com.springBoot.convertor.fromDTO.UserConvertor;
import epam.com.springBoot.convertor.toDTO.ActivityDTOConvertor;
import epam.com.springBoot.convertor.toDTO.UserActivityDTOConvertor;
import epam.com.springBoot.convertor.toDTO.UserDTOConvertor;
import epam.com.springBoot.interceptor.AdminInterceptor;
import epam.com.springBoot.interceptor.UserInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new ActivityConvertor());
        registry.addConverter(new UserConvertor());
        registry.addConverter(new UserDTOConvertor());
        registry.addConverter(new ActivityDTOConvertor());
        registry.addConverter(new UserActivityConvertor());
        registry.addConverter(new UserActivityDTOConvertor());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserInterceptor())
                .addPathPatterns("/api/v1/users/*");
        registry.addInterceptor(new UserInterceptor()).addPathPatterns("/api/v1/activities/*");
        registry.addInterceptor(new AdminInterceptor()).addPathPatterns("/api/v1/admin/*");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }


}
