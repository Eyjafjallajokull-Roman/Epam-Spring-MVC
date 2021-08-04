package epam.com.springBoot.config;

import epam.com.springBoot.convertor.ActivityConvertor;
import epam.com.springBoot.convertor.UserConvertor;
import epam.com.springBoot.convertor.UserDTOConvertor;
import epam.com.springBoot.interceptor.UserInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new ActivityConvertor());
        registry.addConverter(new UserConvertor());
        registry.addConverter(new UserDTOConvertor());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserInterceptor())
                .addPathPatterns("/users/*");
    }
}
