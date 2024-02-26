package com.restservices.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.restservices.dto.UserCredentialsDto;
import com.restservices.dto.UserInformationDto;
import com.restservices.entities.UserEntity;
import com.restservices.repositories.UserRepository;
import com.restservices.requestdto.UserCreationRequst;

@Service
public class RestServicesService implements UserDetailsService{
	@Autowired
	UserRepository repo;
	@Autowired
	BCryptPasswordEncoder encoder;
	
	public String saveUser(UserCreationRequst request) {
		UserEntity user = new UserEntity();
		user.setCity(request.getCity());
		user.setContact(request.getContact());
		user.setEmail(request.getEmail());
		user.setGender(request.getGender());
		user.setName(request.getName());
		user.setPassword(encoder.encode(request.getPassword()));
		repo.save(user);
		
		return ";user created succesfully";
	}
    
	public UserInformationDto loadUserInformation(String string) {
		
		UserInformationDto information= new UserInformationDto("gowtham", "gowthamkosuri1@gmail.com","123456" ,"9701886575", "male", "hyderabad");
		
		
		return information;
	}

	public UserCreationRequst loadUserByEmail(String email) {
		
		  Optional<UserEntity> userData=Optional.of(repo.findById(email).get());
		  
		  if(userData.isPresent()) {
			  UserEntity userInfo=repo.findById(email).get();
		UserCreationRequst user=new UserCreationRequst();
		user.setCity(userInfo.getCity());
		user.setContact(userInfo.getContact());
		user.setEmail(userInfo.getEmail());
		user.setGender(userInfo.getGender());
		user.setName(userInfo.getName());
		user.setPassword(userInfo.getPassword());
		return user;
		  }
		  else
		  {System.out.println("user info is not present");
		  return null;
		  
		  }
		  
	}

	public List<UserCreationRequst> loadUserByCity(String city) {
		List<UserEntity> userData =repo.findByCity(city);
		
	     List<UserCreationRequst> dtoList = new ArrayList<>();

	        for (UserEntity userInfo : userData) {
	            UserCreationRequst user = new UserCreationRequst();
	            user.setCity(userInfo.getCity());
	    		user.setContact(userInfo.getContact());
	    		user.setEmail(userInfo.getEmail());
	    		user.setGender(userInfo.getGender());
	    		user.setName(userInfo.getName());
	    		user.setPassword(userInfo.getPassword());

	           dtoList.add(user);
	        }
		
		return dtoList;
	}

	public List<UserCreationRequst> loadUserByGender(String gender) {
		List<UserEntity> userData =repo.findByGender(gender);
		  List<UserCreationRequst> dtoList = new ArrayList<>();

	        for (UserEntity userInfo : userData) {
	            UserCreationRequst user = new UserCreationRequst();
	            user.setCity(userInfo.getCity());
	    		user.setContact(userInfo.getContact());
	    		user.setEmail(userInfo.getEmail());
	    		user.setGender(userInfo.getGender());
	    		user.setName(userInfo.getName());
	    		user.setPassword(userInfo.getPassword());

	           dtoList.add(user);
	        }
		return dtoList;
	}

	public List<UserCreationRequst> loadUserByGenderAndCity(String gender , String city) {
		List<UserEntity> userData =new ArrayList<UserEntity>();
				
		if(gender != null && city != null) {
		userData=repo.findByGenderAndCity(gender , city);
		}else if (gender==null&& city !=null) {
			userData=repo.findByCity(city);
			}else if(city==null && gender !=null) {
				userData=repo.findByGender(gender);
			}else
			{
				userData=repo.findAll();
			}
		  List<UserCreationRequst> dtoList = new ArrayList<>();

	        for (UserEntity userInfo : userData) {
	            UserCreationRequst user = new UserCreationRequst();
	            user.setCity(userInfo.getCity());
	    		user.setContact(userInfo.getContact());
	    		user.setEmail(userInfo.getEmail());
	    		user.setGender(userInfo.getGender());
	    		user.setName(userInfo.getName());
	    		user.setPassword(userInfo.getPassword());

	           dtoList.add(user);
	        }
		return dtoList;
	}

	public String loginUser(UserCredentialsDto userCredentials) {
		UserEntity user = repo.findByEmailAndPassword(userCredentials.getEmail(), userCredentials.getPassword());
		if (user != null) {
			return "welcome to homepage " + user.getName();
		}else {
			return "invalid credentials";
		}
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserDetails user =  repo.findById(email).orElseThrow(
				() -> new RuntimeException("User details not found")
				);
		return user;
	}

	
}
