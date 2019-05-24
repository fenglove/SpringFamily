package com.itcpay.simplecontrollerdemo.repository;

import com.itcpay.simplecontrollerdemo.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {

}
