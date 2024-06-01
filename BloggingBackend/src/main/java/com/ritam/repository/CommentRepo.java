package com.ritam.repository;

import com.ritam.entity.Comment;
import com.ritam.entity.User;
import com.ritam.payload.CommentDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment,Integer> {

    List<Comment> findByUser(User user);
}
