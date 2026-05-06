package com.frauddetector.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import com.frauddetector.model.JobReport;
import java.util.List;
import com.frauddetector.service.FraudService;

@RestController
@RequestMapping("/api/jobs")
public class FraudController {
	
	@Autowired
	FraudService fraudservice;
	
	
	@PostMapping
	public JobReport createReport(@RequestBody JobReport jobReport) {
		return fraudservice.saveReport(jobReport);
	}
	
	@GetMapping
	public List<JobReport> getAllReports(){
		return fraudservice.getAllReports();
	}
	
	@GetMapping("/company/{name}")
	public List<JobReport> getByCompany(@PathVariable String name){
		return fraudservice.findByCompany(name);
	}
	
	@GetMapping("/fraud")
	public List<JobReport> getFraudReports(){
		return fraudservice.getFraudReports();
	}

}
