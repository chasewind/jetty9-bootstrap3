package com.belief.springtest;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanFactoryTest {

    @Test
    public void test() {
        @SuppressWarnings("resource")
        ApplicationContext context =
                new ClassPathXmlApplicationContext(new String[] {"service.xml"});
        PetServiceImpl service = (PetServiceImpl) context.getBean(PetService.class);
        service.init();
        service = (PetServiceImpl) context.getBean("petService");
        service.init();
        service = (PetServiceImpl) context.getBean("petService", PetService.class);
        service.init();
        service = context.getBean("petService", PetServiceImpl.class);
        service.init();
    }
}
