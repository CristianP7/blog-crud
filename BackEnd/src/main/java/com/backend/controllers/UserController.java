package com.backend.controllers;

import com.backend.model.Users;
import com.backend.model.dto.UsersDTO;
import com.backend.service.impl.UserImpl;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/User")
public class UserController {

    private final UserImpl userService;

    @GetMapping("/searchUser")
    public ResponseEntity<Page<Users>> searchUser(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "40") int size) throws Exception {

        Pageable pageable = PageRequest.of(page, size);
        Page<Users> userPage = userService.readAll(pageable);
        return new ResponseEntity<>(userPage, HttpStatus.OK);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable int id) {
        try {
            Users users = userService.readById(id);
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/create", consumes = "application/json")
    public ResponseEntity<Users> createUser(@RequestBody Users users) {
        try {
            Users createdUsers = userService.save(users);
            return new ResponseEntity<>(createdUsers, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable int id, @RequestBody Users users) {
        try {
            Users updatedUsers = userService.update(users, id);
            return new ResponseEntity<>(updatedUsers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        try {
            userService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    private UsersDTO convertToDto(Users obj) {
//        return mapper.map(obj, UsersDTO.class);
//    }
//
//    private Users convertToEntity(UsersDTO dto) {
//        return mapper.map(dto, Users.class);
//    }
}