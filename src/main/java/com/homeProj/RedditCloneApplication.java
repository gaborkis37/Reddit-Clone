package com.homeProj;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.homeProj.config.SpringitProperties;
import com.homeProj.domain.Comment;
import com.homeProj.domain.Link;
import com.homeProj.repository.CommentRepository;
import com.homeProj.repository.LinkRepository;

@SpringBootApplication
@EnableConfigurationProperties(SpringitProperties.class)
public class RedditCloneApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedditCloneApplication.class, args);
	}

	
	@Bean
	CommandLineRunner runner(LinkRepository linkRepo, CommentRepository commentRepo) {
		return args -> {
			Link link = new Link("Getting Started with spring boot 2","https://google.com");
			linkRepo.save(link);
			
			Comment comment =  new Comment();
			comment.setBody("This is the body");
			comment.setLink(link);
			
			commentRepo.save(comment);
			link.addComment(comment);
			
	
		};
	}
}
