package com.zgc.mtl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
/**
 * 
 *description: 
 *Author:laoyangtou
 *2018年7月24日 下午4:03:02
 */
@SpringBootApplication()
@MapperScan(basePackages = {"com.zgc.mtl.dao","com.zgc.mtl.mybatisGenerator.dao"})
@EnableScheduling
public class Application {
    public static void main( String[] args ){
    	SpringApplication.run(Application.class, args);   	
    }
}
