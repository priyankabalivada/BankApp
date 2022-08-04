package com.example.demo.dao;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="CustomerData")
public class Customer {    
	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", accounts=" + accounts + "]";
	}

	public Customer(int id, String firstName, String lastName, String email, Set<Account> accounts) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.accounts = accounts;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	int id;
	@NotNull(message="cannot be null")
	@Size(min=1, message="cannot be blank")
	@Column(name = "firstName")
	String firstName;
	@NotNull(message="cannot be null")
	@Size(min=1, message="cannot be blank")
	@Column(name = "lastName")
	String lastName;
	@NotNull(message="cannot be null")
	@Size(min=1, message="cannot be blank")
	@Column(name = "email")
	String email;

	public Customer() {
		super();
	}
	
	

	public Customer(int id,
			@NotNull(message = "cannot be null") @Size(min = 1, message = "cannot be blank") String firstName,
			@NotNull(message = "cannot be null") @Size(min = 1, message = "cannot be blank") String lastName,
			@NotNull(message = "cannot be null") @Size(min = 1, message = "cannot be blank") String email) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	@ManyToMany (cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinTable(name="customer_accounts",
	joinColumns = @JoinColumn(name="id", referencedColumnName="id"),
	inverseJoinColumns= @JoinColumn(name="accountNumber", referencedColumnName="accountNumber")
)
	private Set<Account> accounts=new HashSet<>();
	
	
	
}
