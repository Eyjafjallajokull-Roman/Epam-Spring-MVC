package epam.hw2.beans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;

@Order(2)

public class BeanB implements Validator {
    @Value("${beanB.name}")
    private String name;
    @Value("${beanC.value}")
    private String value;



    @Override
    public String toString() {
        return "BeanB{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    private void nameMethod() {
        System.out.println("name " + name);
    }

    private void nameMethodInitBean(){
        System.out.println("BeanPostProcess");
    }

    private void valueMethod() {
        System.out.println("value " + value);
    }

    @Override
    public void validateBefore() throws RuntimeException {
        if (name != null) {
            System.out.println("BeanB name is correct");
        } else {
            System.out.println("BeanB name is incorrect");
            throw new RuntimeException();
        }

    }

    @Override
    public void validateAfter() {
        System.out.println(value);
        if (Integer.parseInt(value) >= 0) {
            System.out.println("BeanB value is correct");
        } else {
            System.out.println("BeanB value is incorrect");
            throw new RuntimeException();
        }
    }
}
