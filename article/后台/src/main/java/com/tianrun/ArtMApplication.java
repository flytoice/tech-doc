package com.tianrun;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication()
public class ArtMApplication {

	private static Logger log = LoggerFactory.getLogger(ArtMApplication.class);
	public static void main(String[] args) {
		log.info("wangyaohua");
		SpringApplication.run(ArtMApplication.class, args);
	}
}
