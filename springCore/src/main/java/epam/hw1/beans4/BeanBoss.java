package epam.hw1.beans4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BeanBoss {
    @Autowired
    private List<BeanRefactor> beanRefactors;

    public void printBeans(){
        for (BeanRefactor beanRefactor: beanRefactors){
            System.out.println(beanRefactor);
        }
    }
}
