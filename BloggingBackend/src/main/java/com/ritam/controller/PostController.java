package com.ritam.controller;

import com.ritam.config.AppConstants;
import com.ritam.payload.ApiResponse;
import com.ritam.payload.PostDto;
import com.ritam.payload.PostResponse;
import com.ritam.service.FileService;
import com.ritam.service.PostService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    private FileService fileService;
    @Value("${project.image}")
    private String path;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto
    , @PathVariable Integer userId,@PathVariable Integer categoryId)
    {
      PostDto createPost = this.postService.createPost(postDto,userId,categoryId);
       return new ResponseEntity<>(createPost, HttpStatus.CREATED);
    }
    @GetMapping("user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId){
       List<PostDto> posts = this.postService.getPostsByUser(userId);
       return new ResponseEntity<>(posts,HttpStatus.OK);
    }


    @GetMapping("category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId){
        List<PostDto> posts = this.postService.getPostsByCategory(categoryId);
        return new ResponseEntity<>(posts,HttpStatus.OK);
    }

    @GetMapping("post/{postId}")
    public ResponseEntity<PostDto> getPostByPostId(@PathVariable Integer postId){
       PostDto postDto = this.postService.getPostById(postId);
       return new ResponseEntity<>(postDto,HttpStatus.OK);
    }

    @GetMapping("posts")
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize,
            @RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY,required = false) String sortBy
    ){
       PostResponse postResponse = this.postService.getAllPost(pageNumber,pageSize,sortBy);
       return new ResponseEntity<>(postResponse,HttpStatus.OK);
    }

    @DeleteMapping("posts/{postId}")
    public ApiResponse deletePost(@PathVariable Integer postId){
        this.postService.deletePost(postId);
        return new ApiResponse("Post is Deleted Successfully",true);
    }
    @PutMapping("posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable Integer postId){
      PostDto updatePost =  this.postService.updatePost(postDto,postId);
      return new ResponseEntity<>(updatePost,HttpStatus.OK);
    }
    @GetMapping("posts/search/{keywords}")
    public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable(value = "keywords") String keywords){
      List<PostDto> result =  this.postService.searchPosts(keywords);
      return new ResponseEntity<>(result,HttpStatus.OK);
    }

    //Post Image upload
    @PostMapping("post/image/upload/{postId}")
    public ResponseEntity<PostDto> uploadPostImage(@RequestParam("image")MultipartFile image,
                                                         @PathVariable Integer postId) throws IOException {
        PostDto postDto = this.postService.getPostById(postId);
       String fileName = this.fileService.uploadImage(path,image);
       postDto.setImageName(fileName);
     PostDto updatedPost = this.postService.updatePost(postDto,postId);
     return new ResponseEntity<>(updatedPost,HttpStatus.OK);
    }
    @GetMapping(value = "post/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable("imageName")String imageName, HttpServletResponse response) throws IOException
    {
        InputStream resources = this.fileService.getResource(path,imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resources,response.getOutputStream());
    }

}
