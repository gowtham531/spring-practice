package com.restservices.controller;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.restservices.dto.UserCredentialsDto;
import com.restservices.dto.UserInformationDto;
import com.restservices.requestdto.UserCreationRequst;
import com.restservices.services.RestServicesService;
import com.restservices.util.JwtTokenUtil;

@RestController
@RequestMapping("/user")
public class RestServicesController {
	//creating logger object
	private static final org.slf4j.Logger logger=LoggerFactory.getLogger(RestServicesController.class);
	@Autowired
	RestServicesService service;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	JwtTokenUtil jwtTokenUtil;
	
	@PostMapping("/create")
	@ResponseBody
	public String createUser(@RequestBody UserCreationRequst request) {
	    logger.info("in /create endpoint");
		String s =service.saveUser(request);
		
		System.out.println(request);
	    logger.info("User Created Succesfully");

		return s;
	}
	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@RequestHeader("login Header")  @RequestBody UserCredentialsDto userCredentials) {
		 logger.info("in /login endpoint");
		System.out.println("UserController : loginUser() : Validating User Credentials");
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userCredentials.getEmail(), userCredentials.getPassword());
		try {
		authenticationManager.authenticate(authenticationToken);
		 logger.info("user validated succesfully");
		}catch (Exception e) {
			 logger.error("not a valid user"+" "+e.getMessage());
			throw new RuntimeException("bad credentials");
		}
		 logger.info("creating token to user");
		String token= jwtTokenUtil.createToken(userCredentials.getEmail());
		org.springframework.http.HttpHeaders header=new org.springframework.http.HttpHeaders();
		header.add("logintoken", token);
		return new ResponseEntity<String>("Welcome to home", header , HttpStatus.OK);
		
	}
	
	@GetMapping("/load")
	@ResponseBody
	public UserInformationDto loaduserInformation() {
	UserInformationDto information =service.loadUserInformation("gowthamkosuri1@gmail.com");
		
		System.out.println(information);
		return information;	
	}
	@GetMapping("/load/{email}")
	@ResponseBody
	public UserCreationRequst loadUserByEmail (@PathVariable("email") String email) {
		System.out.println("given user info;"+email);
		UserCreationRequst user = service.loadUserByEmail(email);
		return user;
		
	}
	
	//using path variables
	@GetMapping("/load/city/{city}")
	public List<UserCreationRequst> loadUserByCity(@PathVariable("city") String city) {
		System.out.println("given user info;"+city);
		List<UserCreationRequst> user = service.loadUserByCity(city);
		return user;
		
	}
	
	//using query parameters
	@RequestMapping(path =  "/load/gender")
	
	public List<UserCreationRequst> loadUserByGender(@RequestParam(name = "gender") String gender ){
		System.out.println("given user info;"+gender);
		List<UserCreationRequst> user = service.loadUserByGender(gender);
        return user;
}
	
	@GetMapping(path =  "/load/gender/city")
	public ResponseEntity<List<UserCreationRequst>> loadUserByGenderAndCity( @RequestHeader("frontToken") @RequestParam(required = false , name = "gender") String gender ,
			                                                     @RequestParam(required = false , name = "city") String city){
		System.out.println("given user info;"+gender+" "+ city);
		System.out.println("given Header info;");
		List<UserCreationRequst> user = service.loadUserByGenderAndCity(gender , city);
		org.springframework.http.HttpHeaders header=new org.springframework.http.HttpHeaders();
		header.add("token", "headertoken");
		
        return new ResponseEntity<List<UserCreationRequst>>(user, header, HttpStatus.OK);	
}
}

