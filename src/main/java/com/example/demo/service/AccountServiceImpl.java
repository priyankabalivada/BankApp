package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Exception.BalanceExceedLimitException;
import com.example.demo.Exception.NegativeBalanceException;
import com.example.demo.Exception.NotFoundException;
import com.example.demo.Exception.SameAccountexception;
import com.example.demo.Repo.AccountRepository;
import com.example.demo.Repo.BankRepository;
import com.example.demo.dao.Account;


@Service
public class AccountServiceImpl implements AccountService{

@Autowired
AccountRepository accountRepository;

@Autowired
BankRepository bankRepository;




@Override
public Account createAccount(int id,Account Account){
if(!bankRepository.existsById(id)) {
	throw new NotFoundException("Account,customer with id->"+id+" not found.");

}
Account Account1 = accountRepository.save(Account); 
return Account1;
}

@Override
public Account  getAccount(int AccountNumber) throws NotFoundException {	
	Optional<Account> Account = accountRepository.findById(AccountNumber); //row
	if(!Account.isPresent()) {
		throw new NotFoundException("Account with id->"+AccountNumber+" not found."); //row not present
	}
  Account acc = Account.get();
  if(acc==null)
  {
		throw new NotFoundException("Account with id->"+AccountNumber+" not found.");	  
  }
	return acc;
}

@Override
public List<Account> getAccounts() {
	  List<Account> Accounts = new ArrayList<Account>();  
	  accountRepository.findAll().forEach(Account -> Accounts.add(Account));  
	  System.out.println("QQQQQQQQQQQjhjhjhjhghfnghvghfg");
	  return Accounts;  
}


/*@Override
public Optional<Account> updateAccount(int AccountNumber, Account Accounts) throws AccountNotFoundException {
	//Account c = null;
	Optional<Account> cl = b.findById(AccountNumber);
	
	 cl.get();
	
	if(cl.isEmpty()) {
		throw new AccountNotFoundException("Account with id->"+AccountNumber+" not found.");
	}
	 Account cust=cl.get();
	 if(cust==null)
	 {
		 throw new NotFoundException("customer with id->"+AccountNumber+" not found.");
	 }
	 else {
	cust.setAccountType(Accounts.getAccountType());
	cust.setBalance(Accounts.getBalance());
	System.out.println(Accounts.getAccountNumber()+";;;;;;;;;;;;;;;;;;;;;;");
    Account save = b.save(cust);
	 return Optional.ofNullable(save);
}}*/


@Override	
public Account transferMoney(int fromAccId,int toAccId,int amount)

{
	 Optional<Account> fromAcc=accountRepository.findById(fromAccId);
	 Optional<Account> toAcc=accountRepository.findById(toAccId);
	 Account fromAccount =fromAcc.get();		
	 if(fromAcc.isEmpty()) {
			throw new NotFoundException("Account with id->"+fromAccId+" not found.");
		}	
		
     int balanceFromAcc=fromAccount.getBalance(); //balance of account 
	 System.out.println("balance of from Account is"+balanceFromAcc);
	
	 Account toAccount=toAcc.get();
	 if(toAcc.isEmpty()) {
			throw new NotFoundException("Account with id->"+toAccId+" not found.");
		}
	 int balanceToAcc= toAccount.getBalance();
	 System.out.println(toAccount);
	 if(amount<=0)
	 { 
		 throw new NegativeBalanceException("amount can not be negative");
	 }
	 if(amount>balanceFromAcc)
	 {
		 throw new BalanceExceedLimitException("Low Balance for the transaction");
	 }
	 else if(fromAccId==toAccId) {
	throw new SameAccountexception("Account with from Account id->"+fromAccId+" same as to account id"+toAccId);
}
	//if(amount<balanceFromAcc)
	 else{
	{
		 balanceFromAcc=balanceFromAcc-amount;
		 System.out.println(balanceFromAcc);
		 balanceToAcc=balanceToAcc+amount;
		 System.out.println(balanceToAcc);
		
	}
	fromAccount.setBalance(balanceFromAcc);
	toAccount.setBalance(balanceToAcc);
	accountRepository.save(fromAccount);
	accountRepository.save(toAccount);
    System.out.println(balanceFromAcc);
    System.out.println(balanceToAcc);
	return null;
	
}
}

}


