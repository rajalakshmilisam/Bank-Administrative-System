package com.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.bo.CustomerBO;
import com.demo.dao.CustomerBankCustomized;
import com.demo.dao.CustomerCustomized;
import com.demo.dto.BankDTO;
import com.demo.dto.CustomerBankCustomizedDTO;
import com.demo.dto.CustomerCustomizedDTO;
import com.demo.dto.CustomerDTO;
import com.demo.entity.Customer;
import com.demo.exception.CustomerException;
import com.demo.exception.CustomerNotFoundException;
import com.demo.response.CustomerBankCustomizedResponse;
import com.demo.response.CustomerCustomizedResponse;
import com.demo.response.CustomerResponseObject;

@Service
public class CustomerService {
	@Autowired
	private CustomerBO customerBO;

	Logger log = Logger.getLogger(CustomerService.class.getName());

	public CustomerResponseObject addCustomer(Customer customer) {

		CustomerResponseObject responseObject = new CustomerResponseObject();
		CustomerDTO customerDTO = new CustomerDTO();
		List<CustomerDTO> customerDTOList = new ArrayList<>();
		try {
			log.info("Add Customer Function getting Initiated..!!");
			Customer addedCustomer = customerBO.addCustomer(customer);
			if (addedCustomer != null) {

				customerDTO.setCustomerId(addedCustomer.getCustomerId());
				customerDTO.setCustomerFirstName(addedCustomer.getCustomerFirstName());
				customerDTO.setCustomerLastName(addedCustomer.getCustomerLastName());
				customerDTO.setCustomerAddress(addedCustomer.getCustomerAddress());
				customerDTO.setEmail(addedCustomer.getEmail());
				customerDTO.setGender(addedCustomer.getGender());
				customerDTO.setPhoneNumber(addedCustomer.getPhoneNumber());
				customerDTO.setBankId(addedCustomer.getBank().getBankId());

				customerDTOList.add(customerDTO);

				responseObject.setCustomerDTOList(customerDTOList);

				responseObject.setMessage("Customer Successfully added to the Database!");

				log.info("Customer name " + addedCustomer.getCustomerFirstName() + " "
						+ addedCustomer.getCustomerLastName() + " Successfully added to the database...");
			} else {
				responseObject.setMessage("Failed to add a new Customer to the database!!");
				log.error("Failed to add a new Customer..");
			}
		} catch (Exception e) {
			responseObject.setMessage("Failed to add a new Customer to the database!!");
			log.error("Error Occured while adding new Customer...");
		}
		return responseObject;
	}

	public CustomerResponseObject findById(int id) throws CustomerNotFoundException, CustomerException {
		CustomerResponseObject responseObject = new CustomerResponseObject();

		Customer addedCustomer = new Customer();
		CustomerDTO customerDTO = new CustomerDTO();
		List<CustomerDTO> customerDTOList = new ArrayList<>();

		try {
			log.info("FInd By Id Function Initiated..");
			addedCustomer = customerBO.findById(id);

			customerDTO.setCustomerId(addedCustomer.getCustomerId());
			customerDTO.setCustomerFirstName(addedCustomer.getCustomerFirstName());
			customerDTO.setCustomerLastName(addedCustomer.getCustomerLastName());
			customerDTO.setCustomerAddress(addedCustomer.getCustomerAddress());
			customerDTO.setEmail(addedCustomer.getEmail());
			customerDTO.setGender(addedCustomer.getGender());
			customerDTO.setPhoneNumber(addedCustomer.getPhoneNumber());
			customerDTO.setBankId(addedCustomer.getBank().getBankId());

			customerDTOList.add(customerDTO);
			responseObject.setCustomerDTOList(customerDTOList);

			responseObject.setMessage("Customer id " + id + "  is Successfully fetched from the Database!");

			log.info("Successfully fetched Customer id " + id + " from the database...");
		} catch (Exception e) {
			responseObject.setMessage("Customer id " + id + "  is not found in the Database!");
			log.error(e.getMessage());
		}
		return responseObject;
	}

	public CustomerResponseObject findAllCustomers() {
		CustomerResponseObject responseObject = new CustomerResponseObject();

		List<Customer> listOfCustomers = new ArrayList<>();
		List<CustomerDTO> customersDTOList = new ArrayList<>();

		try {
			log.info("Find all customers function initiated...");
			listOfCustomers = customerBO.findAllCustomers();
			customersDTOList = assignCustomerDTO(listOfCustomers);

			responseObject.setCustomerDTOList(customersDTOList);
			responseObject.setMessage("All Customers Successfully fetched from the Database!");

			log.info("Successfully fetched all customers from the database...");

		} catch (Exception e) {
			responseObject.setMessage(" Failed to fetch all customers from the database..."
					+ "Check whether the data present in the database...");
			log.error(e);
		}

		return responseObject;

	}

	public CustomerResponseObject findByGreaterThanById(int id) throws CustomerNotFoundException, CustomerException {
		CustomerResponseObject responseObject = new CustomerResponseObject();

		List<Customer> listOfCustomers = new ArrayList<>();
		List<CustomerDTO> customerDTOList = new ArrayList<>();

		try {
			log.info("Fetching the id which is greater than give Id function is Initiated!!");
			listOfCustomers = customerBO.findCustomerGreaterThanById(id);

			customerDTOList = assignCustomerDTO(listOfCustomers);

			responseObject.setCustomerDTOList(customerDTOList);

			responseObject
					.setMessage("Customer ID which is greater than the given id " + id + " are fetched successfully!!");
			log.info("Customer Fetched Successfuly");
		} catch (Exception e) {
			responseObject.setMessage("Error occured while fetching Customer Id which is greater than given id: " + id);
		}
		return responseObject;
	}

	public CustomerCustomizedResponse findByCustomerCustomized(String name)
			throws CustomerNotFoundException, CustomerException {
		CustomerCustomizedResponse responseObject = new CustomerCustomizedResponse();
		List<CustomerCustomized> listOfCustomersCustomized = new ArrayList<>();
		List<CustomerCustomizedDTO> customerCustomizedDTOList = new ArrayList<>();

		try {
			log.info("Fetching the id which is greater than give Id function is Initiated!!");
			listOfCustomersCustomized = customerBO.findByCustomerNameCustomized(name);

			for (CustomerCustomized customerCustomized : listOfCustomersCustomized) {
				CustomerCustomizedDTO customerCustomizedDTO = new CustomerCustomizedDTO();
				customerCustomizedDTO.setFirstName(customerCustomized.getName1());
				customerCustomizedDTO.setLastName(customerCustomized.getName2());
				customerCustomizedDTO.setGender(customerCustomized.getGender());

				customerCustomizedDTOList.add(customerCustomizedDTO);

			}
			responseObject.setCustomerCustomizedDTOList(customerCustomizedDTOList);
			responseObject.setMessage("Successfully Customer are fetched By Name Customized!!");
			log.info("Customer Fetched Successfuly");
		} catch (Exception e) {
			responseObject.setMessage("Error occured while fetching Customers..");
		}
		return responseObject;

	}

	public CustomerResponseObject findAllOrderByName() {
		CustomerResponseObject responseObject = new CustomerResponseObject();

		List<Customer> listOfCustomers = new ArrayList<>();
		List<CustomerDTO> customersDTOList = new ArrayList<>();

		try {
			listOfCustomers = customerBO.findAllOrderByName();
			customersDTOList = assignCustomerDTO(listOfCustomers);

			responseObject.setCustomerDTOList(customersDTOList);
			responseObject.setMessage("All Customers Successfully fetched from the Database!");

			log.info("Successfully fetched All Customers from the database...");

		} catch (Exception e) {
			responseObject.setMessage(" Failed to fetch All Customers By Ascending Order from the database...");
			log.error(e);
		}

		return responseObject;

	}

	public CustomerResponseObject findCustomersWithBank() {
		CustomerResponseObject responseObject = new CustomerResponseObject();

		List<Customer> listOfCustomers = new ArrayList<>();
		List<CustomerDTO> customersDTOList = new ArrayList<>();

		try {
			listOfCustomers = customerBO.findCustomerWithBank();

			customersDTOList = assignCustomerDTO(listOfCustomers);

			responseObject.setCustomerDTOList(customersDTOList);

			responseObject.setMessage("All Customers Successfully fetched from the Database!");

			log.info("Successfully fetched All Customers with Bank from the database...");

		} catch (Exception e) {
			responseObject.setMessage(" Failed to fetch All Customers with Bank from the database...");
			log.error(e);
		}

		return responseObject;

	}

	public CustomerBankCustomizedResponse findByCustomerBankCustomized() {
		CustomerBankCustomizedResponse responseObject = new CustomerBankCustomizedResponse();
		List<CustomerBankCustomized> customerBankCustomizedList = new ArrayList<>();
		List<CustomerBankCustomizedDTO> customerBankCustomizedDTOList = new ArrayList<>();

		try {
			log.info("Find customer with bank function initiated.....");
			customerBankCustomizedList = customerBO.findByCustomerBankCustomized();

			for (CustomerBankCustomized customerCustom : customerBankCustomizedList) {
				CustomerBankCustomizedDTO customerBankCustomizedDTO = new CustomerBankCustomizedDTO();
				customerBankCustomizedDTO.setBankName(customerCustom.getBankName());
				customerBankCustomizedDTO.setFirstName(customerCustom.getName1());
				customerBankCustomizedDTO.setLastName(customerCustom.getName2());
				customerBankCustomizedDTO.setGender(customerCustom.getGender());

				customerBankCustomizedDTOList.add(customerBankCustomizedDTO);

			}
			responseObject.setCustomerBankCustomizedDTOList(customerBankCustomizedDTOList);
			responseObject.setMessage(" Customers fetched Successfully With Banks!!");

			log.info("Customers fetched Successfulliy !!");

		} catch (Exception e) {
			responseObject.setMessage("Error Occured while fetching Customers with Bank!");
			log.error(e);
		}
		return responseObject;
	}

	public List<CustomerDTO> assignCustomerDTO(List<Customer> listOfCustomers) {
		List<CustomerDTO> customerDTOList = new ArrayList<>();

		for (Customer customer : listOfCustomers) {

			CustomerDTO customerDTO = new CustomerDTO();
			customerDTO.setCustomerId(customer.getCustomerId());
			customerDTO.setBankId(customer.getBank().getBankId());
			customerDTO.setCustomerFirstName(customer.getCustomerFirstName());
			customerDTO.setCustomerLastName(customer.getCustomerLastName());
			customerDTO.setCustomerAddress(customer.getCustomerAddress());
			customerDTO.setEmail(customer.getEmail());
			customerDTO.setGender(customer.getGender());
			customerDTO.setPhoneNumber(customer.getPhoneNumber());

			customerDTOList.add(customerDTO);
		}
		return customerDTOList;
	}
}
