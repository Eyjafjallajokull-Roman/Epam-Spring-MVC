package epam.hw2.beans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;

@Order(3)
public class BeanC implements Validator {

    @Value("${beanC.name}")
    private String name;
    @Value("${beanC.value}")
    private String value;

    @Override
    public String toString() {
        return "BeanC{" +
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
            System.out.println("BeanC name is correct");
        } else {
            System.out.println("BeanC name is incorrect");
            throw new RuntimeException();
        }

    }

    @Override
    public void validateAfter() {
        if (Integer.parseInt(value) >= 0) {
            System.out.println("BeanC value is correct");
        } else {
            System.out.println("BeanC value is incorrect");
            throw new RuntimeException();
        }
    }
}
