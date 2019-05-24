package com.example.complexcontrollerdemo.service;

import com.example.complexcontrollerdemo.model.Coffee;
import com.example.complexcontrollerdemo.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Coffee getCoffee(Long id){
        return coffeeRepository.getOne(id);
    }

    public Coffee getCoffee(String name){
        return coffeeRepository.findByName(name);
    }

}
