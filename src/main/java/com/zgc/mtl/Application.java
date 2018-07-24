package com.zgc.mtl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
/**
 * 
 *description: 
 *Author:laoyangtou
 *2018年7月24日 下午4:03:02
 */
@SpringBootApplication
public class Application {
    public static void main( String[] args ){
    	ApplicationContext ctx = SpringApplication.run(Application.class, args);   	
//        System.out.println( "Hello World!" );
//        
//        String[] beanNames = ctx.getBeanDefinitionNames();
//        for (String beanName : beanNames) {
//			System.out.println(beanName);
//		}
    }
}
