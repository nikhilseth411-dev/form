package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Customer;
import com.repository.CustomerRepository;

@Service
public class CustomerService {

@Autowired
private CustomerRepository repository;


public Customer saveCustomer(
Customer customer
){

boolean exists =

repository
.existsByPanNumber(

customer
.getPanNumber()

);

if(
exists
){

throw new RuntimeException(
"USER_ALREADY_EXISTS"
);

}

return repository
.save(
customer
);

}


public List<Customer>
getAllCustomers(){

return repository
.findAll();

}

}