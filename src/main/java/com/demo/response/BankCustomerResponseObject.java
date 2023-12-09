package com.demo.response;

import java.util.List;

import com.demo.dto.BankCustomerCustomizedDTO;

public class BankCustomerResponseObject {
	private String message;

	private List<BankCustomerCustomizedDTO> bankCustomerCustomizedDTOList;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<BankCustomerCustomizedDTO> getBankCustomerCustomizedDTOList() {
		return bankCustomerCustomizedDTOList;
	}

	public void setBankCustomerCustomizedDTOList(List<BankCustomerCustomizedDTO> bankCustomerCustomizedDTOList) {
		this.bankCustomerCustomizedDTOList = bankCustomerCustomizedDTOList;
	}

}
