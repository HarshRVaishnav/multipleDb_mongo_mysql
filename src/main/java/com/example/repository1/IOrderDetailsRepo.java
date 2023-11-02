package com.example.repository1;

import com.example.entity1.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderDetailsRepo extends JpaRepository<OrderDetails, Integer> {


}
