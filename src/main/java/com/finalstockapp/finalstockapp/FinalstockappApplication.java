package com.finalstockapp.finalstockapp;

import com.finalstockapp.finalstockapp.Service.CompanyService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@SpringBootApplication
@EnableFeignClients
public class FinalstockappApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinalstockappApplication.class, args);
	}
//	@Bean
//	CompanyService getCompanyService(){
//		return new CompanyService();
//	}

}
