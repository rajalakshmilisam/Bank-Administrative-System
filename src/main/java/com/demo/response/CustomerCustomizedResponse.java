package com.demo.response;

import java.util.List;

import com.demo.dto.CustomerCustomizedDTO;

public class CustomerCustomizedResponse {
	private String message;

	private List<CustomerCustomizedDTO> customerCustomizedDTOList;

	public List<CustomerCustomizedDTO> getCustomerCustomizedDTOList() {
		return customerCustomizedDTOList;
	}

	public void setCustomerCustomizedDTOList(List<CustomerCustomizedDTO> customerCustomizedDTOList) {
		this.customerCustomizedDTOList = customerCustomizedDTOList;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
