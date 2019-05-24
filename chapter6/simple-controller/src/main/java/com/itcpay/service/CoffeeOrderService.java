package com.itcpay.service;

import com.itcpay.model.Coffee;
import com.itcpay.model.CoffeeOrder;
import com.itcpay.model.OrderState;
import com.itcpay.repository.CoffeeOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;

@Service
@Slf4j
@Transactional
public class CoffeeOrderService {

    @Autowired
    private CoffeeOrderRepository coffeeOrderRepository;

    public CoffeeOrder createOrder(String customer, Coffee... coffees) {
        CoffeeOrder order = CoffeeOrder.builder()
                .customer(customer)
                .items(new ArrayList<>(Arrays.asList(coffees)))
                .state(OrderState.INIT)
                .build();
        CoffeeOrder saved = coffeeOrderRepository.save(order);
        log.info("New Order:{}", saved);
        return saved;
    }

}
