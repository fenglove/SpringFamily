package com.itcpay.service;

import com.itcpay.model.Coffee;
import com.itcpay.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@CacheConfig(cacheNames = "CoffeeCache")
public class CoffeeService {

    @Autowired
    private CoffeeRepository coffeeRepository;

    @Cacheable
    public List<Coffee> getAllCoffee() {
        return coffeeRepository.findAll();
    }

    public List<Coffee> getCoffeeByName(List<String> names) {
        return coffeeRepository.findByNameInOrderById(names);
    }

}
