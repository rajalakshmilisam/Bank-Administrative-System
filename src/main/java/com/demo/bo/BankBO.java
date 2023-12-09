package com.demo.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.dao.BankCustomerCustomized;
import com.demo.dao.BankCustomized;
import com.demo.dao.BankRepository;
import com.demo.entity.Bank;
import com.demo.exception.BankException;
import com.demo.exception.BankNotFoundException;


@Component
public class BankBO {
	@Autowired
	private BankRepository bankRepository;

	public Bank addBank(Bank bank) throws BankException {

		Bank addedBank = new Bank();

		String emailPattern = "^\\S+@gmail\\.com$";
		boolean isEmailValid = false;
		String phoneNumberPattern = "\\d{10}";
		boolean isContactNumberValid = false;

		isEmailValid = bank.getEmail().matches(emailPattern) ? true : false;
		isContactNumberValid = bank.getContactNumber().matches(phoneNumberPattern) ? true : false;

		if (isEmailValid == true && isContactNumberValid == true) {

			addedBank = bankRepository.save(bank);

		} else {
			throw new BankException("This Email or Phone Number are not valid, Kindly check!!");
		}
		return addedBank;
	}

	public Bank updateBank(int bankId, Bank bank) throws BankException {
		
		Bank existingBank = new Bank();

		String emailPattern = "^\\S+@gmail\\.com$";
		boolean isEmailValid = false;
		String phoneNumberPattern = "\\d{10}";
		boolean isContactNumberValid = false;

		isEmailValid = bank.getEmail().matches(emailPattern);
		isContactNumberValid = bank.getContactNumber().matches(phoneNumberPattern);

		if (isEmailValid == true && isContactNumberValid == true) {

			existingBank = bankRepository.findById(bankId).orElse(null);

		} else {
			throw new BankException("Invalid email or contact number format.");
		}
		return existingBank;
	}

	public Bank findById(int id) throws BankNotFoundException {
		Bank bank = bankRepository.findById(id).orElse(null);
		if (bank == null) {
			throw new BankNotFoundException(id + " is not found in the database.");
		}
		return bank;

	}

	public List<Bank> findAllBanks() throws BankNotFoundException {
		List<Bank> banks = new ArrayList<>();
		banks = bankRepository.findAll();
		if (banks.isEmpty()) {
			throw new BankNotFoundException("No Banks found in the database.");
		}
		return banks;
	}

	public List<Bank> findBankGreaterThanById(int id) throws BankNotFoundException {
		List<Bank> lisOfBanks = new ArrayList<>();
		lisOfBanks = bankRepository.findBankByGreaterThanById(id);
		if (lisOfBanks.isEmpty()) {
			throw new BankNotFoundException(
					"No Banks are found in the database which are greater than the given id " + id);
		}
		return lisOfBanks;
	}

	public List<Bank> findBankByName(String name) throws BankNotFoundException {
		List<Bank> lisOfBanks = new ArrayList<>();
		lisOfBanks = bankRepository.findByBankName(name);
		if (lisOfBanks.isEmpty()) {
			throw new BankNotFoundException("No Banks found in the database.");
		}
		return lisOfBanks;
	}

	public List<BankCustomized> findByBankNameCustomized(String name) throws BankNotFoundException {
		List<BankCustomized> listOfCustomizedBanks = new ArrayList<>();
		listOfCustomizedBanks = bankRepository.findByBankNameCustomized(name);
		if (listOfCustomizedBanks.isEmpty()) {
			throw new BankNotFoundException("No Banks found in the database.");
		}
		return listOfCustomizedBanks;
	}

	public List<Bank> findAllOrderByName() throws BankNotFoundException {
		List<Bank> lisOfBanks = new ArrayList<>();
		lisOfBanks = bankRepository.findAllOrderByNameDescending();
		if (lisOfBanks.isEmpty()) {
			throw new BankNotFoundException("No Banks found in the database.");
		}
		return lisOfBanks;
	}

	public List<Bank> findBankWithCustomersInnerJoin() throws BankNotFoundException {
		List<Bank> lisOfBanks = new ArrayList<>();
		lisOfBanks = bankRepository.findBankWithCustomersInnerJoin();
		if (lisOfBanks.isEmpty()) {
			throw new BankNotFoundException("No Banks found in the database.");
		}
		return lisOfBanks;
	}

	public List<Bank> findBankWithCustomersLeftJoin() throws BankNotFoundException {
		List<Bank> lisOfBanks = new ArrayList<>();
		lisOfBanks = bankRepository.findBankWithCustomersLeftJoin();
		if (lisOfBanks.isEmpty()) {
			throw new BankNotFoundException("No Banks found in the database.");
		}
		return lisOfBanks;
	}

	public List<BankCustomerCustomized> findByBankCustomerCustomized() throws BankNotFoundException {
		List<BankCustomerCustomized> lisOfBanks = new ArrayList<>();
		lisOfBanks = bankRepository.findByBankCustomerCustomized();
		if (lisOfBanks.isEmpty()) {
			throw new BankNotFoundException("No bank found in the database..!!");
		}
		return lisOfBanks;
	}
}
