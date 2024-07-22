package com.doan.AppTuyenDung;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class AppTuyenDungApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppTuyenDungApplication.class, args);
	}

	// @Bean
	// public WebMvcConfigurer configure(){
	// 	return new WebMvcConfigurer() {
	// 		@Override
	// 		public void addCorsMappings(CorsRegistry reg){
	// 			reg.addMapping("/**").allowedOrigins("*");
	// 		}
	// 	};
	// }

}
