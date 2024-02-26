package com.restservices.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.restservices.requestdto.Order;

@Controller
public class RestServicesOrderController {

	@PostMapping("/order/create")
	@ResponseBody
	public String createOrder(@RequestBody Order request) {
		System.out.println(request);
		return "order created succesfully";
}
}	
	
