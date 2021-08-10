package epam.hw1.config;



import epam.hw1.beans3.BeanE;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = {"epam.hw1.beans3", "epam.hw1.beans2"}, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = BeanE.class)})
public class AppConfig1 {

}
