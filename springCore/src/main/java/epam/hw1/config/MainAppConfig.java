package epam.hw1.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({AppConfig2.class, AppConfig1.class})
@ComponentScan(basePackages = {"epam.hw1.otherBeans", "epam.hw1.beans4"})
public class MainAppConfig {

}

