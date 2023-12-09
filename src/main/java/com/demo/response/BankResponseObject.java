package com.demo.response;

import java.util.List;

import com.demo.dto.BankDTO;

public class BankResponseObject {

	private String message;

	private List<BankDTO> listOfBanksDTO;
	
	public BankResponseObject() {
		super();
	}

	public List<BankDTO> getListOfBanksDTO() {
		return listOfBanksDTO;
	}

	public void setListOfBanksDTO(List<BankDTO> listOfBanksDTO) {
		this.listOfBanksDTO = listOfBanksDTO;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


}
