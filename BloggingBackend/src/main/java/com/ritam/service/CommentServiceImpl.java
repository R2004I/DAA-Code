package com.ritam.service;

import com.ritam.entity.Comment;
import com.ritam.entity.Post;
import com.ritam.entity.User;
import com.ritam.exception.ResourceNotFoundException;
import com.ritam.payload.CommentDto;
import com.ritam.repository.CommentRepo;
import com.ritam.repository.PostRepo;
import com.ritam.repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId, Integer userId) {

        User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","user id",userId));
        Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","post id",postId));
        Comment comment=this.modelMapper.map(commentDto,Comment.class);
        comment.setUser(user);
        comment.setPost(post);
        Comment newComment = this.commentRepo.save(comment);
        return this.modelMapper.map(newComment,CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
      Comment comment = this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","comment id",commentId));
        this.commentRepo.delete(comment);
    }

    @Override
    public List<CommentDto> getCommentByUser(String userName) {
      User user = this.userRepo.getUserByName(userName);
       List<Comment> comments = this.commentRepo.findByUser(user);
     List<CommentDto> commentDtoList = comments.stream().map((comment)->this.modelMapper.map(comment,CommentDto.class)).collect(Collectors.toList());
        return commentDtoList;
    }
}
