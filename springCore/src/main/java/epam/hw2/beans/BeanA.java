package epam.hw2.beans;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;


public class BeanA implements InitializingBean, DisposableBean, Validator {
    private String name;
    private String value;


    @Override
    public String toString() {
        return "BeanA{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("BeanA was destroyed");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("BeanA initialization");
    }


    @Override
    public void validateBefore() throws RuntimeException {
        if (name == null) {
            System.out.println("BeanA name is correct");
        } else {
            System.out.println("BeanA name is incorrect");
            throw new RuntimeException();
        }

    }

    @Override
    public void validateAfter() {
        if (value == null) {
            System.out.println("BeanA value is correct");
        } else {
            System.out.println("BeanA value is incorrect");
            throw new RuntimeException();
        }
    }
}
