package com.itcpay.simplecontrollerdemo.service;

import com.itcpay.simplecontrollerdemo.model.Coffee;
import com.itcpay.simplecontrollerdemo.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

@Slf4j
@Service
@CacheConfig(cacheNames = "CoffeeCache")
public class CoffeeService {

    @Autowired
    private CoffeeRepository coffeeRepository;

    @Cacheable
    public List<Coffee> getAllCoffee() {
        return coffeeRepository.findAll();
    }

    public List<Coffee> getCoffeeByName(List<String> names){
        return coffeeRepository.findByNameInOrderById(names);
    }

}
