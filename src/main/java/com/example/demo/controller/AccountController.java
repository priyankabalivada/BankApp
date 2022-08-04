package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Exception.AccountAlreadyexistException;

import com.example.demo.Exception.NotFoundException;
import com.example.demo.Repo.BankRepository;
import com.example.demo.dao.Account;
import com.example.demo.dao.Transfer;
import com.example.demo.service.AccountService;
@RestController
public class AccountController {
	
	
	@Autowired
	AccountService accountService;
	
	
	
	@PostMapping("/accounts/{id}")
	public ResponseEntity<Account> PostAccount(@PathVariable Integer id,@RequestBody Account Account)
	{
		Account savedAccount=accountService.createAccount (id,Account); 	
		return new ResponseEntity<>(savedAccount,HttpStatus.CREATED);	
	}
	
	
	@GetMapping("/accounts/{AccountNumber}")
	public ResponseEntity<Account> getAccount (@PathVariable int AccountNumber)  { 
		Account Account=accountService.getAccount(AccountNumber); 
		return new ResponseEntity<Account>(Account,HttpStatus.OK);
	}
	
	@GetMapping ("/api/accounts")
	public ResponseEntity<List<Account>> getAccounts() { 
		List<Account> Accounts=accountService.getAccounts();
		return new ResponseEntity<List<Account>>(Accounts,HttpStatus.OK);

	}
	 
	
	@PutMapping("/transfer")
	public ResponseEntity<Object> transfer(@RequestBody Transfer transfer) throws AccountAlreadyexistException
	{
		
		accountService.transferMoney(transfer.getFromAccount(),transfer.getToAccount(),transfer.getAmount());
		return new ResponseEntity<>("Transaction is success",HttpStatus.OK);
	}
	
}
