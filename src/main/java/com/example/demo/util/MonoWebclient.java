package com.example.demo.util;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;


public class MonoWebclient {
	@Autowired
	WebClient webClient;
	
	@SuppressWarnings("unchecked")
	public HashMap<Object, Object> getWebMonotoMap(String baseUrl, String url) {
			
		HashMap<Object, Object>  monoMap = webClient.mutate()
                .baseUrl(baseUrl)
                .build()
                .get()
                .uri(url)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(HashMap.class).block();
		return monoMap;
	}
}
