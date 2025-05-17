package com.ganesh.RatingService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ganesh.RatingService.entity.Rating;
import com.ganesh.RatingService.service.RatingService;

@RestController
@RequestMapping("/ratings")
public class RatingController {
    @Autowired
private RatingService ratingService;
@PostMapping
    public ResponseEntity<?> create(@RequestBody Rating rating){
        
        return new ResponseEntity<>(ratingService.create(rating),HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<?> getRatings(){

        return new ResponseEntity<>(ratingService.getRatings(),HttpStatus.OK);
    }
@GetMapping("/users/{userId}")
    public ResponseEntity<?> getRatingByUserId(@PathVariable String userId){

        return new ResponseEntity<>(ratingService.getRatingsByUserId(userId),HttpStatus.OK);
    }
    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<?> getRatingByHotelId(@PathVariable String hotelId){

        return new ResponseEntity<>(ratingService.getRatingsByHotelId(hotelId),HttpStatus.OK);
    }

}
