package com.example.demo.data.service;

import java.util.HashMap;
import java.util.Map;

public interface BokService {
	
	public HashMap<Object, Object> getBokIndex() throws Exception;

	public HashMap<Object, Object> getBokIndex(Map<String, String> inParam) throws Exception;
	
}
