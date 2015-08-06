/**   
 * Copyright © 2015 itofficewb. All rights reserved.
 * 
 * @Title: AppInitializer.java 
 * @Prject: itoffice-web
 * @Package: com.itoffice.api.comfig
 * @author: jiangzimao@126.com   
 * @date: 2015年8月2日 下午3:33:39 
 * @version: V1.0   
 */
package com.itoffice.api.comfig;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.PathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.WebAsyncTask;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;

/**
 *
 * SwaggerConfig
 * @auth jiangzimao@126.com   
 * @data 2015年8月2日 下午3:33:39 
 *
 */
@Configuration
@EnableSwagger
public class SwaggerConfig {
	
	private SpringSwaggerConfig springSwaggerConfig;

	/**
	 * Required to autowire SpringSwaggerConfig
	 */
	@Autowired
	public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig) {
		this.springSwaggerConfig = springSwaggerConfig;
	}

	/**
	 * Every SwaggerSpringMvcPlugin bean is picked up by the swagger-mvc
	 * framework - allowing for multiple swagger groups i.e. same code base
	 * multiple swagger resource listings. 
	 */
	@Bean
	public SwaggerSpringMvcPlugin customImplementation() {
		List<Class> classList = new ArrayList<Class>();
		classList.add(HttpEntity.class);
        classList.add(ResponseEntity.class);
        classList.add(Callable.class);
        classList.add(DeferredResult.class);
        classList.add(WebAsyncTask.class);
		
		return new SwaggerSpringMvcPlugin(this.springSwaggerConfig).apiVersion("V1.0")//
		        .apiInfo(apiInfo())//
		        .includePatterns(".*?")
		        .ignoredParameterTypes(classList.toArray(new Class[classList.size()]))
		        .build();
	}
	
	private ApiInfo apiInfo()
    {
        ApiInfo apiInfo = new ApiInfo(
                "My Apps API Title", 
                "My Apps API Description",
                "My Apps API terms of service", 
                "My Apps API Contact Email", 
                "My Apps API Licence Type",
                "My Apps API License URL");
        return apiInfo;
    }
	
	@Bean
	public CommonsMultipartResolver multipartResolver() throws IOException{
	    CommonsMultipartResolver resolver = new CommonsMultipartResolver();
	    resolver.setDefaultEncoding("UTF-8");
	    resolver.setMaxInMemorySize(1024);
	    resolver.setMaxUploadSize(-1);
	    resolver.setUploadTempDir(new PathResource("D://temp/"));
	    return resolver;
	}
}