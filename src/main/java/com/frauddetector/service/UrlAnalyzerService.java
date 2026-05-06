package com.frauddetector.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frauddetector.exception.InvalidUrlException;
import com.frauddetector.repository.FraudRepository;

@Service
public class UrlAnalyzerService {
	

	 public Map<String, Object> analyzeUrl(String url){
		 
		 if(url==null || url.trim().isEmpty()) {
			 throw new InvalidUrlException("Url cannot be empty!");
		 }
		 
		 if(!url.startsWith("http://") &&
			!url.startsWith("https://")) {
			 throw new InvalidUrlException(
			"Invalid URL fromat ! must start with http:// or https://"
					 );
		 }
		 
		 url = url.toLowerCase();
		 String domain = extractDomain(url);

       System.out.println("URL: " + url);
       System.out.println("Domain: " + domain);
		 
		 //known fraud patterns
		 List<String> fraudPatterns = List.of(
				 "blogspot.com", "wordpress.com",
			        "wixsite.com", "weebly.com",
			        "bit.ly", "tinyurl.com"
				 );
		 if(fraudPatterns.stream().anyMatch(domain::contains)) {
			 return Map.of(
					 "status", "FRAUD",
			          "reason", "Known fraudulent domain pattern",
			          "flags", List.of(
			                  "Domain is known fraud pattern",
			                  "Avoid this link immediately")
					 );
		 }

	//social media
		 List<String>socialMedia =List.of(
				"instagram.com", "facebook.com",
			     "whatsapp", "telegram", "twitter.com"
				 );
		 if(socialMedia.stream().anyMatch(url::contains)) {
			 return Map.of(
					 "status", "SUSPICIOUS",
					 "reason", "Job link shared via social media",
					 "flags", List.of("Social media job source" ));
		 }
		 
		//  — Trusted job portals
		List<String>trustedPortals = List.of(
		    	  "naukri.com", "linkedin.com",
		         "indeed.com", "shine.com",
			     "monster.com", "glassdoor.com",
			      "foundit.in", "internshala.com"
				);
		if(trustedPortals.stream().anyMatch(domain::equals)) {
			return Map.of(
					"status", "LOW_RISK",
					"reason", "Trusted job platform",
					"flags", List.of("Known job portal"));
		}
		
		//known trusted companies
		List<String> trustedDomains = List.of(
			      "tcs.com", "wipro.com", "infosys.com",
			        "cognizant.com", "accenture.com",
			        "capgemini.com", "hcl.com", "hcltech.com",
			        "techmahindra.com", "techmahindra.com",
			        "dell.com", "microsoft.com", "amazon.com",
			        "google.com", "ibm.com", "oracle.com",
			        "deloitte.com", "mindtree.com", "mphasis.com",
			        "hexaware.com", "ltimindtree.com"
		);

		if (trustedDomains.stream().anyMatch(domain::contains)) {
		    return Map.of(
		        "status", "SAFE",
		        "reason", "Official company website",
		        "flags", List.of("Verified  company domain"));
		}

	

		// Step 5 — completely unknown
		return Map.of(
		    "status", "UNVERIFIED",
		    "reason", "Company not in our database — verify independently before applying",
		    "flags", List.of(
		            "Company not verified",
		            "Research before applying")
			    );
	 }
		
		private String extractDomain(String url) {
	        try {
	            url = url.replace("https://", "")
	                     .replace("http://", "")
	                     .replace("www.", "");
	            return url.split("/")[0];
	        } catch (Exception e) {
	            return url;
	        }
		     	
}

}