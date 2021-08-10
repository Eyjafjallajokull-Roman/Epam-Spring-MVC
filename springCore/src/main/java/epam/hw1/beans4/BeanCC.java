package epam.hw1.beans4;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
@Qualifier("beanCC")
public class BeanCC implements BeanRefactor {
    private String name;

    public BeanCC() {
        name = "beanCC";
    }

    @Override
    public String toString() {
        return "BeanCC{" +
                "name='" + name + '\'' +
                '}';
    }
}
