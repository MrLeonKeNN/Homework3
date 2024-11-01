package aston.intensive.config_serer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigSererApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigSererApplication.class, args);
	}

}
