package com.example.demo.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.Customer;



@Repository
public interface  BankRepository extends  JpaRepository<Customer,Integer>{

}
