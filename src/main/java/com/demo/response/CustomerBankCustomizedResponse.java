package com.demo.response;

import java.util.List;

import com.demo.dto.CustomerBankCustomizedDTO;

public class CustomerBankCustomizedResponse {
	private String message;
	private List<CustomerBankCustomizedDTO> customerBankCustomizedDTOList;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<CustomerBankCustomizedDTO> getCustomerBankCustomizedDTOList() {
		return customerBankCustomizedDTOList;
	}

	public void setCustomerBankCustomizedDTOList(List<CustomerBankCustomizedDTO> customerBankCustomizedDTOList) {
		this.customerBankCustomizedDTOList = customerBankCustomizedDTOList;
	}

}
