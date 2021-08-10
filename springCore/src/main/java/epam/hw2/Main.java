package epam.hw2;

import epam.hw2.config.AppConfig1;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig1.class);


        applicationContext.close();
    }
}
