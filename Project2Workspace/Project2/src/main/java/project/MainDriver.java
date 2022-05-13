package project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({"classpath*:applicationContext.xml"})
public class MainDriver {

	public static void main(String[] args) {
		SpringApplication.run(MainDriver.class, args);
	}
	
}
