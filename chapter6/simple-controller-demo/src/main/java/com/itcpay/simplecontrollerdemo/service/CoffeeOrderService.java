package com.itcpay.simplecontrollerdemo.service;

import com.itcpay.simplecontrollerdemo.model.Coffee;
import com.itcpay.simplecontrollerdemo.model.CoffeeOrder;
import com.itcpay.simplecontrollerdemo.model.OrderState;
import com.itcpay.simplecontrollerdemo.repository.CoffeeOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;

@Slf4j
@Service
@Transactional
public class CoffeeOrderService {

    @Autowired
    private CoffeeOrderRepository orderRepository;

    public CoffeeOrder createOrder(String customer, Coffee...coffee){
        CoffeeOrder order = CoffeeOrder.builder()
                .customer(customer)
                .items(new ArrayList<>(Arrays.asList(coffee)))
                .state(OrderState.INIT)
                .build();
        CoffeeOrder saved = orderRepository.save(order);
        log.info("New order: {}", saved);
        return saved;
    }

    public boolean updateState(CoffeeOrder order, OrderState state){
        if (state.compareTo(order.getState()) <= 0) {
            log.warn("Wrong State order: {}, {}", state, order.getState());
            return false;
        }
        order.setState(state);
        orderRepository.save(order);
        log.info("Updated Order: {}", order);
        return true;
    }

}
