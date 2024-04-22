package com.example.springboot.controllers;

import com.example.springboot.dtos.LoginRecordDto;
import com.example.springboot.dtos.ProductRecordDto;
import com.example.springboot.dtos.UserRecordDto;
import com.example.springboot.models.ProductModel;
import com.example.springboot.models.UserModel;
import com.example.springboot.repositories.ProductRepository;
import com.example.springboot.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @PostMapping("/users")
    public ResponseEntity<UserModel> createUser(@RequestBody @Valid UserRecordDto userRecordDto){
        var userModel = new UserModel();
        BeanUtils.copyProperties(userRecordDto, userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(userModel));
    }

    @PostMapping("/users/login")
    public ResponseEntity<Object> login(@RequestBody @Valid LoginRecordDto loginRecordDto){

        Optional<UserModel> user0 = userRepository.findByEmail(loginRecordDto.email());

        if(user0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
        var userModel = user0.get();

        if(userModel.getPassword().equals(loginRecordDto.password())){
            return ResponseEntity.status(HttpStatus.OK).body("You are logged!");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials!");
    }

}






