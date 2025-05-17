package com.ganesh.IdentityService.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ganesh.IdentityService.entity.UserCredential;
@Repository
public interface UserCredentialRepository extends JpaRepository<UserCredential,Long> {

    Optional<UserCredential> findByName(String username);

}
