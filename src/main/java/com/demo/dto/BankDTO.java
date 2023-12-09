package com.demo.dto;

import java.util.List;

public class BankDTO {
	private int bankId;

	private String bankName;

	private String email;

	private String bankAddress;

	private String contactNumber;

	private List<CustomerDTO> customerDTOList;

	public List<CustomerDTO> getCustomerDTOList() {
		return customerDTOList;
	}

	public void setCustomerDTOList(List<CustomerDTO> customerDTOList) {
		this.customerDTOList = customerDTOList;
	}

	public int getBankId() {
		return bankId;
	}

	public void setBankId(int bankId) {
		this.bankId = bankId;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBankAddress() {
		return bankAddress;
	}

	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}

	@Override
	public String toString() {
		return "BankDTO [bankId=" + bankId + ", bankName=" + bankName + ", email=" + email + ", bankAddress="
				+ bankAddress + ", contactNumber=" + contactNumber + "]";
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

}
