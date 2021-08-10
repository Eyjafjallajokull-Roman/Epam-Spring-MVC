package epam.hw2.config;

import epam.hw2.beans.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:app.properties")
public class AppConfig2 {

    @Bean(initMethod = "nameMethod", destroyMethod = "valueMethod")
    public BeanB getBeanB() {

        return new BeanB();
    }

    @Bean(initMethod = "nameMethod", destroyMethod = "valueMethod")
    public BeanC getBeanC() {
        return new BeanC();
    }

    @Bean(initMethod = "nameMethod", destroyMethod = "valueMethod")
    public BeanD getBeanD() {
        return new BeanD();
    }

    @Bean
    public BeanA getBeanA() {
        return new BeanA();
    }

    @Bean
    public BeanE getBeanE() {
        return new BeanE();
    }

    @Bean
    public BeanF getBeanF() {
        return new BeanF();
    }

    @Bean
    public OtherBean getOtherBean() {
        return new OtherBean();
    }

    @Bean
    public OtherBeanProcessor getOtherBeanProcessor() {
        return new OtherBeanProcessor();
    }


}
