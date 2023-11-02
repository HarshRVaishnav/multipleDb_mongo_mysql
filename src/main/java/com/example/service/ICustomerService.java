package com.example.service;

import java.util.List;

import com.example.entity1.Customer;

public interface ICustomerService {

    public List<Customer> getCustomerList();

    public Customer registerCustomer(Customer customerNumber);

    public Customer updateCustomerDetail(Integer customerNumber, Customer customer);

    public Customer getCustomerById(Integer customerNumber);

    public String deleteCustomer(Integer customerNumber);

    public Customer findBycustomerFirstName(String customerFirstName);

    public Customer findBycustomerLastName(String customerLastName);

}
