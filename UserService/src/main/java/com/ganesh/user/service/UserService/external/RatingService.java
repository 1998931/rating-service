package com.ganesh.user.service.UserService.external;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.ganesh.user.service.UserService.entity.Rating;

@FeignClient(name="RATINGSERVICE")
public interface RatingService {

@GetMapping("/ratings/users/{userId}")
    Rating[] getAllRatings(@PathVariable String userId);
    @GetMapping
    List<Rating> getAllRatingsForUsers();

}
