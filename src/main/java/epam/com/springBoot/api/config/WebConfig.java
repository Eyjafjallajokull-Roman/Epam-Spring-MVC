package epam.com.springBoot.api.config;

import epam.com.springBoot.convertor.ActivityConvertor;
import epam.com.springBoot.convertor.ActivityDTOConvertor;
import epam.com.springBoot.convertor.UserConvertor;
import epam.com.springBoot.convertor.UserDTOConvertor;
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
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserInterceptor())
                .addPathPatterns("/users/*");
        registry.addInterceptor(new UserInterceptor()).addPathPatterns("/activities/*");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }


}