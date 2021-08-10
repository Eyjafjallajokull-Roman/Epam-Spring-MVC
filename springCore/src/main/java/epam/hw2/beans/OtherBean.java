package epam.hw2.beans;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


public class OtherBean implements BeanFactoryPostProcessor {


    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        for (String beanName : configurableListableBeanFactory.getBeanDefinitionNames()) {
            if (beanName.equals("getBeanB")){
                BeanDefinition beanDefinition = configurableListableBeanFactory.getBeanDefinition(beanName);
                beanDefinition.setInitMethodName("nameMethodInitBean");
            }
        }
    }
}

