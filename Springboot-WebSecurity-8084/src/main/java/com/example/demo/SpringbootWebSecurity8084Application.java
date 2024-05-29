package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.filter.HstsFilter;

@SpringBootApplication
@ServletComponentScan // 支援傳統JavaWeb 技術
@Configuration
public class SpringbootWebSecurity8084Application {

	@Bean
    public FilterRegistrationBean<HstsFilter> hstsFilterRegistrationBean() {
        FilterRegistrationBean<HstsFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new HstsFilter());
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }

	public static void main(String[] args) {
		SpringApplication.run(SpringbootWebSecurity8084Application.class, args);
	}

}
