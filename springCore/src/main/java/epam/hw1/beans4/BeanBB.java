package epam.hw1.beans4;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(3)
@Qualifier("beanBB")
public class BeanBB implements BeanRefactor {
    private String name;
    public BeanBB() {
        name = "beanBB ";
    }

    @Override
    public String toString() {
        return "BeanBB{" +
                "name='" + name + '\'' +
                '}';
    }
}
