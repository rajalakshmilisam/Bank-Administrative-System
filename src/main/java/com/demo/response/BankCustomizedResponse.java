package com.demo.response;

import java.util.List;

import com.demo.dto.BankCustomizedDTO;

public class BankCustomizedResponse {
	private String message;

	private List<BankCustomizedDTO> bankCustomizedDTOList;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<BankCustomizedDTO> getBankCustomizedDTOList() {
		return bankCustomizedDTOList;
	}

	public void setBankCustomizedDTOList(List<BankCustomizedDTO> bankCustomizedDTOList) {
		this.bankCustomizedDTOList = bankCustomizedDTOList;
	}

}
