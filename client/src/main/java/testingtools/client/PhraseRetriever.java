package testingtools.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Component
public class PhraseRetriever {

	@Autowired
	private StringClient stringClient;

	@HystrixCommand(fallbackMethod = "retrieveFallbackGeoinfo")
	public String geoInfo() {
		return stringClient.geoinfo();
	}

	@HystrixCommand(fallbackMethod = "retrieveFallbackGreeting")
	public String greeting() {
		return stringClient.greeting();
	}

	public String retrieveFallbackGeoinfo() {
		return "geoInfo service is DEAD. ";
	}

	public String retrieveFallbackGreeting() {
		return "greeting service is DEAD";
	}
}
