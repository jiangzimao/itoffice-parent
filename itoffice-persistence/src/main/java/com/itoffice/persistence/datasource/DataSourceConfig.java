/**   
 * Copyright © 2015 itofficewb. All rights reserved.
 * 
 * @Title: DataSourceConfig.java 
 * @Prject: itoffice-persistence
 * @Package: com.itoffice.persistence.datasource 
 * @author: jiangzimao@126.com   
 * @date: 2015年8月2日 上午11:35:51 
 * @version: V1.0   
 */
package com.itoffice.persistence.datasource;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

/** 
 * @ClassName: DataSourceConfig 
 * @Description: 数据源配置信息
 * @author: jiangzimao@126.com
 * @date: 2015年8月2日 上午11:35:51  
 */
@Configuration
public class DataSourceConfig {
	
	private static final String DRIVER_CLASS_NAME = "jdbc.driverClassName";
    private static final String URL = "jdbc.url";
    private static final String USERNAME = "jdbc.username";
    private static final String PASSWORD = "jdbc.password";
    
    @Inject
    private Environment env;

    @Bean(name="dataSource")
    @Profile("dev")
    public DataSource dataSource() {
        BasicDataSource bds = new BasicDataSource();
        bds.setDriverClassName(env.getProperty(DRIVER_CLASS_NAME));
        bds.setUrl(env.getProperty(URL));
        bds.setUsername(env.getProperty(USERNAME));
        bds.setPassword(env.getProperty(PASSWORD));
        return bds;
    }
}

