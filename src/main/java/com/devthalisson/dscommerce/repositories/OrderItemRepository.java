package com.devthalisson.dscommerce.repositories;

import com.devthalisson.dscommerce.entities.Order;
import com.devthalisson.dscommerce.entities.OrderItem;
import com.devthalisson.dscommerce.entities.OrderItemPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {

}