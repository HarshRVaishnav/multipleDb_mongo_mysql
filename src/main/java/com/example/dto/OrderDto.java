package com.example.dto;

import com.example.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto  {

    private Integer orderNumber;

    private LocalDate orderDate;

    private LocalDate shippedDate;

    private OrderStatus orderStatus;

    @Size(max = 500, message = "Comments cannot be more than 500 characters")
    private String comments;

    private Integer customerNumber;

    private List<OrderDetailsDto> orderDetails;

}
