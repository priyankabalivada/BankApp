package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import javax.security.auth.login.AccountNotFoundException;

import com.example.demo.Exception.NotFoundException;
import com.example.demo.dao.Account;

public interface AccountService {

	

	Account getAccount(int AccountNumber)throws NotFoundException ;

	List<Account> getAccounts();

	//Optional<Account> updateAccount(int AccountNumber, Account Accounts) throws AccountNotFoundException;

	Account createAccount(int id, Account Account);

	Account transferMoney(int acc, int desacc, int amount);

	


}
