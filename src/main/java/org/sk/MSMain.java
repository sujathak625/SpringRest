package org.sk;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class MSMain {


	public static void main(String[] args) throws Exception {
		System.getProperties().put( "server.port", 8191); 
		SpringApplication.run(MSMain.class, args);
	}

}