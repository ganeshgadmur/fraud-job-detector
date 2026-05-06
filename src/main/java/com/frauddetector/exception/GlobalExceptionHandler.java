package com.frauddetector.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	//handle invalid Url
	
	@ExceptionHandler(InvalidUrlException.class)
	public String handleInvalidUrl(
			InvalidUrlException ex, Model model) {

		model.addAttribute("error", ex.getMessage());
		return "error";
	}
	
	//handle any other exception
	
	@ExceptionHandler(Exception.class)
	public String handleGeneral(Exception ex, Model model) {
		model.addAttribute("error", "Something went wrong:" + ex.getMessage());
		return "error";
	}

}
