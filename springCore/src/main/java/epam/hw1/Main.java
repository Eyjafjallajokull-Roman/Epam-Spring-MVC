package epam.hw1;



import epam.hw1.beans4.BeanABC;
import epam.hw1.config.MainAppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainAppConfig.class);
//        for (String s : applicationContext.getBeanDefinitionNames()){
//            System.out.println(s);
//        }
        BeanABC beanABC = applicationContext.getBean(BeanABC.class);
        beanABC.test();
    }
}
