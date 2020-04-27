package com.library.manager.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.manager.models.Order;
import com.library.manager.repositories.OrderRepository;

@RestController
public class OrderController {

	@Autowired
	private OrderRepository orderRepository;

	@GetMapping("/order")
	public List<Order> listOrders() {
		return orderRepository.findAll();
	}

}
