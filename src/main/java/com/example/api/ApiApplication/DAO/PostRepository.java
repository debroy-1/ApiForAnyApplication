package com.example.api.ApiApplication.DAO;

import com.example.api.ApiApplication.user.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Integer> {
}
