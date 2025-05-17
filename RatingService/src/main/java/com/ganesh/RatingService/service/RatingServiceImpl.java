package com.ganesh.RatingService.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ganesh.RatingService.entity.Rating;
import com.ganesh.RatingService.repository.RatingRepository;
@Service
public class RatingServiceImpl implements RatingService{
@Autowired
    private RatingRepository repository;
    @Override
    public Rating create(Rating rating) {
        String random=UUID.randomUUID().toString();
        rating.setRatingId(random);
       return repository.save(rating);
    }

    @Override
    public List<Rating> getRatings() {
       return repository.findAll();
    }

    @Override
    public List<Rating> getRatingsByUserId(String userId) {
        return repository.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingsByHotelId(String hotelId) {
        return repository.findByHotelId(hotelId);
    }

}
