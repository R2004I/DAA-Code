package com.ritam.repository;

import com.ritam.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    User getUserByName(String userName);
    Optional<User> findByEmail(String email);

}
