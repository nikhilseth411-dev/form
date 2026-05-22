package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.entity.Customer;
import com.service.CustomerService;
import com.service.PdfService;

@RestController
@CrossOrigin(origins="*")

public class CustomerController {

@Autowired
private CustomerService service;

@Autowired
private PdfService pdfService;



@PostMapping("/save-pdf")

public ResponseEntity<byte[]>
saveCustomer(

@RequestBody Customer customer

)throws Exception{

service.saveCustomer(
customer
);

byte[] pdf =
pdfService.generatePdf(
customer
);

return ResponseEntity
.ok()

.header(
"Content-Disposition",
"attachment; filename=form121.pdf"
)

.header(
"Content-Type",
"application/pdf"
)

.body(
pdf
);

}



@GetMapping("/customers")

public List<Customer>
getAllCustomers(){

return service
.getAllCustomers();

}

}