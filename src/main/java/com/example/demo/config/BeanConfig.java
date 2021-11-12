package com.example.demo.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(
		basePackages = {"com.example.demo.data","com.example.demo.trade"}
)
public class BeanConfig {

}
