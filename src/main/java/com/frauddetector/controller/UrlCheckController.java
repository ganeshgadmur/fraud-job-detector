package com.frauddetector.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import com.frauddetector.service.UrlAnalyzerService;

@RestController
@RequestMapping("/api/check")
public class UrlCheckController {
	
	@Autowired
	UrlAnalyzerService urlAnalyzerService;
	
	
	@PostMapping
	public Map<String, Object> checkUrl(@RequestBody String url){
		return urlAnalyzerService.analyzeUrl(url);
	}

}
