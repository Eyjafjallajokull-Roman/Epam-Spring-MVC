package epam.hw2.beans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;

@Order(1)
public class BeanD implements Validator {
    @Value("${beanD.name}")
    private String name;
    @Value("${beanD.value}")
    private String value;

    @Override
    public String toString() {
        return "BeanD{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    private void nameMethod(){
        System.out.println("name " + name);
    }

    private void valueMethod(){
        System.out.println("value " + value);
    }

    @Override
    public void validateBefore() throws RuntimeException {
        if (name != null) {
            System.out.println("BeanD name is correct");
        } else {
            System.out.println("BeanD name is incorrect");
            throw new RuntimeException();
        }

    }

    @Override
    public void validateAfter() {
        if (Integer.parseInt(value) >= 0) {
            System.out.println("BeanD value is correct");
        } else {
            System.out.println("BeanD value is incorrect");
            throw new RuntimeException();
        }
    }
}
