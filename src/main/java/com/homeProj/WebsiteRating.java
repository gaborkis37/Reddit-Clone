package com.homeProj;

import java.util.Locale;

import org.ocpsoft.prettytime.PrettyTime;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.homeProj.config.SpringitProperties;

@SpringBootApplication
@EnableConfigurationProperties(SpringitProperties.class)
@EnableJpaAuditing
public class WebsiteRating {

	public static void main(String[] args) {
		SpringApplication.run(WebsiteRating.class, args);
	}

	@Bean
	PrettyTime prettytime() {
		return new PrettyTime(new Locale(""));
	}
}
