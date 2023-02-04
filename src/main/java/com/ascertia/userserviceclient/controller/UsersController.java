package com.ascertia.userserviceclient.controller;


import com.ascertia.userserviceclient.dto.ApiResponse;
import com.ascertia.userserviceclient.dto.UsersDTO;
import com.ascertia.userserviceclient.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class UsersController {

    @Autowired
    UsersService usersService;

    @PostMapping("/save-user")
    public ResponseEntity<ApiResponse<String>> saveUser(@RequestBody UsersDTO usersDTO){
        log.info("In UserController.saveUser()");
        usersService.saveUser(usersDTO);
        log.info("In UserController.saveUser() Successfully saved user");
        return ResponseEntity.ok(new ApiResponse<>(null,null));
    }

    @PutMapping("/update-user")
    public ResponseEntity<ApiResponse<String>> updateUser(@RequestBody UsersDTO usersDTO){
        log.info("In UserController.updateUser()");
        usersService.updateUser(usersDTO);
        log.info("In UserController.updateUser() Successfully update user");
        return ResponseEntity.ok(new ApiResponse<>(null,null));
    }

    @GetMapping("/get-all-users")
    public ResponseEntity<ApiResponse<List<UsersDTO>>> getAllUsers(){
        log.info("In UserController.getAllUsers()");
        List<UsersDTO> users=usersService.getAllUsers();
        log.info("In UserController.getAllUsers() Successfully get all users");
        return ResponseEntity.ok(new ApiResponse<>(users,null));
    }

    @GetMapping("/search-user")
    public ResponseEntity<ApiResponse<List<UsersDTO>>> searchUser(UsersDTO usersDTO){
        log.info("In UserController.searchUser()");
        List<UsersDTO> users= usersService.searchUser(usersDTO);
        log.info("In UserController.searchUser() Successfully search user");
        return ResponseEntity.ok(new ApiResponse<>(users,null));
    }

    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<ApiResponse<String>> deleteUser(@PathVariable("id") Long id){
        log.info("In UserController.searchUser()");
        usersService.deleteUser(id);
        log.info("In UserController.searchUser() Successfully delete user");
        return ResponseEntity.ok(new ApiResponse<>(null,null));
    }
}
