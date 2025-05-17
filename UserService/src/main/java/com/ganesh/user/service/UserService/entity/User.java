package com.ganesh.user.service.UserService.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
@Builder
public class User {
    @Id
private String userId;
private String name;
private String email;
private String about;
@Transient
private List<Rating> ratings=new ArrayList<>();
@Transient
    private List<Hotel> hotels=new ArrayList<>();
}
