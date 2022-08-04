package com.example.demo.dao;

public class Transfer {
    
	int fromAccount;
   
	int toAccount;
	int amount;
	public int getFromAccount() {
		return fromAccount;
	}
	public void setFromAccount(int fromAccount) {
		this.fromAccount = fromAccount;
	}
	public int getToAccount() {
		return toAccount;
	}
	public void setToAccount(int toAccount) {
		this.toAccount = toAccount;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public Transfer(int fromAccount, int toAccount, int amount) {
		super();
		this.fromAccount = fromAccount;
		this.toAccount = toAccount;
		this.amount = amount;
	}
	public Transfer() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Transfer [fromAccount=" + fromAccount + ", toAccount=" + toAccount + ", amount=" + amount + "]";
	}

	
	
	
	
}
