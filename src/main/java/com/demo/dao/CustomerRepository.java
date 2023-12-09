package com.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.entity.Customer;
 
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	@Query("SELECT c FROM Customer c WHERE c.customerId > :id")
	List<Customer> findCustomerByGreaterThanById(@Param("id") int customerId);

	// Fetches few columns
	@Query("SELECT c.customerFirstName as name1,c.customerLastName as name2, c.gender as gender FROM Customer c WHERE c.customerFirstName = :name")
	List<CustomerCustomized> findByCustomerNameCustomized(@Param("name") String customerFirstName);

	// NamedQuery
	List<Customer> findAllOrderByNameAscending();

	// Inner Join
	@Query("SELECT c FROM Customer c JOIN c.bank b ")
	List<Customer> findCustomersWithBank();

	// Customized data by join
	@Query("SELECT c.customerFirstName AS name1, c.customerLastName AS name2, c.gender AS gender, b.bankName AS bankName FROM Customer c JOIN c.bank b")
	List<CustomerBankCustomized> findByCustomerBankCustomized();

}
