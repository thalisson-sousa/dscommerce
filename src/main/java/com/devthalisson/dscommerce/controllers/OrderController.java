package com.devthalisson.dscommerce.controllers;

import com.devthalisson.dscommerce.dto.OrderDTO;
import com.devthalisson.dscommerce.dto.ProductDTO;
import com.devthalisson.dscommerce.dto.ProductMinDTO;
import com.devthalisson.dscommerce.services.OrderService;
import com.devthalisson.dscommerce.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController

@RequestMapping(value = "/orders")
public class OrderController {

    @Autowired
    private OrderService service;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderDTO> findById(@PathVariable Long id) {
        OrderDTO dto = service.findByID(id);
        return ResponseEntity.ok(dto);
    }
}
