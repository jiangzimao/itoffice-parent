/**   
 * Copyright © 2015 itoffice. All rights reserved.
 * 
 * @Title: AppConfig.java 
 * @Prject: itoffice-web
 * @Package: com.itoffice.web.config 
 * @author: jiangzimao@126.com   
 * @date: 2015年8月1日 下午10:23:44 
 * @version: V1.0   
 */
package com.itoffice.web.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySources;

import com.itoffice.persistence.config.JpaConfig;

/** 
 * @ClassName: AppConfig 
 * @Description: APP配置文件
 * @author: jiangzimao@126.com
 * @date: 2015年8月1日 下午10:23:44  
 */

@Configuration
@ComponentScan(basePackages={"com.itoffice"},
excludeFilters=@ComponentScan.Filter(type=FilterType.REGEX, pattern={"com.itoffice.*"}))
//属性文件导入配置
@PropertySources(
        value = {
        		//@PropertySource(value = "classpath:sysconfig.properties", ignoreResourceNotFound = false)
        })

@Import(value = {
		JpaConfig.class, //JPA配置 
		WebConfiguration.class 
		})
public class AppConfig {

}
