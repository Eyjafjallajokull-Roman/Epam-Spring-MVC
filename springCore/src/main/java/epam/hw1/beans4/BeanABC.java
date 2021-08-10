package epam.hw1.beans4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class BeanABC {
    @Autowired
    private BeanRefactor beanAA;
    @Autowired
    @Qualifier("beanBB")
    private BeanRefactor beanBB;
    @Autowired
    @Qualifier("beanCC")
    private BeanRefactor beanCC;


    public void test() {
        System.out.println(beanAA);
        System.out.println(beanBB);
        System.out.println(beanCC);
    }


}
