package com.example.loginAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LoginApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginApiApplication.class, args);

	} //main

//	@Bean // the objects that form the backbone of your application and that are managed by the Spring IoC container
//	public FilterRegistrationBean<JwtFilter> jwtFilter() { //a bean used to provide configuration to register Filter instances. It can be used to provide things like URL mappings etc
//		FilterRegistrationBean<JwtFilter> registrationBean = new FilterRegistrationBean<>(); //Instanciamos el Filter registration BEan
//		registrationBean.setFilter(new JwtFilter());
//		registrationBean.addUrlPatterns("/api/login/users/*");
//		registrationBean.addUrlPatterns("/api/login/users*"); //Podemos agregar todos los patrones que queramos proteger
//		return registrationBean;
//	}//jwtFilter()

}//LoginApplication
