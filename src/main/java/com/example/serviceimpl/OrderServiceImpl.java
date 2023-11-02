package com.example.serviceimpl;

import com.example.entity1.Order;
import com.example.entity1.OrderDetails;
import com.example.entity2.Product;
import com.example.exception.OrderNotFoundException;
import com.example.exception.ProductNotFoundException;
import com.example.repository1.IOrderDetailsRepo;
import com.example.repository1.IOrderRepo;
import com.example.service.IOrderService;
import com.example.service.IProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private IOrderRepo orderRepo;

    @Autowired
    private IProductService productService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IOrderDetailsRepo orderDetailsRepo;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public List<Order> getAllOrder() {
        if (orderRepo.count() != 0) {
            return orderRepo.findAll();
        } else {
            throw new OrderNotFoundException(" Orders not found ");
        }
    }

    @Override
    @Transactional
    public Order createOrder(Order order) {
        order.setComments(order.getComments());
        orderRepo.save(order);
        List<OrderDetails> orderDetailsDtoList = order.getOrderDetails();
        List<Integer> pid = orderDetailsDtoList.stream().map(OrderDetails::getProductCode).collect(Collectors.toList());
        List<Product> products = productService.getlistbyproductCode(pid);
        Map<Integer, Product> productMap = products.stream()
                .collect(Collectors.toMap(Product::getProductCode, product -> product));
        List<OrderDetails> orderDetailsList = new ArrayList<>();

        for (OrderDetails orderDetailsDto : orderDetailsDtoList) {
            Product product = productMap.get(orderDetailsDto.getProductCode());
            if (product.getQuantityInStock() < orderDetailsDto.getQuantityOrdered()) {
                throw new ProductNotFoundException("Product is out of stock");
            }

            productService.updateProductQuantityInStock(product.getProductCode(), orderDetailsDto.getQuantityOrdered());
            OrderDetails orderDetails = modelMapper.map(orderDetailsDtoList, OrderDetails.class);
            orderDetails.setQuantityOrdered(orderDetailsDto.getQuantityOrdered());
            orderDetails.setPriceEach(product.getPrice());
            //orderDetails.setProduct(product);
            //orderDetails.setOrder(order);
            orderDetails.setProductCode(Integer.valueOf(product.getProductCode()));
            orderDetails.setOrderNumber(order.getOrderNumber());
            orderDetailsList.add(orderDetails);
        }

        orderDetailsRepo.saveAll(orderDetailsList);
       // order.setOrderDetails(orderDetailsList);
        orderRepo.save(order);
        return order;
    }

    @Override
    public Order findOrderById( Integer orderNumber) {
        return orderRepo.findById(orderNumber).orElseThrow(() -> new OrderNotFoundException("Order does not exist"));
    }



    @Override
    public Order updateShippingDate(Integer orderNumber,  Order order) {
        Optional<Order> foundOrder = orderRepo.findById(orderNumber);
        if (foundOrder.isPresent()) {
            Order order2 = foundOrder.get();
            order2.setShippedDate(order.getShippedDate());
            return orderRepo.save(order2);
        } else {
            throw new OrderNotFoundException("Shipping Date not found for order number " + orderNumber);
        }
    }

    @Override
    public Order updateOrderStatus(Integer orderNumber, Order order) {
        Optional<Order> foundOrder = orderRepo.findById(orderNumber);
        if (foundOrder.isPresent()) {
            order.setOrderStatus(order.getOrderStatus());
            return orderRepo.save(order);
        } else {
            throw new OrderNotFoundException("Comment of given order could not be updated");
        }
    }

    @Override
    public Order updateComment(Integer orderNumber, Order order) {
        Optional<Order> foundOrder = orderRepo.findById(orderNumber);
        if (foundOrder.isPresent()) {
            Order order2 = foundOrder.get();
            order2.setComments(order.getComments());
            return orderRepo.save(order2);
        } else {
            throw new OrderNotFoundException("Shipping Date not found for order number " + orderNumber);
        }
    }

    @Override
    public String deleteOrder(Integer orderNumber) {
        Optional<Order> optionalOrder = orderRepo.findById(orderNumber);
        // Order orders = null;
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            // Update the quantity of each product that was ordered in this order
            for (OrderDetails orderDetails : order.getOrderDetails()) {
                int productCode = orderDetails.getProductCode();
                int quantityOrdered = orderDetails.getQuantityOrdered();
                productService.incrementDecrementProductQuantityInStock(productCode, "increment", quantityOrdered);
            }
            orderRepo.delete(order);
            return  "Order with order number " + orderNumber + " has been deleted";
        } else {
            throw new OrderNotFoundException("Order with order number " + orderNumber + " not found");
        }
    }
}











































/*  @PostMapping("/ord")
    @Transactional
    public Order createOrder(@Valid @RequestBody OrderDto orderDTO) {
        Order order = new Order();
        order.setComments(orderDTO.getComments());
        Customer customer = iCustomerRepo.findById(orderDTO.getCustomerNumber())
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
        order.setCustomer(customer);
        List<OrderDetailsDto> orderDetailsDTOList = orderDTO.getOrderDetails();
        for (OrderDetailsDto orderDetailsDTO : orderDetailsDTOList) {
            Product product = productRepo.findById(orderDetailsDTO.getProductCode())
                    .orElseThrow(() -> new IllegalArgumentException("Product not found"));
            if (product.getQuantityInStock() >= orderDetailsDTO.getQuantityOrdered()) {
                product.setQuantityInStock(product.getQuantityInStock() - orderDetailsDTO.getQuantityOrdered());
                productRepo.save(product);
                OrderDetails orderDetails = new OrderDetails();
                orderDetails.setOrder(order);
                orderDetails.setProduct(product);
                orderDetails.setQuantityOrdered(orderDetailsDTO.getQuantityOrdered());
                orderDetails.setPriceEach(product.getPrice());
                iOrderDetailsRepo.save(orderDetails);
            } else {
                throw new IllegalArgumentException("Product is out of stock");
            }
        }
        return iOrderRepo.save(order);
    }*/
