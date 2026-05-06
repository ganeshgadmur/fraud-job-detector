package com.frauddetector.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity

@Table(name ="job_reports")
public class JobReport {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
	
	private String companyName;
	private String jobTitle;
	private String jobLink;
	private String description;
	private String reportedReason;
	private String detectedReasons;
	
	private String status;
	
	public JobReport() {}
	
		public JobReport(String companyName, String jobTitle,
				String jobLink, String description, 
				String reportedReason, String status, String detectedReasons) {
		this.companyName =companyName;
		this.jobTitle =jobTitle;
		this.jobLink = jobLink;
		this.description = description;
		this.reportedReason = reportedReason;
		this.status =status;
		this.detectedReasons = detectedReasons;
		
}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getCompanyName() {
			return companyName;
		}

		public void setCompanyName(String companyName) {
			this.companyName = companyName;
		}

		public String getJobTitle() {
			return jobTitle;
		}

		public void setJobTitle(String jobTitle) {
			this.jobTitle = jobTitle;
		}

		public String getJobLink() {
			return jobLink;
		}

		public void setJobLink(String jobLink) {
			this.jobLink = jobLink;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getReportedReason() {
			return reportedReason;
		}

		public void setReportedReason(String reportedReason) {
			this.reportedReason = reportedReason;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}
		

		public String getDetectedReasons() {
			return detectedReasons;
		}

		public void setDetectedReasons(String detectedReasons) {
			this.detectedReasons = detectedReasons;
		}

		@Override
		public String toString() {
			return "JobReport [id=" + id + ", companyName=" + companyName + ", jobTitle=" + jobTitle + ", jobLink="
					+ jobLink + ", description=" + description + ", reportedReason=" + reportedReason
					+ ", detectedReasons=" + detectedReasons + ", status=" + status + "]";
		}



}
