package com.ganesh.user.service.UserService.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ganesh.user.service.UserService.entity.Hotel;
import com.ganesh.user.service.UserService.entity.Rating;
import com.ganesh.user.service.UserService.entity.User;
import com.ganesh.user.service.UserService.exception.UserNotFound;
import com.ganesh.user.service.UserService.external.HotelService;
import com.ganesh.user.service.UserService.external.RatingService;
import com.ganesh.user.service.UserService.repository.UserRepository;
@Service
public class UserServiceImpl implements UserService{
@Autowired
    private UserRepository userrepo;
    @Autowired
    private RestTemplate restTemplate;
    private org.slf4j.Logger logger=LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private HotelService hotelService;
    @Autowired
    private RatingService ratingService;
    @Override
    public User createUser(User user) {
      String random=  UUID.randomUUID().toString();
      user.setUserId(random);
       return userrepo.save(user);
    }

    @Override
public List<User> getAll() {
    // Fetch all users from database
    List<User> users = userrepo.findAll();

    //Fetch ratings from RATING SERVICE
    ResponseEntity<List<Rating>> response = restTemplate.exchange(
        "http://RATINGSERVICE/ratings",
        HttpMethod.GET,
        null,
        new ParameterizedTypeReference<List<Rating>>() {}
    );
        List<Rating> ratings = response.getBody();  // Get the ratings list
        ResponseEntity<List<Hotel>> responsehotels = restTemplate.exchange(
                "http://HOTELSERVICE/ratings",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Hotel>>() {}
        );
        List<Hotel> hotels= responsehotels.getBody();  // Get the ratings list

    // Map ratings to users

        List<Rating> userRatings = new ArrayList<>();
        List<Hotel> userHotels = new ArrayList<>();
    hotels.forEach(hotel -> {
        users.forEach(user -> {
            ratings.forEach(rating -> {
                if (rating.getUserId().equals(user.getUserId())) {
                    userRatings.add(rating);
                }
                if (hotel.getHotelId().equals(rating.getHotelId())) {
                    userHotels.add(hotel);
                }
            });
            user.setHotels(userHotels);
            user.setRatings(userRatings);
        });

    });
        return users;
    }

    @Override
    public User getUser(String userId) {
       User user=userrepo.findById(userId).orElseThrow(()-> new UserNotFound("User not found with an id "));
       //http://localhost:8082/ratings/users/2a712b83-a8d8-422a-9dce-5ca1f2d991c4

       
     // Rating[]  ratingsforUsers=restTemplate.getForObject("http://RATINGSERVICE/ratings/users/"+user.getUserId(),Rating[].class);

Rating[] ratingsforUsers=ratingService.getAllRatings(userId);

//logger.info("{} ",ratingsforUsers);

List<Rating> ratings=Arrays.stream(ratingsforUsers).toList();

List<Rating> ratingList = ratings.stream().map(rating -> {


  // ResponseEntity<Hotel> forEntity = restTemplate.getForEntity(
   //    "http://HOTELSERVICE/hotels/"+rating.getHotelId(), Hotel.class
  // );

   Hotel hotel = hotelService.getHotel(rating.getHotelId());
   rating.setHotel(hotel);
   return rating;  
}).collect(Collectors.toList());

    
user.setRatings(ratingList );
       return user;

    }

}
