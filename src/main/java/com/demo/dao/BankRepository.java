package com.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.entity.Bank;

@Repository
public interface BankRepository extends JpaRepository<Bank, Integer> {
	@Query("SELECT b FROM Bank b WHERE b.bankId > :id")
	List<Bank> findBankByGreaterThanById(@Param("id") int bankId);

	@Query("SELECT b FROM Bank b WHERE b.bankName LIKE %:name%")
	List<Bank> findByBankName(@Param("name") String bankName);

	// Fetches few columns
	@Query("SELECT b.email as email,b.bankName as bankName FROM Bank b WHERE b.bankName = :name")
	List<BankCustomized> findByBankNameCustomized(@Param("name") String bankName);

	// NamedQuery
	List<Bank> findAllOrderByNameDescending();

	// Inner Join
	@Query("SELECT b FROM Bank b JOIN b.customers c")
	List<Bank> findBankWithCustomersInnerJoin();

	// LeftOuterJoin    
	@Query("SELECT b FROM Bank b LEFT OUTER JOIN b.customers c")
	List<Bank> findBankWithCustomersLeftJoin();

	// Customized data by join
	@Query("SELECT b.bankName as bankName,b.bankAddress as bankAddress,c.customerFirstName "
			+ "as customerName, c.gender as customerGender  FROM Bank b Join b.customers c")
	List<BankCustomerCustomized> findByBankCustomerCustomized();
	
}

