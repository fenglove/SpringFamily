package com.itcpay.controller;

import com.itcpay.model.Coffee;
import com.itcpay.service.CoffeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/coffee")
public class CoffeeController {

    @Autowired
    private CoffeeService coffeeService;

    @ResponseBody
    @GetMapping("/")
    public List<Coffee> getAll() {
        return coffeeService.getAllCoffee();
    }

}
