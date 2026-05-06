package com.frauddetector.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.frauddetector.model.JobReport;

@Repository
public interface FraudRepository 
 extends JpaRepository<JobReport, Long>
{
	List<JobReport> findByCompanyName(String companyName);
	List<JobReport> findByStatus(String status);

}
