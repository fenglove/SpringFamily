package com.itcpay.controller;

import com.itcpay.controller.request.NewCoffeeRequest;
import com.itcpay.model.Coffee;
import com.itcpay.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/coffee")
public class CoffeeController {

    @Autowired
    private CoffeeService coffeeService;

//    @ResponseBody
//    @ResponseStatus(HttpStatus.CREATED)
//    @RequestMapping(path = "/", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
//    public Coffee addCoffee(@Valid NewCoffeeRequest newCoffee, BindingResult result) {
//        if (result.hasErrors()) {
//            // 简单处理一下
//            log.warn("Binding Errors:{}", result);
//            return null;
//        }
//        return coffeeService.saveCoffee(newCoffee.getName(), newCoffee.getPrice());
//    }

    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Coffee addCoffeeWithoutBindingResult(@Valid NewCoffeeRequest newCoffee) {
        return coffeeService.saveCoffee(newCoffee.getName(), newCoffee.getPrice());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<Coffee> batchCoffee(@RequestParam("file") MultipartFile file) {
        List<Coffee> coffees = new ArrayList<>();
        if (!file.isEmpty()) {
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
                String str;
                while ((str = reader.readLine()) != null) {
                    String[] arr = StringUtils.split(str, " ");
                    if (arr != null && arr.length == 2) {
                        coffees.add(coffeeService.saveCoffee(arr[0],
                                Money.of(CurrencyUnit.of("CNY"), NumberUtils.createBigDecimal(arr[1]))));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                IOUtils.closeQuietly(reader);
            }
        }
        return coffees;
    }

    @ResponseBody
    @GetMapping(path = "/", params = "!name")
    public List<Coffee> getAll() {
        return coffeeService.getAllCoffee();
    }

    /**
     * 测试不要 @PathVariable 注解
     */
    @ResponseBody
    @RequestMapping(path = "/{id}", method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Coffee getById(@PathVariable Long id) {
        Coffee coffee = coffeeService.getCoffee(id);
        return coffee;
    }

    @ResponseBody
    @GetMapping(path = "/", params = "name")
    public Coffee getByName(String name) {
        return coffeeService.getCoffee(name);
    }

}
