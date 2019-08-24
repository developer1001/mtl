package com.zgc.mtl.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

//@Configuration
public class DatabaseConfig {

//    @Bean
//    @ConfigurationProperties(prefix = "mydb.datasource")
//    public DataSource dataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean
//    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
//        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
//        sessionFactory.setDataSource(dataSource);
//        return sessionFactory.getObject();
//    }
//
//    @Bean
//    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }
	
//	@Bean  
//    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
//        
//        PropertySourcesPlaceholderConfigurer p = new PropertySourcesPlaceholderConfigurer();
//        String[] locations = {
//                "config/application.properties"
//        };
//        
//        List<ClassPathResource> locationsList = new ArrayList<ClassPathResource>();
//        
//        for(String location: locations){
//            locationsList.add(new ClassPathResource(location));
//        }
////        new ClassPathResource0
//        p.setLocations(locationsList.toArray(new ClassPathResource[1]));
//        return p;
//    }    
}
