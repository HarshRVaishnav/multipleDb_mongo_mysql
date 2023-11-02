package com.example.repository1;

import com.example.entity1.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerRepo extends JpaRepository<Customer, Integer> {

    Customer findBycustomerFirstName(String customerFirstName);

    Customer findBycustomerLastName(String customerLastName);
}