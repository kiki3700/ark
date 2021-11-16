package com.example.demo.config;

import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.example.demo.data.scheduler.IndexScheduler;

@Configuration
public class MethodInterceptorConfig {
	
	@Autowired 
	IndexScheduler indexScheduler;
	
	/*@Bean
	@Primary
	public ProxyFactoryBean cybosProxyFactoryBean() {
		ProxyFactoryBean cybosProxyFactoryBean = new ProxyFactoryBean();
		cybosProxyFactoryBean.setTarget(indexScheduler);
		cybosProxyFactoryBean.setInterceptorNames("cybosInterceptor");
		return cybosProxyFactoryBean;
	}*/
	
}
