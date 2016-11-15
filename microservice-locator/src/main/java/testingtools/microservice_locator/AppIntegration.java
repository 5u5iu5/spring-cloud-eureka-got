package testingtools.microservice_locator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.DiscoveryClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Component
public class AppIntegration {

	@Autowired
	private DiscoveryClient discoveryClient;

	@HystrixCommand(fallbackMethod = "retrieveFallbackNoHope")
	public String retrieveHopeServices() {

		InstanceInfo instanceInfo = discoveryClient.getNextServerFromEureka("hope", false);
		instanceInfo.getHomePageUrl();
		return "Yeah!";
	}

	public String retrieveFallbackNoHope() {
		return "NOBODY! JUAJUAJUA";
	}
}
