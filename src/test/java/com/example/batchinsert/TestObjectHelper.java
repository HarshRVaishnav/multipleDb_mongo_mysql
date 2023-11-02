package com.example.batchinsert;
import com.example.entity1.Order;
import com.example.entity1.OrderDetails;

public class TestObjectHelper {

    public static Order createOrder(int nameIdentifier) {
        Order Order = new Order();
        Order.setCustomerNumber(nameIdentifier + 1);
        return Order;
    }

    public static OrderDetails createOrderDetails(Order Order) {
        OrderDetails OrderDetails = new OrderDetails();
        OrderDetails.setOrderNumber(  Order.getOrderNumber());
        OrderDetails.setOrder(Order);
        return OrderDetails;
    }
}
