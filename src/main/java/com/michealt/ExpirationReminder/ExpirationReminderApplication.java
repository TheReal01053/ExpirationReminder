package com.michealt.ExpirationReminder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;


@EnableAutoConfiguration
@Configuration
@SpringBootApplication
public class ExpirationReminderApplication {
	public static String htmlMessage;

	public static void main(String[] args) throws IOException {

		printFile();


		SpringApplication.run(ExpirationReminderApplication.class, args);
	}

	private static void printFile() throws IOException {
		Path htmlDoc = Paths.get("\\\\clar_fs01\\GroupDrive\\Apps\\ExpirationReminder\\", "index.html");
		try (FileReader reader = new FileReader(htmlDoc.toFile());
			 BufferedReader br = new BufferedReader(reader)) {

			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
				htmlMessage += line;
			}
		}
	}

	private static void loop() {

		int[] someArray = { 1, 2, 5, 6, 3 };

		for (int i = 0; i < someArray.length; i++) {
			System.out.println(someArray[i]);
		}
	}
}
