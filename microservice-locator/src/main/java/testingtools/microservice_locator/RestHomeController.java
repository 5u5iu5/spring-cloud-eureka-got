package testingtools.microservice_locator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestHomeController {

	@Autowired
	private AppIntegration appIntegration;

	@RequestMapping("/anyonelive")
	String home() {
		return "George says.." + appIntegration.retrieveHopeServices();
	}

	@RequestMapping("/")
	String geoInfo() {
		return "[..]  Westeros";
	}
}
