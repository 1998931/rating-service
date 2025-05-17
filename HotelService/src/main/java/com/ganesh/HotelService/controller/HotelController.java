package com.ganesh.HotelService.controller;

import com.ganesh.HotelService.service.HotelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ganesh.HotelService.entity.Hotel;
import com.ganesh.HotelService.service.HotelService;


@RestController
@RequestMapping("/hotels")
public class HotelController {

    private HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping
    public ResponseEntity<?> cretaed(@RequestBody Hotel hotel){
        
        return new ResponseEntity<>(hotelService.create(hotel),HttpStatus.CREATED);
    }
@GetMapping
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(hotelService.getAll(),HttpStatus.OK);
    }
@GetMapping("/{hotelId}")
    public ResponseEntity<?> getHotel(@PathVariable String hotelId){
        return new ResponseEntity<>(hotelService.getHotel(hotelId),HttpStatus.OK);
    }

    
}
