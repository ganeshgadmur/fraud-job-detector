package com.frauddetector.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class DescriptionScannerService {
	
	public Map<String, Object> scanDescription(String description){
		
		if(description ==null || description.trim().isEmpty()) {
			return Map.of(
					"riskLevel", "UNKNOWN",
					"flagsFound",List.of(),
					"message", "No description provided"
					);
		}
		description = description.toLowerCase();
		
		// red flag patterns
		
		List<String> fraudPatterns = List.of(
				 "pay registration fee",
		            "security deposit",
		            "training fee",
		            "pay to join",
		            "zoom interview",
		            "work from home earn",
		            "earn from home",
		            "no experience needed",
		            "no experience required",
		           "whatsapp only",
		            "contact on whatsapp",
		            "telegram only",
		            "urgent hiring",
		            "limited seats",
		            "guaranteed job",
		            "100% placement",
		            "part time earn",
		            "daily payment",
		            "weekly payment"
				);
		
		//find which patterns matched
		List<String> flagsFound = new ArrayList<>();
		for(String pattern : fraudPatterns) {
			if(description.contains(pattern)) {
				flagsFound.add(pattern);
				
			}
		}
		//claculate risk level
		
		if (flagsFound.size() >= 3) {
            return Map.of(
                "riskLevel", "FRAUD",
                "flagsFound", flagsFound,
                "message", "Multiple fraud patterns detected!"
            );
        } else if (flagsFound.size() == 2) {
            return Map.of(
                "riskLevel", "SUSPICIOUS",
                "flagsFound", flagsFound,
                "message", "Suspicious patterns found!"
            );
        } else if (flagsFound.size() == 1) {
            return Map.of(
                "riskLevel", "WARNING",
                "flagsFound", flagsFound,
                "message", "One warning pattern found"
            );
        }
		
		return Map.of(
				"riskLevel", "CLEAN",
				"flagsFound", List.of(),
				"message", "No found patterns detected"
				);

	}

}
