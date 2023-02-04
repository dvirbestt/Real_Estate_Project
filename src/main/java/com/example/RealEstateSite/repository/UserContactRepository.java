package com.example.RealEstateSite.repository;

import com.example.RealEstateSite.model.UserContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserContactRepository extends JpaRepository<UserContact,Integer> {

    public Optional<UserContact> findUserByUsername(String username);
    public Optional<UserContact> findUserByEmail(String email);
}
