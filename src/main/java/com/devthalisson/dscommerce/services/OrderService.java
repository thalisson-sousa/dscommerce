package com.devthalisson.dscommerce.services;

import com.devthalisson.dscommerce.dto.OrderDTO;
import com.devthalisson.dscommerce.entities.Order;
import com.devthalisson.dscommerce.repositories.OrderRepository;
import com.devthalisson.dscommerce.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Transactional(readOnly = true)
    public OrderDTO findByID(Long id) {
        Order order = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
        return new OrderDTO(order);
    }
}
