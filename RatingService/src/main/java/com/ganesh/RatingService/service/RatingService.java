package com.ganesh.RatingService.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ganesh.RatingService.entity.Rating;
@Service
public interface RatingService {

Rating create(Rating rating);

List<Rating> getRatings();

List<Rating> getRatingsByUserId(String userId);

List<Rating> getRatingsByHotelId(String hotelId);


}
