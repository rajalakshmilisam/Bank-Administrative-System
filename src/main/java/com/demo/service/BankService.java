package com.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.demo.bo.BankBO;
import com.demo.dao.BankCustomerCustomized;
import com.demo.dao.BankCustomized;
import com.demo.dao.BankRepository;
import com.demo.dao.CustomerRepository;
import com.demo.dto.BankCustomerCustomizedDTO;
import com.demo.dto.BankCustomizedDTO;
import com.demo.dto.BankDTO;
import com.demo.dto.CustomerDTO;
import com.demo.entity.Bank;
import com.demo.entity.Customer;
import com.demo.response.BankCustomerResponseObject;
import com.demo.response.BankCustomizedResponse;
import com.demo.response.BankResponseObject;

@Service
public class BankService {
	@Autowired // Only for Transaction
	private BankRepository bankRepository;
	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private BankBO bankBO;

	Logger log = Logger.getLogger(BankService.class.getName());

	public BankResponseObject addBank(Bank bank) {

		BankResponseObject responseObject = new BankResponseObject();
		BankDTO bankDTO = new BankDTO();
		List<BankDTO> bankDTOList = new ArrayList<>();
		try {
			log.info("Add bank function getting initiated...!!");
			Bank addedBank = bankBO.addBank(bank);
			if (addedBank != null) {

				bankDTO.setBankId(addedBank.getBankId());
				bankDTO.setBankName(addedBank.getBankName());
				bankDTO.setBankAddress(addedBank.getBankAddress());
				bankDTO.setContactNumber(addedBank.getContactNumber());
				bankDTO.setEmail(addedBank.getEmail());

				bankDTOList.add(bankDTO);

				responseObject.setListOfBanksDTO(bankDTOList);

				responseObject.setMessage(
						"Bank name - " + addedBank.getBankName() + " is successfully added to the database..!!");

				log.info(addedBank.getBankName() + " is successfully addedd to the database..");
			} else {
				responseObject.setMessage("Failed to add new bank to the database!!");
				log.error("Failed to add a new bank...Please Contact Admin!!");
			}
		} catch (Exception e) {
			responseObject
					.setMessage("Failed to add a new bank in the database. Please check your Email and Phone Number");
			log.error(e);
		}
		return responseObject;
	}

	public BankResponseObject findById(int id) {
		BankResponseObject responseObject = new BankResponseObject();
		Bank addedBank = new Bank();
		BankDTO bankDTO = new BankDTO();
		List<BankDTO> bankDTOList = new ArrayList<>();
		try {
			log.info("Findbyid function initiated..");
			addedBank = bankBO.findById(id);

			bankDTO.setBankId(addedBank.getBankId());
			bankDTO.setBankName(addedBank.getBankName());
			bankDTO.setBankAddress(addedBank.getBankAddress());
			bankDTO.setContactNumber(addedBank.getContactNumber());
			bankDTO.setEmail(addedBank.getEmail());

			bankDTOList.add(bankDTO);

			responseObject.setListOfBanksDTO(bankDTOList);

			responseObject.setMessage("Bank id " + id + " is fetched successfully !!");

			log.info(id + " is successfulliy fetched!!");

		} catch (Exception e) {
			responseObject.setMessage(id + " is not exits in the database..Please give a valid id to fetch!!");
			log.error(e);
		}
		return responseObject;
	}

	public BankResponseObject findAllBanks() {
		BankResponseObject responseObject = new BankResponseObject();

		List<Bank> listOfBanks = new ArrayList<>();
		List<BankDTO> bankDTOList = new ArrayList<>();
		try {
			log.info("findAll banks function initiated...");
			listOfBanks = bankBO.findAllBanks();
			// bankDTOList = assignBankDTO(listOfBanks);

			for (Bank bank : listOfBanks) {

				BankDTO bankDTO = new BankDTO();

				bankDTO.setBankId(bank.getBankId());
				bankDTO.setBankName(bank.getBankName());
				bankDTO.setBankAddress(bank.getBankAddress());
				bankDTO.setContactNumber(bank.getContactNumber());
				bankDTO.setEmail(bank.getEmail());

				bankDTOList.add(bankDTO);
			}

			responseObject.setListOfBanksDTO(bankDTOList);

			responseObject.setMessage("All Banks fetched Successfully !!");

			log.info("Banks are fetched Successfulliy !!");

		} catch (Exception e) {
			responseObject.setMessage("Error Occured while fetching List of Bank");
			log.error(e);
		}
		return responseObject;
	}

	public BankResponseObject findBankGreaterThanById(int id) {
		BankResponseObject responseObject = new BankResponseObject();

		List<Bank> listOfBanks = new ArrayList<>();
		List<BankDTO> bankDTOList = new ArrayList<>();

		try {
			log.info("Fetching the bankid which is greater than give id function is initiated...!!");
			listOfBanks = bankBO.findBankGreaterThanById(id);
			bankDTOList = assignBankDTO(listOfBanks);

			responseObject.setListOfBanksDTO(bankDTOList);

			responseObject
					.setMessage("bankId which are greater than the given id " + id + " are fetched successfully!!");
			log.info("List of banks fetched successfuly");
		} catch (Exception e) {
			responseObject.setMessage("Error occured while fetching bankid with greater than given id: " + id);
			log.error(e);
		}
		return responseObject;
	}

	public BankResponseObject findBankByName(String name) {
		BankResponseObject responseObject = new BankResponseObject();
		List<Bank> listOfBanks = new ArrayList<>();
		List<BankDTO> bankDTOList = new ArrayList<>();

		try {
			log.info("Fetching bank by name function is Initiated...!!");

			listOfBanks = bankBO.findBankByName(name);
			bankDTOList = assignBankDTO(listOfBanks);

			responseObject.setListOfBanksDTO(bankDTOList);
			responseObject.setMessage("Successfully Banks are fetched by name: " + name);
			log.info("Bank Fetched Successfuly");
		} catch (Exception e) {
			responseObject.setMessage("Error occured while fetching Bank By Name..");
			log.error(e);
		}
		return responseObject;
	}

	public BankCustomizedResponse findByBankNameCustomized(String name) {
		BankCustomizedResponse responseObject = new BankCustomizedResponse();
		List<BankCustomized> listOfCustomizedBanks = new ArrayList<>();
		List<BankCustomizedDTO> bankCustomizedDTOList = new ArrayList<>();

		try {
			log.info("Fetching Bank By Name function is Initiated!!");
			listOfCustomizedBanks = bankBO.findByBankNameCustomized(name);
			log.info("Successfully fetched Banks By Name..!");
			for (BankCustomized bankCustomized : listOfCustomizedBanks) {

				BankCustomizedDTO bankCustomizedDTO = new BankCustomizedDTO();

				bankCustomizedDTO.setBankName(bankCustomized.getbankName());
				bankCustomizedDTO.setEmail(bankCustomized.getEmail());

				bankCustomizedDTOList.add(bankCustomizedDTO);
			}

			responseObject.setBankCustomizedDTOList(bankCustomizedDTOList);
			responseObject.setMessage("Successfully Banks are fetched By Name Customized!!");
			log.info("Bank Fetched Successfuly");
		} catch (Exception e) {
			responseObject.setMessage("Error occured while fetching Bank By Name Customized..");
			log.error(e);
		}
		return responseObject;
	}

	public BankResponseObject findAllOrderByName() {
		BankResponseObject responseObject = new BankResponseObject();

		List<Bank> listOfBanks = new ArrayList<>();
		List<BankDTO> bankDTOList = new ArrayList<>();
		try {
			log.info("Find all banks in descending order function Initiated..");
			listOfBanks = bankBO.findAllOrderByName();
			bankDTOList = assignBankDTO(listOfBanks);

			responseObject.setListOfBanksDTO(bankDTOList);

			responseObject.setMessage("All Banks fetched Successfully By Decending Order!!");

			log.info("Banks fetched Successfulliy !!");

		} catch (Exception e) {
			responseObject.setMessage("Error Occured while fetching Bank By Descending");
			log.error(e);
		}
		return responseObject;
	}

	public BankResponseObject findBankWithCustomersInnerJoin() {
		BankResponseObject responseObject = new BankResponseObject();

		List<Bank> listOfBanks = new ArrayList<>();
		List<BankDTO> bankDTOList = new ArrayList<>();
		try {
			log.info("Find Bank with customers function initiated..");

			listOfBanks = bankBO.findBankWithCustomersInnerJoin();

			for (Bank bank : listOfBanks) {

				BankDTO bankDTO = new BankDTO();

				bankDTO.setBankId(bank.getBankId());
				bankDTO.setBankName(bank.getBankName());
				bankDTO.setBankAddress(bank.getBankAddress());
				bankDTO.setContactNumber(bank.getContactNumber());
				bankDTO.setEmail(bank.getEmail());

				List<CustomerDTO> customerDTOList = new ArrayList<>();

				for (Customer customer : bank.getCustomers()) {

					CustomerDTO customerDTO = new CustomerDTO();

					customerDTO.setBankId(customer.getBank().getBankId());
					customerDTO.setCustomerFirstName(customer.getCustomerFirstName());
					customerDTO.setCustomerLastName(customer.getCustomerLastName());
					customerDTO.setCustomerAddress(customer.getCustomerAddress());
					customerDTO.setEmail(customer.getEmail());
					customerDTO.setPhoneNumber(customer.getPhoneNumber());
					customerDTO.setGender(customer.getGender());
					customerDTO.setCustomerId(customer.getCustomerId());

					customerDTOList.add(customerDTO);
				}
				bankDTO.setCustomerDTOList(customerDTOList);
				bankDTOList.add(bankDTO);
			}

			responseObject.setListOfBanksDTO(bankDTOList);

			responseObject.setMessage("All Banks fetched Successfully With Customers using inner join!!");

			log.info("Banks fetched Successfully !!");

		} catch (Exception e) {
			responseObject.setMessage("Error Occured while fetching Bank with Customers");
			log.error(e);
		}
		return responseObject;
	}

	public BankResponseObject findBankWithCustomersLeftJoin() {
		BankResponseObject responseObject = new BankResponseObject();
		List<Bank> listOfBanks = new ArrayList<>();
		try {
			log.info("Find Bank with Customers Function Initiated...");
			listOfBanks = bankBO.findBankWithCustomersLeftJoin();
			List<BankDTO> bankDTOList = new ArrayList<>();

			for (Bank bank : listOfBanks) {
				BankDTO bankDTO = new BankDTO();
				bankDTO.setBankId(bank.getBankId());
				bankDTO.setBankName(bank.getBankName());
				bankDTO.setBankAddress(bank.getBankAddress());
				bankDTO.setContactNumber(bank.getContactNumber());
				bankDTO.setEmail(bank.getEmail());

				List<CustomerDTO> customerDTOList = new ArrayList<>();

				for (Customer customer : bank.getCustomers()) {

					CustomerDTO customerDTO = new CustomerDTO();
					customerDTO.setCustomerFirstName(customer.getCustomerFirstName());
					customerDTO.setCustomerLastName(customer.getCustomerLastName());
					customerDTO.setCustomerAddress(customer.getCustomerAddress());
					customerDTO.setCustomerId(customer.getCustomerId());
					customerDTO.setGender(customer.getGender());
					customerDTO.setPhoneNumber(customer.getPhoneNumber());
					customerDTO.setEmail(customer.getEmail());

					customerDTOList.add(customerDTO);
				}

				bankDTO.setCustomerDTOList(customerDTOList);
				bankDTOList.add(bankDTO);
			}

			responseObject.setListOfBanksDTO(bankDTOList);
			responseObject.setMessage("Successfully fetched Bank with Customers using left join..");
			log.info("Successfully fetched data from the database using left join..");
		} catch (Exception e) {
			responseObject.setMessage("Failed to load data from database..");
			log.error(e);
		}
		return responseObject;
	}

	public BankCustomerResponseObject findByBankCustomerCustomized() {
		BankCustomerResponseObject responseObject = new BankCustomerResponseObject();

		List<BankCustomerCustomized> listOfBanksCustomerCustomized = new ArrayList<>();
		List<BankCustomerCustomizedDTO> BankCustomerCustomizedDTOList = new ArrayList<>();
		try {
			log.info("Finding Banks with Customers function Initiated...!");
			listOfBanksCustomerCustomized = bankBO.findByBankCustomerCustomized();

			for (BankCustomerCustomized bankCustomerCustom : listOfBanksCustomerCustomized) {

				BankCustomerCustomizedDTO bankCustomerCustomizedDTO = new BankCustomerCustomizedDTO();

				bankCustomerCustomizedDTO.setBankAddress(bankCustomerCustom.getBankAddress());
				bankCustomerCustomizedDTO.setBankName(bankCustomerCustom.getBankName());
				bankCustomerCustomizedDTO.setCustomerGender(bankCustomerCustom.getCustomerGender());
				bankCustomerCustomizedDTO.setCustomerName(bankCustomerCustom.getCustomerName());

				BankCustomerCustomizedDTOList.add(bankCustomerCustomizedDTO);
			}
			responseObject.setBankCustomerCustomizedDTOList(BankCustomerCustomizedDTOList);
			responseObject.setMessage(" Banks fetched Successfully With Customers!!");

			log.info("Banks fetched Successfulliy !!");

		} catch (Exception e) {
			responseObject.setMessage("Error Occured while fetching Bank with Customers!");
			log.error(e);
		}
		return responseObject;
	}

	public List<BankDTO> assignBankDTO(List<Bank> listOfBanks) {
		List<BankDTO> bankDTOList = new ArrayList<>();

		for (Bank bank : listOfBanks) {

			BankDTO bankDTO = new BankDTO();

			bankDTO.setBankId(bank.getBankId());
			bankDTO.setBankName(bank.getBankName());
			bankDTO.setBankAddress(bank.getBankAddress());
			bankDTO.setContactNumber(bank.getContactNumber());
			bankDTO.setEmail(bank.getEmail());

			bankDTOList.add(bankDTO);
		}
		return bankDTOList;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void transactiondemo(Bank bank1, Customer customer1) { // Monolithic transaction
		System.out.println("Bank add...before");
		bankRepository.save(bank1);
		System.out.println("Bank add...after");
		System.out.println("Customer add...before");
		customerRepository.save(customer1);
		System.out.println("Customer add...after");
	}

	public BankResponseObject updateBank(int bankId, Bank bank) {
	    BankResponseObject responseObject = new BankResponseObject();
	    BankDTO bankDTO = new BankDTO();
		List<BankDTO> bankDTOList = new ArrayList<>();
	    try {
	        log.info("Update bank function getting initiated...!!");
	       
	        Bank existingBank = bankBO.updateBank(bankId, bank);
	        
	        if (existingBank != null) {
	            
	            existingBank.setBankName(bank.getBankName());
	            existingBank.setBankAddress(bank.getBankAddress());
	            existingBank.setContactNumber(bank.getContactNumber());
	            existingBank.setEmail(bank.getEmail());
	            
	         
	            Bank updatedBank = bankRepository.save(existingBank);
	           
	            bankDTO.setBankId(updatedBank.getBankId());
	            bankDTO.setBankName(updatedBank.getBankName());
	            bankDTO.setBankAddress(updatedBank.getBankAddress());
	            bankDTO.setContactNumber(updatedBank.getContactNumber());
	            bankDTO.setEmail(updatedBank.getEmail());
	            
	            bankDTOList.add(bankDTO);
	            responseObject.setListOfBanksDTO(bankDTOList);
	            
	            responseObject.setMessage("Bank name - " + updatedBank.getBankName() + " is successfully updated.");
	            
	            log.info(updatedBank.getBankName() + " is successfully updated in the database.");
	        } else {
	            responseObject.setMessage("Bank with ID " + bankId + " does not exist.");
	            log.error("Bank with ID " + bankId + " does not exist.");
	        }
	    } catch (Exception e) {
	        responseObject.setMessage("Failed to update the bank in the database. Please check Email or Phone number..");
	        log.error("Error occurred: ", e);
	    }
	    
	    return responseObject;
	}

}
