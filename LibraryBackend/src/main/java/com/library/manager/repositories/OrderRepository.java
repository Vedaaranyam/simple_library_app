package com.library.manager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.manager.models.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
