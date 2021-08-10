package epam.hw2.beans;

import org.springframework.context.annotation.Lazy;

@Lazy
public class BeanF implements Validator {
    private String name;
    private String value;


    @Override
    public String toString() {
        return "BeanF{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    @Override
    public void validateBefore() throws RuntimeException {
        if (name == null) {
            System.out.println("BeanF name is correct");
        } else {
            System.out.println("BeanF is incorrect");
            throw new RuntimeException();
        }

    }

    @Override
    public void validateAfter() {
        if (value == null) {
            System.out.println("BeanF value is correct");
        } else {
            System.out.println("BeanF is incorrect");
            throw new RuntimeException();
        }
    }
}

