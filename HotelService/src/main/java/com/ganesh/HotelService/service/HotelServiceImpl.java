package com.ganesh.HotelService.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ganesh.HotelService.Exception.ResourceNotFoundException;
import com.ganesh.HotelService.HotelRepository.HotelRepository;
import com.ganesh.HotelService.entity.Hotel;
@Service
public class HotelServiceImpl implements HotelService{


    private final  HotelRepository hRepository;

    public HotelServiceImpl(HotelRepository hRepository) {
        this.hRepository = hRepository;
    }

    @Override
    public Hotel create(Hotel hotel) {
       String random= UUID.randomUUID().toString();
       hotel.setHotelId(random);
        return hRepository.save(hotel);
    }


    @Override
    public List<Hotel> getAll() {
       return hRepository.findAll();
    }

    @Override
    public Hotel getHotel(String hotelId) {
      return hRepository.findById(hotelId).orElseThrow(()-> new ResourceNotFoundException("Hotel with given id not found "));
    }

}
