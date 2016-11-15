package testingtools.microservice_string;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableAutoConfiguration
@RestController
@EnableDiscoveryClient
@EnableConfigurationProperties(MyProps.class)
@RefreshScope
public class StringApp {
	@Autowired
	private ValyrioService valyrioService;

	@Autowired
	private MyProps props;

	@Value("${myprops.response:no response!}")
	String response;

	@Bean
	@RefreshScope
	public ValyrioService valyrioService() {
		return new ValyrioService(props.getName());
	}

	@RequestMapping("/valarmorghulis")
	public String home() {
		return valyrioService.getName() + " says:" + response;
	}

	public static void main(String[] args) {
		SpringApplication.run(StringApp.class, args);
	}

}

@RequiredArgsConstructor
class ValyrioService {
	private final String name;

	@SuppressWarnings("unused")
	private ValyrioService() {
		name = "UNKNOWN";
	}

	public ValyrioService(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}

@ConfigurationProperties("myprops")
class MyProps {
	private String name = "Jaqen H’ghar";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}