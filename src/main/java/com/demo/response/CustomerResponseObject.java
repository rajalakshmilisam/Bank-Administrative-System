package com.demo.response;

import java.util.List;

import com.demo.dto.CustomerDTO;

public class CustomerResponseObject {

	private String message;

	private List<CustomerDTO> customerDTOList;

	public String getMessage() {
		return message;
	}

	public CustomerResponseObject() {
		super();
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<CustomerDTO> getCustomerDTOList() {
		return customerDTOList;
	}

	public void setCustomerDTOList(List<CustomerDTO> customerDTOList) {
		this.customerDTOList = customerDTOList;
	}

}
