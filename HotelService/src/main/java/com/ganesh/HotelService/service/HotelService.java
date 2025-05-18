package com.ganesh.HotelService.service;

import java.util.List;


import org.springframework.stereotype.Service;

import com.ganesh.HotelService.entity.Hotel;


@Service
public interface HotelService {
    Hotel create(Hotel hotel);
    List<Hotel> getAll();
    Hotel getHotel(String hotelId);

}
