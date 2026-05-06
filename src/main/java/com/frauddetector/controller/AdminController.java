package com.frauddetector.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.frauddetector.service.FraudService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	 @Autowired
	 FraudService fraudService;
	 
	 @GetMapping
	 public String adminPanel(Model model) {
		 model.addAttribute("reports", fraudService.getAllReports());
		 return "admin";
	 }
	 @GetMapping("/filter")
	 public String filterReports(@RequestParam String status, Model model) {
		 model.addAttribute("reports", fraudService.getReportsByStatus(status));
		 model.addAttribute("selectedStatus", status);
		 return "admin";
	 }
	 
	 @PostMapping("/update/{id}")
	 public String updateStatus(
			 @PathVariable Long id,
			 @RequestParam String status) {
		 fraudService.updateStatus(id, status);
		 return "redirect:/admin";
		 
	 }
	 
	 @PostMapping("/delete/{id}")
	 public String deleteReport(
			 @PathVariable Long id) {
		 fraudService.deleteReport(id);
		 return "redirect:/admin";
	 }
}
