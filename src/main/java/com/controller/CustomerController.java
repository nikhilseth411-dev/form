package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.entity.Customer;
import com.service.CustomerService;
import com.service.PdfService;

@RestController
@CrossOrigin(origins = "*")
public class CustomerController {

	@Autowired
	private CustomerService service;

	@Autowired
	private PdfService pdfService;

	@PostMapping("/save-pdf")
	public ResponseEntity<?> savePdf(@RequestBody Customer customer) {
		try {
			Customer saved = service.saveCustomer(customer);
			byte[] pdf = pdfService.generatePdf(saved);
			return ResponseEntity.ok().header("Content-Disposition", "attachment; filename=customer.pdf")
					.header("Content-Type", "application/pdf").body(pdf);
		} catch (RuntimeException e) {

			if ("USER_ALREADY_EXISTS".equals(e.getMessage())) {
				return ResponseEntity.status(409).body("User already exists");
			}
			return ResponseEntity.status(500).body("Save failed");
		}
	}
}