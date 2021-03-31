package com.andrei.springbank.bankacc.query;

import com.andrei.springbank.bankacc.core.config.AxonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EntityScan(basePackages = "com.andrei.springbank.bankacc.core.model")
@Import(AxonConfig.class)
public class BankaccQueryApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankaccQueryApiApplication.class, args);
	}

}
