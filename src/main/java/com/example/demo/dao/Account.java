package com.example.demo.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="AccountData")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "accountNumber")
	int accountNumber;
	@Column(name = "accountType")
	@NotNull(message="cannot be null")
	@Size(min=1, message="cannot be blank")
	String accountType;
	@Column(name = "balance")
	@NotNull(message="cannot be null")
	int balance;
	public int getAccountNumber() {
		return accountNumber;
	}
	@Override
	public String toString() {
		return "Account [accountNumber=" + accountNumber + ", accountType=" + accountType + ", balance=" + balance
				+ "]";
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public Account(String accountType, int balance) {
		super();
		this.accountType = accountType;
		this.balance = balance;
	}
	
	public Account() {
		super();
	}
	
	
	public Account(int accountNumber,
			@NotNull(message = "cannot be null") @Size(min = 1, message = "cannot be blank") String accountType,
			@NotNull(message = "cannot be null") int balance) {
		super();
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.balance = balance;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
}
