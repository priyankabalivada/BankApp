package com.example.demo.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.Account;



@Repository
public interface  AccountRepository extends  JpaRepository<Account,Integer>{


  public Account findByAccountTypeAndBalance(String accountType,Integer balance);
  public List<Account> findByAccountTypeOrBalance(String accountType,Integer balance);
  @Query(value="select * from Account",nativeQuery=true)
  public List<Account> getAllAccounts();

}
