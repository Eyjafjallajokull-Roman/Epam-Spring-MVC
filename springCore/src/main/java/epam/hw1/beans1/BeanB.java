package epam.hw1.beans1;



import epam.hw1.otherBeans.OtherBeanB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BeanB {
    @Autowired
    private OtherBeanB otherBeanB;


}
