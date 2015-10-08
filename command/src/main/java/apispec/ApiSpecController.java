// controller and application starter/entry point
package apispec;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class ApiSpecController {

	@Autowired
	private ApiSpecRepository apiSpecRepository;

	@RequestMapping(value = "/apiSpecs", method = RequestMethod.POST, headers = { "validation=spring-boot-103" }, produces = "application/json")
	public Map<String, String> postCommand(@RequestBody ApiSpec apispec) {
		Map<String, String> ACK = new HashMap<String, String>();
		ACK.put("success", "true");
		ACK.put("message", "started saving the received data");

		System.out.println(apispec);
		// start a worker actor to take care of the data saving task
		// ----
		apiSpecRepository.save(apispec);
		// ----

		return ACK;
	}

	@RequestMapping(value = "/apiSpecs/{id}", method = RequestMethod.DELETE, headers = { "validation=spring-boot-103" }, produces = "application/json")
	public Map<String, String> deleteCommand(@PathVariable("id") long id) {
		Map<String, String> ACK = new HashMap<String, String>();
		ACK.put("success", "true");
		ACK.put("message", "started deleting entry with provided id");
		return ACK;
	}

	public static void main(String[] arguments) {
		SpringApplication.run(ApiSpecController.class, arguments);
	}
}
