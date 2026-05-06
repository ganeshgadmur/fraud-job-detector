package com.frauddetector.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frauddetector.model.JobReport;
import com.frauddetector.repository.FraudRepository;

@Service   
	public class FraudService {

	    @Autowired
	    FraudRepository fraudRepository;
	    
	    @Autowired
	    UrlAnalyzerService urlAnalyzerService;

	    @Autowired
	    DescriptionScannerService descriptionScannerService;
	    
	   // Save a new report
	    public JobReport saveReport(JobReport jobReport) {
	    	
	    	//Analyze Url
	    	Map<String, Object> urlResult =
	    	    urlAnalyzerService.analyzeUrl(jobReport.getJobLink());
	    	//get status from analysis
	    	String urlStatus = (String)urlResult.get("status");
	    	List<String> urlFlags = (List<String>) urlResult.get("flags");
	    	
	    	
	    	//scan Description
	    	Map<String, Object> descResult =
	    	descriptionScannerService.scanDescription(jobReport.getDescription());
	    	String descRisk = (String) descResult.get("riskLevel");
	    	  List<String> descFlags =(List<String>)descResult.get("flagsFound");
	    	  
	    	  //combine all flags
	    	  
	    	  List<String> allFlags = new ArrayList<>();
	    	  allFlags.addAll(urlFlags);
	    	  allFlags.addAll(descFlags);
	    	  
	    	  //join flags as single string for DB
	    	  
	    	  String reasonsText = String.join(" | ", allFlags);
	    	  jobReport.setDetectedReasons(reasonsText);
	    	  
	    	  // set final status
	    	String finalStatus = combinedStatus(urlStatus, descRisk);
	    	   //set status on jobReport
    	   jobReport.setStatus(finalStatus);
    	   
//	    //save to database
	        return fraudRepository.save(jobReport);
	    }

	    
	private String combinedStatus(String urlStatus, String descRisk) {
	    	
	    	
	        // Fraud only when both are bad
	    	if (urlStatus.equals("FRAUD") || 
	                descRisk.equals("FRAUD")) {
	                return "FRAUD";
	            }
	    	// URl fraud alone = fraud
	    	if(urlStatus.equals("FRAUD")){
	    		return "FRAUD";
	    	}
	    	
	    	//description fraud alone = suspicious
	    	//(URl might be genuine company)
	    	
	    	if(descRisk.equals("FRAUD")) {
	    		return "SUSPICIOUS";
	    	}
	    				

	            //Both suspicious = fraud
	            if (urlStatus.equals("SUSPICIOUS") && 
	                descRisk.equals("SUSPICIOUS")) {
	                return "FRAUD";
	            }
	            
	            //either suspicious
	            if (urlStatus.equals("SUSPICIOUS") || 
		                descRisk.equals("SUSPICIOUS")) {
		                return "SUSPICIOUS";
		            }

	            // WARNING = suspicious
	            if (descRisk.equals("WARNING")) {
	                return "SUSPICIOUS";
	            }

	            // URL says SAFE and description CLEAN
	            if (urlStatus.equals("SAFE") && 
	                descRisk.equals("CLEAN")) {
	                return "SAFE";
	            }
	            //default
	            return urlStatus;
	    }
	    // Get all reports
	    public List<JobReport> getAllReports() {
	        return fraudRepository.findAll();
	    }

	    // Find by company name
	    public List<JobReport> findByCompany(String companyName) {
	        return fraudRepository.findByCompanyName(companyName);
	    }

	    // Find all fraud reports
	    public List<JobReport> getFraudReports() {
	        return fraudRepository.findByStatus("FRAUD");
	        
	    }
	    // get reports by status
	    public List<JobReport> getReportsByStatus(String status){
	    	return fraudRepository.findByStatus(status);
	    }
	    
	    //update status
	
	    public  void updateStatus(Long id, String status) {
	    	JobReport report = fraudRepository.findById(id)
	    			.orElseThrow(()->
	    			new RuntimeException("Report not found!"));
	    	report.setStatus(status);
	    	fraudRepository.save(report);
	    }
	    
	    //delete report
	    
	    public void deleteReport(Long id) {
	    	 fraudRepository.deleteById(id);
	       }
	    }
	    
	



