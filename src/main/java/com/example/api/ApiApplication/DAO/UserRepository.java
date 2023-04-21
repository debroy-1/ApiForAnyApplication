package com.example.api.ApiApplication.DAO;

import com.example.api.ApiApplication.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
