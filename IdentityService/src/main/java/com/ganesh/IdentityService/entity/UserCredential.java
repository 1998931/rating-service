package com.ganesh.IdentityService.entity;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;

@Component
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="user_detail")
public class UserCredential {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private String name;
    private String email;
    private String password;


}
