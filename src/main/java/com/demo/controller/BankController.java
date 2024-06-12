package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.entity.Bank;
import com.demo.response.BankCustomerResponseObject;
import com.demo.response.BankCustomizedResponse;
import com.demo.response.BankResponseObject;
import com.demo.service.BankService;

@RestController
@RequestMapping("/bank")
@CrossOrigin("http://localhost:3000")
public class BankController {

	@Autowired
	private BankService bankService;

	@GetMapping("/hello")
	public String greetings(){
		return "WELCOME TO THE LOVE BANK";
	}

	@PostMapping("/addBank")

	public BankResponseObject addBank(@RequestBody Bank bank) {

		BankResponseObject responseObject = new BankResponseObject();

		responseObject = bankService.addBank(bank);

		return responseObject;
	}

	@GetMapping("/fetchById/{id}")
	public BankResponseObject fetchById(@PathVariable int id) {
		BankResponseObject responseObject = new BankResponseObject();

		responseObject = bankService.findById(id);

		return responseObject;
	}

	@PutMapping("/updateBank/{id}")
	public BankResponseObject updateBank(@RequestBody Bank bank,@PathVariable int id) {

		BankResponseObject responseObject = new BankResponseObject();

		responseObject = bankService.updateBank(id,bank);

		return responseObject;
	}

	@GetMapping("/fetchAllBanks")
	public BankResponseObject fetchAllBanks() {
		BankResponseObject responseObject = new BankResponseObject();

		responseObject = bankService.findAllBanks();

		return responseObject;
	}

	@GetMapping("/fetchBankGreaterThanById/{id}")
	public BankResponseObject fetchBankGreaterThanById(@PathVariable int id) {
		BankResponseObject responseObject = new BankResponseObject();

		responseObject = bankService.findBankGreaterThanById(id);

		return responseObject;
	}

	@GetMapping("/findBankByName")
	public BankResponseObject findBankByName(@RequestParam String name) {
		BankResponseObject responseObject = new BankResponseObject();

		responseObject = bankService.findBankByName(name);

		return responseObject;
	}

	@GetMapping("/findBankByNameCustomized")
	public BankCustomizedResponse findBankByNameCustomized(@RequestParam String name) {
		BankCustomizedResponse responseObject = new BankCustomizedResponse();

		responseObject = bankService.findByBankNameCustomized(name);

		return responseObject;
	}

	@GetMapping("/findAllOrderByName")
	public BankResponseObject findAllOrderByName() {
		BankResponseObject responseObject = new BankResponseObject();

		responseObject = bankService.findAllOrderByName();

		return responseObject;
	}

	@GetMapping("/findBankWithCustomersInnerJoin")
	public BankResponseObject findBankWithCustomersInnerJoin() {
		BankResponseObject responseObject = new BankResponseObject();

		responseObject = bankService.findBankWithCustomersInnerJoin();

		return responseObject;
	}

	@GetMapping("/findBankWithCustomersLeftJoin")
	public BankResponseObject findBankWithCustomersLeftJoin() {
		BankResponseObject responseObject = new BankResponseObject();

		responseObject = bankService.findBankWithCustomersLeftJoin();

		return responseObject;
	}

	@GetMapping("/findByBankCustomerCustomized")
	public BankCustomerResponseObject findByBankCustomerCustomized() {
		BankCustomerResponseObject responseObject = new BankCustomerResponseObject();

		responseObject = bankService.findByBankCustomerCustomized();

		return responseObject;
	}

}
