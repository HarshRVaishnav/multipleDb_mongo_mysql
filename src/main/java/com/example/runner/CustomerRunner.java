package com.example.runner;

import com.example.entity1.Customer;
import com.example.repository1.ICustomerRepo;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

//@Component
@Order(2)
public class CustomerRunner implements CommandLineRunner {
    @Autowired
    private ICustomerRepo customerRepo;

    private static final int LOOP_CUNT = 100;

    private final Faker faker = new Faker(new Locale("en-IND"));

    public void run(String... args) {
        if (customerRepo.count() == 0) {
            List<Customer> list = new LinkedList<>();
            for (int i = 1; i < LOOP_CUNT; i++) {
                Customer customer = new Customer();
                customer.setCustomerFirstName(faker.name().firstName());
                customer.setCustomerLastName(faker.name().lastName());
                customer.setPhone(String.valueOf(faker.number().numberBetween(1000000000L, 9999999999L)));
                customer.setAddressLine1(faker.address().streetAddress());
                customer.setAddressLine2(faker.address().secondaryAddress());
                customer.setCity(faker.address().city());
                customer.setState(faker.address().state());
                customer.setPostalCode(faker.number().numberBetween(100000, 999999));
                customer.setCountry(faker.address().country());
                list.add(customer);
            }
            customerRepo.saveAll(list);
        }
    }
}
