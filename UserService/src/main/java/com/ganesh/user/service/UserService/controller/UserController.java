package com.ganesh.user.service.UserService.controller;


import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ganesh.user.service.UserService.entity.User;
import com.ganesh.user.service.UserService.service.UserService;
import com.ganesh.user.service.UserService.service.UserServiceImpl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@CrossOrigin("*")
@RequestMapping("/users")
public class UserController {
@Autowired
    private UserService uService;
private org.slf4j.Logger logger=LoggerFactory.getLogger(UserController.class);
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        
       User createdUser= uService.createUser(user);
       return new ResponseEntity<>(createdUser,HttpStatus.CREATED);
    }
    int reteyCount=1;
@GetMapping("/{userId}")
//@CircuitBreaker(name="ratingHotelBreaker",fallbackMethod = "ratingHotelfallback")

@Retry(name="ratingHotelService",fallbackMethod = "ratingHotelfallback")
    public ResponseEntity<User> getUser(@PathVariable String userId){
logger.info("Retry count :{}"+reteyCount);
reteyCount++;
        return new ResponseEntity<>(uService.getUser(userId),HttpStatus.OK);

    }

    public ResponseEntity<User> ratingHotelfallback(String userId,Exception ex){
      //  logger.info("Fallback is excuted because service is down :",ex.getMessage());
     User user=   User.builder()
        .userId("1234")
        .name("Pappu")
        .about("This is dummy user created ")
        .email("pappu@gmail.com")
        .build();
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers(){

        return new ResponseEntity<>(uService.getAll(),HttpStatus.OK);
    }

}
