package com.demo.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.dao.CustomerBankCustomized;
import com.demo.dao.CustomerCustomized;
import com.demo.dao.CustomerRepository;
import com.demo.entity.Customer;
import com.demo.exception.CustomerException;
import com.demo.exception.CustomerNotFoundException;

@Component
public class CustomerBO {
	@Autowired
	private CustomerRepository customerRepository;

	public Customer addCustomer(Customer customer) throws CustomerException {
		Customer addedCustomer = new Customer();

		String emailPattern = "^\\S+@gmail\\.com$";
		boolean isEmailValid = false;
		String phoneNumberPattern = "\\d{10}";
		boolean isContactNumberValid = false;

		isEmailValid = customer.getEmail().matches(emailPattern) ? true : false;
		isContactNumberValid = customer.getPhoneNumber().matches(phoneNumberPattern);

		if (isEmailValid == true && isContactNumberValid == true) {
			addedCustomer = customerRepository.save(customer);
		} else {
			throw new CustomerException("Given Phone Number is not valid. Please give 10 digit Number");
		}
		return addedCustomer;
	}

	public Customer findById(int id) throws CustomerNotFoundException {
		Customer customer = customerRepository.findById(id).orElse(null);
		if (customer == null) {
			throw new CustomerNotFoundException("No Customer found for the given id " + id);
		}
		return customer;
	}

	public List<Customer> findAllCustomers() throws CustomerNotFoundException {
		List<Customer> customers = new ArrayList<>();
		customers = customerRepository.findAll();
		if (customers.isEmpty()) {
			throw new CustomerNotFoundException("No Customer are found in the database!!");
		}
		return customers;
	}

	public List<Customer> findCustomerGreaterThanById(int id) throws CustomerNotFoundException {
		List<Customer> listOfCustomers = new ArrayList<>();
		listOfCustomers = customerRepository.findCustomerByGreaterThanById(id);
		if (listOfCustomers.isEmpty()) {
			throw new CustomerNotFoundException("No Customer are found in the database!!");
		}
		return listOfCustomers;
	}

	public List<CustomerCustomized> findByCustomerNameCustomized(String name) throws CustomerNotFoundException {
		List<CustomerCustomized> listOfCustomers = new ArrayList<>();
		listOfCustomers = customerRepository.findByCustomerNameCustomized(name);
		if (listOfCustomers.isEmpty()) {
			throw new CustomerNotFoundException("No Customer are found in the database!!");
		}
		return listOfCustomers;
	}

	public List<Customer> findAllOrderByName() throws CustomerNotFoundException {
		List<Customer> listOfCustomers = new ArrayList<>();
		listOfCustomers = customerRepository.findAllOrderByNameAscending();
		if (listOfCustomers.isEmpty()) {
			throw new CustomerNotFoundException("No Customer are found in the database!!");
		}
		return listOfCustomers;
	}

	public List<Customer> findCustomerWithBank() throws CustomerNotFoundException {
		List<Customer> listOfCustomers = new ArrayList<>();
		listOfCustomers = customerRepository.findCustomersWithBank();
		if (listOfCustomers.isEmpty()) {
			throw new CustomerNotFoundException("No Customer are found in the database!!");
		}
		return listOfCustomers;
	}

	public List<CustomerBankCustomized> findByCustomerBankCustomized() throws CustomerNotFoundException {
		List<CustomerBankCustomized> listOfCustomers = new ArrayList<>();
		listOfCustomers = customerRepository.findByCustomerBankCustomized();
		if (listOfCustomers.isEmpty()) {
			throw new CustomerNotFoundException("No Customer are found in the database!!");
		}
		return listOfCustomers;
	}
}
