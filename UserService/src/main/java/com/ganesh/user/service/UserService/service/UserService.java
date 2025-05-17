package com.ganesh.user.service.UserService.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ganesh.user.service.UserService.entity.User;
@Service
public interface UserService {
User createUser(User user);
List<User> getAll();
User getUser(String userId);

}
