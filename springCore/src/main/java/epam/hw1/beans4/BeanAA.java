package epam.hw1.beans4;

import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
@Primary
public class BeanAA implements BeanRefactor {
    private String name;

    public BeanAA() {
        name = "beanAA";
    }

    @Override
    public String toString() {
        return "BeanAA{" +
                "name='" + name + '\'' +
                '}';
    }
}
