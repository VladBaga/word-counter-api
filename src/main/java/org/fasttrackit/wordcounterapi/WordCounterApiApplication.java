package org.fasttrackit.wordcounterapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(value = "org.fasttrackit.wordcounterapi")
public class WordCounterApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WordCounterApiApplication.class, args);
	}
}