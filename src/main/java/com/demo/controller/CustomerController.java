package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.entity.Customer;
import com.demo.exception.CustomerException;
import com.demo.exception.CustomerNotFoundException;
import com.demo.response.BankCustomerResponseObject;
import com.demo.response.CustomerBankCustomizedResponse;
import com.demo.response.CustomerCustomizedResponse;
import com.demo.response.CustomerResponseObject;
import com.demo.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private CustomerService customerService;

	@PostMapping("/addCustomer")
	public CustomerResponseObject addCustomer(@RequestBody Customer customer) {
		CustomerResponseObject responseObject = new CustomerResponseObject();
		responseObject = customerService.addCustomer(customer);
		return responseObject;
	}

	@GetMapping("/fetchById/{id}")
	public CustomerResponseObject fetchCustomer(@PathVariable int id)
			throws CustomerNotFoundException, CustomerException {
		CustomerResponseObject responseObject = new CustomerResponseObject();
		responseObject = customerService.findById(id);
		return responseObject;

	}

	@GetMapping("/fetchAllCustomers")
	public CustomerResponseObject fetchAllCustomers() {
		CustomerResponseObject responseObject = new CustomerResponseObject();
		responseObject = customerService.findAllCustomers();
		return responseObject;
	}

	@GetMapping("/fetchCustomerGreaterthanById/{id}")
	public CustomerResponseObject fetchCustomerGreaterthanById(@PathVariable int id)
			throws CustomerNotFoundException, CustomerException {
		CustomerResponseObject responseObject = new CustomerResponseObject();
		responseObject = customerService.findByGreaterThanById(id);
		return responseObject;

	}

	@GetMapping("/findCustomerByNameCustomized")
	public CustomerCustomizedResponse findCustomerByName(@RequestParam String name)
			throws CustomerNotFoundException, CustomerException {
		CustomerCustomizedResponse responseObject = new CustomerCustomizedResponse();
		responseObject = customerService.findByCustomerCustomized(name);
		return responseObject;
	}

	@GetMapping("/findAllOrderByName")
	public CustomerResponseObject findAllOrderByName() {
		CustomerResponseObject responseObject = new CustomerResponseObject();
		responseObject = customerService.findAllOrderByName();
		return responseObject;
	}

	@GetMapping("/findCustomersWithBank")
	public CustomerResponseObject findCustomersWithBank() {
		CustomerResponseObject responseObject = new CustomerResponseObject();
		responseObject = customerService.findCustomersWithBank();
		return responseObject;
	}

	@GetMapping("/findByCustomerBankCustomized")
	public CustomerBankCustomizedResponse findByCustomerBankCustomized() {
		CustomerBankCustomizedResponse responseObject = new CustomerBankCustomizedResponse();
		responseObject = customerService.findByCustomerBankCustomized();
		return responseObject;
	}
}
