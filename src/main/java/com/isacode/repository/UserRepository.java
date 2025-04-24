package com.isacode.repository;

import com.isacode.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User >findByEmailAndPassword(String email, String password);

}
