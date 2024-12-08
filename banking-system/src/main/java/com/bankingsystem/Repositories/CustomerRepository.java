package com.bankingsystem.Repositories;

import com.bankingsystem.Entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

   // Optional <Customer> findById();
    List<Customer> findAll();
    //Customer findBymail(String mail);


}