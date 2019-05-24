package com.example.complexcontrollerdemo.controller;

import com.example.complexcontrollerdemo.model.Coffee;
import com.example.complexcontrollerdemo.service.CoffeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/coffee")
public class CoffeeController {

    @Autowired
    private CoffeeService coffeeService;

    @ResponseBody
    @GetMapping("/")
    public List<Coffee> getAll(){
        return coffeeService.getAllCoffee();
    }

    @ResponseBody
    @RequestMapping(path = "/{id}", method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Coffee getById(@PathVariable Long id){
        Coffee coffee = coffeeService.getCoffee(id);
        return coffee;
    }

    @ResponseBody
    @GetMapping(value = "/", params = "name")
    public Coffee getByName(@RequestParam String name){
        return coffeeService.getCoffee(name);
    }

}
