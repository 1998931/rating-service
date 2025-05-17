package com.ganesh.user.service.UserService.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ganesh.user.service.UserService.entity.Hotel;

@FeignClient(name="HOTELSERVICE")
public interface HotelService {
@GetMapping("/hotels/{hotelId}")
    Hotel getHotel(@PathVariable String hotelId);


}
