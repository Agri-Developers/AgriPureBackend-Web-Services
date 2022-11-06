package com.agripure.agripurebackend.controller;

import com.agripure.agripurebackend.entities.User;
import com.agripure.agripurebackend.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@Api(tags = "Users", value = "Web Service RESTful - Users")
public class UserController {
    private IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List User", notes = "Method for list all Users")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Users found"),
            @ApiResponse(code = 404, message = "Users not found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<List<User>>findAllUsers() {
        try {
            List<User> users = userService.getAll();
            if(users.size() > 0)
                return new ResponseEntity<>(users, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Find User by Id", notes = "Method for find user by Id")
    @ApiResponses({
            @ApiResponse(code = 201, message = "User found"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<User>findUserById(@PathVariable("id") Long id) {
        try {
            Optional<User> user = userService.getById(id);
            if (!user.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            else
                return new ResponseEntity<>(user.get(), HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<User>findByEmail(@RequestParam(name = "email")String email) {
        try {
            Optional<User> user = userService.findByEmail(email);
            if(!user.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            else
                return new ResponseEntity<>(user.get(), HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/searchPremium", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List Users premium", notes = "Method for list all Users premium")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Users found"),
            @ApiResponse(code = 404, message = "Users not found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<List<User>>findByPremium(@RequestParam(name = "premium")Boolean premium) {
        try {
            List<User> users = userService.findByPremium(premium);
            if(users.size() > 0)
                return new ResponseEntity<>(users, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}