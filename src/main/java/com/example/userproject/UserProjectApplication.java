package com.example.userproject;

import com.example.userproject.user.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.format.Formatter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@SpringBootApplication
public class UserProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserProjectApplication.class, args);
	}

	@Bean
	public Formatter<LocalDate> localDateFormatter() {
		return new Formatter<>() {
			@Override
			public String print(LocalDate object, Locale locale) {
				return DateTimeFormatter.ISO_DATE.format(object);
			}

			@Override
			public LocalDate parse(String text, Locale locale)    {
				return LocalDate.parse(text, DateTimeFormatter.ISO_DATE);
			}
		};
	}

}
