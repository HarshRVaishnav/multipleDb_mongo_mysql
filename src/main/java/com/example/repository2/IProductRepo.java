package com.example.repository2;

import com.example.entity2.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//@EnableMongoRepositories
public interface IProductRepo extends MongoRepository<Product, Integer> {

    public List<Product> findAllByOrderByProductNameAsc();


    public List<Product> findByProductNameContainsAllIgnoreCase(String name);


    public List<Product> findByProductCodeIn(List<Integer> collect);

    public List<Product> findByProductCodeInAndQuantityInStockGreaterThan(List<Integer> productCodes, int quantityInStock);

}
