package uos.capstone.backend.common.presentation;

import java.nio.charset.Charset;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/healthCheck")
public class HealthCheckController {

	@GetMapping("/spring")
	public String healthCheckSpring() {

		return "캡스톤 스프링 서버 정상 작동";
	}

	@GetMapping("/ai")
	public ResponseEntity<?> healthCheckFlask() {
		ResponseEntity<?> response = getHealthCheck();

		return response;
	}

	public ResponseEntity<?> getHealthCheck() {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

		HttpEntity entity = new HttpEntity(httpHeaders);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.exchange(
			"http://flask:6000/", HttpMethod.GET, entity, String.class);

		System.out.println(responseEntity.getStatusCode());
		System.out.println(responseEntity.getBody());

		return responseEntity;
	}

}
