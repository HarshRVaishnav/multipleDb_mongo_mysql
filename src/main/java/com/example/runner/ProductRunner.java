package com.example.runner;

import com.example.entity2.Product;
import com.example.repository2.IProductRepo;
import com.example.serviceimpl.SequenceGeneratorService;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import static com.example.entity2.Product.SEQUENCE_NAME;

@Component
@Order(5)
public class ProductRunner implements CommandLineRunner {

    @Autowired
    private IProductRepo productRepo;
    @Autowired
    private SequenceGeneratorService service;
    @Override
    public void run(String... args) throws Exception {
        if (productRepo.count() == 0) {
            System.out.println(productRepo.count());
            List<Product> list=new LinkedList<>();
            Faker faker = new Faker(new Locale("en-IND"));
            for (int i = 1; i < 10; i++) {
                Product product = new Product();
                product.setProductCode(service.getSequenceNumber(SEQUENCE_NAME));
                product.setProductName(faker.commerce().productName());
                product.setProductDescription(faker.lorem().sentence());
                product.setQuantityInStock(faker.number().numberBetween(1, 100));
                product.setPrice(faker.number().randomDouble(2, 102, 10000));
                list.add(product);
            }
            productRepo.saveAll(list);
        }
    }
}
