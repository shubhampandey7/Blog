package com.blog.config;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
public class Configure {
	
	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
