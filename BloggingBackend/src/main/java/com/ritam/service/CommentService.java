package com.ritam.service;

import com.ritam.entity.Comment;
import com.ritam.payload.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto,Integer postId,Integer userId);

    void deleteComment(Integer commentId);

    List<CommentDto> getCommentByUser(String userName);
}
