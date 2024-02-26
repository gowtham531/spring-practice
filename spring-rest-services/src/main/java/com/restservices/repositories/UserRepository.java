package com.restservices.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restservices.entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String>{

   List<UserEntity> findByCity(String city);

  List<UserEntity> findByGender(String gender);

List<UserEntity> findByGenderAndCity(String gender, String city);

UserEntity findByEmailAndPassword(String email, String password);

}