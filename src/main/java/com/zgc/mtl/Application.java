package com.zgc.mtl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * 
 *description: 
 *Author:laoyangtou
 *2018年7月24日 下午4:03:02
 */
@SpringBootApplication()
@MapperScan(basePackages = {"com.zgc.mtl.dao","com.zgc.mtl.mybatisGenerator.dao"})
public class Application {
    public static void main( String[] args ){
//    	/**
//         * Springboot整合Elasticsearch 在项目启动前设置一下的属性，防止报错
//         * 解决netty冲突后初始化client时还会抛出异常
//         * java.lang.IllegalStateException: availableProcessors is already set to [4], rejecting [4]
//         */
//        System.setProperty("es.set.netty.runtime.available.processors", "false");
    	SpringApplication.run(Application.class, args);   	
//        System.out.println( "Hello World!" );
//        
//        String[] beanNames = ctx.getBeanDefinitionNames();
//        for (String beanName : beanNames) {
//			System.out.println(beanName);
//		}
    }
}
