package com.example.service;


import com.example.entity1.Order;

import javax.validation.Valid;
import java.util.List;

public interface IOrderService {

    public List<Order> getAllOrder();

    public Order createOrder(Order orderDto);

    public Order findOrderById(Integer orderNumber);

    public String deleteOrder(Integer orderNumber) ;

    public Order updateShippingDate( Integer orderNumber,  Order order);

    Order updateOrderStatus(Integer orderNumber, Order order);

    public Order updateComment(Integer orderNumber, Order order);

    //public Order createOrder2(Order order);

}