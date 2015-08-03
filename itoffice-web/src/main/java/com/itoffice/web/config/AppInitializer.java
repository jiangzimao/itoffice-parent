/**   
 * Copyright © 2015 itofficewb. All rights reserved.
 * 
 * @Title: AppInitializer.java 
 * @Prject: itoffice-web
 * @Package: com.itoffice.web.config 
 * @author: jiangzimao@126.com   
 * @date: 2015年8月2日 下午3:33:35 
 * @version: V1.0   
 */
package com.itoffice.web.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.util.ClassUtils;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.springframework.web.util.Log4jConfigListener;

import com.itoffice.common.Constants;

/** 
 * @ClassName: AppInitializer 
 * @Description: APP初始化
 * @author: jiangzimao@126.com
 * @date: 2015年8月2日 下午3:33:35  
 */
@Order(0)
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	
	public static final Logger log = LoggerFactory.getLogger(AppInitializer.class);

	@Override
    public void onStartup(ServletContext servletContext) throws ServletException {
		// 使用InputStream得到一个资源文件
		InputStream inputstream = null;
		try {
			inputstream = this.getClass().getResourceAsStream("/sysconfig.properties");
			// new 一个Properties
			Properties properties = new Properties();
			properties.load(inputstream);
			servletContext.setInitParameter(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, properties.getProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME)); 
			servletContext.setInitParameter(Constants.SWAGGER_API_BASEPATH, properties.getProperty(Constants.SWAGGER_API_BASEPATH));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(inputstream != null){
				try {
					inputstream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		//Log4jConfigListener 
		servletContext.setInitParameter("log4jConfigLocation", "classpath:log4j.properties"); 
        servletContext.addListener(Log4jConfigListener.class); 
        
        servletContext.addListener(new RequestContextListener());
        
        super.onStartup(servletContext);
    }
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { AppConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		Class<?> swaggerConfigClass = null;
		try {
            swaggerConfigClass = ClassUtils.forName("com.itoffice.api.config.SwaggerConfig", null);
        } catch (Exception ex) {
            swaggerConfigClass = null;
            log.error("exception is thrown when loading SwaggerConfig class [com.itoffice.api.config.SwaggerConfig]...@" + ex);
        }
		List<Class<?>> classes = new ArrayList<Class<?>>();
        classes.add(AppConfig.class);
        if (swaggerConfigClass != null) {
            classes.add(swaggerConfigClass);
        }
        return classes.toArray(new Class[classes.size()]);
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
	
	@Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        encodingFilter.setForceEncoding(true);
        return new Filter[]{encodingFilter};
    }
	
}
