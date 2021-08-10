package epam.hw1.beans1;



import epam.hw1.otherBeans.OtherBeanA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BeanA {
    private OtherBeanA otherBeanA;

    @Autowired
    public BeanA(OtherBeanA otherBeanA) {
        this.otherBeanA = otherBeanA;
    }
}
