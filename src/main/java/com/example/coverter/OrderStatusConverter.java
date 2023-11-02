package com.example.coverter;

import com.example.enums.OrderStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class OrderStatusConverter implements AttributeConverter<OrderStatus, String> {

    @Override
    public String convertToDatabaseColumn(OrderStatus status) {
        return status.name();
    }

    @Override
    public OrderStatus convertToEntityAttribute(String status) {
        return OrderStatus.valueOf(status);
    }
}
