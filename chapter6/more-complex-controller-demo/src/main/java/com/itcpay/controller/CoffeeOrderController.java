package com.itcpay.controller;

import com.itcpay.controller.request.NewOrderRequest;
import com.itcpay.model.Coffee;
import com.itcpay.model.CoffeeOrder;
import com.itcpay.service.CoffeeOrderService;
import com.itcpay.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@Slf4j
public class CoffeeOrderController {

    @Autowired
    private CoffeeService coffeeService;
    @Autowired
    private CoffeeOrderService coffeeOrderService;

    @GetMapping("/{id}")
    public CoffeeOrder getOrder(@PathVariable("id") Long id) {
        return coffeeOrderService.get(id);
    }

    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CoffeeOrder create(@RequestBody NewOrderRequest newOrder) {
        log.info("Recevie new order:{}", newOrder);
        List<Coffee> list = coffeeService.getCoffeeByName(newOrder.getItems());
        Coffee[] coffees = list.toArray(new Coffee[]{});
        return coffeeOrderService.createOrder(newOrder.getCustomer(), coffees);
    }

}
