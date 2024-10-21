package org.example.orders.domain.orders.repository.querydsl;

import org.example.orders.domain.orders.model.entity.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrdersRepositoryCustom {
    Page<Orders> historyWithPaging(Object user, Pageable pageable, String status, Integer month);
}
