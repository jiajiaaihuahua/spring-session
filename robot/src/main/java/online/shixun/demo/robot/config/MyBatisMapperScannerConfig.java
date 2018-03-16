/********************************************
 * Copyright (c) , shixun.online
 *
 * All rights reserved
 *
*********************************************/
package online.shixun.demo.robot.config;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p> MyBatis 扫描配置类</p>
 *
 */
@Configuration
@AutoConfigureAfter(MyBatisConfig.class)
public class MyBatisMapperScannerConfig {

	/**
	 * <p>配置MyBatis的包扫描路径以及扫描配置@Mapper的类</p>
	 * @return
	 */
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("online.shixun.demo.robot");
        mapperScannerConfigurer.setAnnotationClass(Mapper.class);
        return mapperScannerConfigurer;
    }

}
