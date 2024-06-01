package com.ritam.controller;

import com.ritam.payload.ApiResponse;
import com.ritam.payload.UserDto;
import com.ritam.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    private UserService userService;

    //PUT - update User

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable("userId") Integer userId) {
      UserDto updatedUser =  this.userService.updateUser(userDto,userId);
      return ResponseEntity.ok(updatedUser);
    }


    //DELETE - Delete User
  @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer uid){
        this.userService.deleteUser(uid);
        return new ResponseEntity(new ApiResponse("user Deleted Successfully",true),HttpStatus.OK);
    }
    //GET - Read Users
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUser(){
       return ResponseEntity.ok(this.userService.getAllUsers());
    }
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable("userId")Integer uid)
    {
        return ResponseEntity.ok(this.userService.getUserById(uid));
    }
}
