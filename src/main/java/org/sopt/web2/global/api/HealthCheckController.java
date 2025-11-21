package org.sopt.web2.global.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController implements HealthCheckApi {

	@Override
	@GetMapping("/health-check")
	public String healthCheck() {
		return "OK";
	}

}