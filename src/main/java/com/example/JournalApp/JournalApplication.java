package com.example.JournalApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.data.mongodb.MongoDatabaseFactory;


@EnableTransactionManagement
@EnableMongoRepositories(basePackages = "com.example.JournalApp.Repository")
@SpringBootApplication
public class JournalApplication {

	public static void main(String[] args) {
		SpringApplication.run(JournalApplication.class, args);
	}

	@Bean
	public PlatformTransactionManager name(MongoDatabaseFactory dbFactory){
		return new MongoTransactionManager(dbFactory);
	}
}


	//In Spring Framework, PlatformTransactionManager is the central interface for managing transactions
    // (begin, commit, rollback) in a consistent way across various transaction types (JPA, JDBC, MongoDB, etc.).

