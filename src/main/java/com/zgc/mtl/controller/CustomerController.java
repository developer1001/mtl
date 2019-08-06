package com.zgc.mtl.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zgc.mtl.dao.jpa.CustomersRepository;
import com.zgc.mtl.model.Customer;
/**
 * spring data jpa
 * @date 2019-08-05 10:12:31
 * @author yang
 */
@RestController
@RequestMapping("customer")
public class CustomerController {
	@Autowired
	private CustomersRepository customersRepository;
	
	@RequestMapping("findAll")
	public Object findAll() {
		List<Customer> findAll = customersRepository.findAll();
		return findAll;
	}
}
