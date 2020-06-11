package com.tcs.xpl.inventory.InventoryConsumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableWebSecurity
public class Config extends WebSecurityConfigurerAdapter{	
	
 @Bean
 public RestTemplate getRestTemplate()
 {
	 return new RestTemplate();
 }
 
 protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
	 	http.csrf().disable();
		http.authorizeRequests().anyRequest().permitAll();
	}
 
}