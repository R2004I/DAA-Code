package com.ritam.controller;

import com.ritam.payload.ApiResponse;
import com.ritam.payload.CommentDto;
import com.ritam.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("user/{userId}/post/{postId}")
    public ResponseEntity<CommentDto> postComment(@RequestBody CommentDto commentDto,@PathVariable Integer userId,@PathVariable Integer postId)
    {
       CommentDto commentDto1 = this.commentService.createComment(commentDto,postId,userId);
        return new ResponseEntity<>(commentDto1, HttpStatus.CREATED);
    }
    @GetMapping("user/{userName}")
    public ResponseEntity<List<CommentDto>> getCommentByUserName(@PathVariable String userName)
    {
       List<CommentDto> commentDtoList = this.commentService.getCommentByUser(userName);
       return new ResponseEntity<>(commentDtoList,HttpStatus.OK);
    }
    @DeleteMapping("delete/{commentId}")
    public ApiResponse deleteComment(@PathVariable Integer commentId){
        this.commentService.deleteComment(commentId);
        return new ApiResponse("Comment is deleted Successfully",true);
    }
}
