package epam.hw2.beans;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


public class BeanE implements Validator {
    private String name;
    private String value;

    @Override
    public String toString() {
        return "BeanE{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    @PostConstruct
    public void postConstruct() {
        name = "BeanEE";
        System.out.println(name);
    }

    @PreDestroy
    public void preDestroy() {
        name = null;
        System.out.println(name);
    }

    @Override
    public void validateBefore() throws RuntimeException {
        if (name == null) {
            System.out.println("BeanE name is correct");
        } else {
            System.out.println("BeanE name is incorrect");
            throw new RuntimeException();
        }

    }

    @Override
    public void validateAfter() {
        if (name != null) {
            System.out.println("BeanE value is correct");
        } else {
            System.out.println("BeanE value is incorrect");
            throw new RuntimeException();
        }
    }
}
