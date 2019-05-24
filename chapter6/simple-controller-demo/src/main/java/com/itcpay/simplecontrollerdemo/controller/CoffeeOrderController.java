package com.itcpay.simplecontrollerdemo.controller;

import com.itcpay.simplecontrollerdemo.controller.request.NewOrderRequest;
import com.itcpay.simplecontrollerdemo.model.Coffee;
import com.itcpay.simplecontrollerdemo.model.CoffeeOrder;
import com.itcpay.simplecontrollerdemo.service.CoffeeOrderService;
import com.itcpay.simplecontrollerdemo.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@Slf4j
public class CoffeeOrderController {

    @Autowired
    private CoffeeOrderService orderService;
    @Autowired
    private CoffeeService coffeeService;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public CoffeeOrder create(@RequestBody NewOrderRequest newOrder){
        log.info("Receive new Order {}", newOrder);
        Coffee[] coffeeList = coffeeService.getCoffeeByName(newOrder.getItems())
                .toArray(new Coffee[] {});
        return orderService.createOrder(newOrder.getCustomer(), coffeeList);
    }

}
