package com.dou.kylin;

import com.dou.kylin.sys.dao.AccountDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by dou on 15/7/24.
 */
public class test {
    public static void main(String[] args) {
        System.out.println( "Hello ");
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring/spring-context.xml");
        AccountDao sd = (AccountDao)ac.getBean("accountDao");
        sd.selectByUsername("admin");
    }
}
