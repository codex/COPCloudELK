package cop.cloud.elk.aggregator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AggregatorConfig {
	@Bean
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		// TODO: Add a ClientHttpRequestInterceptor to add Session ID to headers
		return restTemplate;
	}

}