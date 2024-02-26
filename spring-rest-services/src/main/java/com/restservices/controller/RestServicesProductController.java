package com.restservices.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.restservices.requestdto.Product;

@RestController
public class RestServicesProductController {

	@PostMapping("/create/product")
	public String setProducts(@RequestBody Product request) {
		System.out.println(request);
		
		return "products set succesfully";
	}
}
