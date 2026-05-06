package com.frauddetector.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.frauddetector.model.JobReport;
import com.frauddetector.service.FraudService;

@Controller
public class HomeUIController {
     
	@Autowired
	FraudService fraudService;
	
	
	@GetMapping("/home")
	public String home(Model model) {
		model.addAttribute("jobReport", new JobReport());
		  return "index";
}
	
	@PostMapping("/submit")
	public String submitJob(@ModelAttribute JobReport jobReport, Model model) {
		
		JobReport saved = fraudService.saveReport(jobReport);
		
		model.addAttribute("result", saved);
		return "result";
	}
	
	@GetMapping("/reports")
	 public String viewReports(Model model) {
		model.addAttribute("reports", fraudService.getAllReports());
		return "reports";
	}
	
}