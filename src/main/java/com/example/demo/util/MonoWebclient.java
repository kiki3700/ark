package com.example.demo.util;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Component
public class MonoWebclient {
	@Autowired
	WebClient webClient;
	
	@SuppressWarnings("unchecked")
	public String getWebMonotoJson(String baseUrl, String url) {
			
		String  responseJson = webClient.mutate()
                .baseUrl(baseUrl)
                .build()
                .get()
                .uri(url)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class).block();
		return responseJson;
	}
}
