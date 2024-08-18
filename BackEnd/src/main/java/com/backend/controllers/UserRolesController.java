package com.backend.controllers;

import com.backend.model.Articles;
import com.backend.model.UserRoles;
import com.backend.model.Users;
import com.backend.model.dto.ArticlesDTO;
import com.backend.model.dto.UserRoleDTO;
import com.backend.model.dto.UsersDTO;
import com.backend.service.impl.UserRoleImpl;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/UserRoless")
public class UserRolesController {

    private final UserRoleImpl userRolesService;
    private final ModelMapper modelMapper;

    @GetMapping("/searchUserRoless")
    public ResponseEntity<Page<UserRoleDTO>> searchUserRoless(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "40") int size) throws Exception {

        try {
            List<UserRoles> userRoles = userRolesService.readAll();
            Pageable pageable = PageRequest.of(page, size);
            Page<UserRoles> userPage = userRolesService.readAll(pageable);
            Page<UserRoleDTO> userDtoPage = userPage.map(this::convertToDto);
            return new ResponseEntity<>(userDtoPage, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    private UserRoleDTO convertToDto(UserRoles users) {
        return modelMapper.map(users, UserRoleDTO.class);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<UserRoles> getUserRolesById(@PathVariable int id) {
        try {
            UserRoles UserRoles = userRolesService.readById(id);
            return new ResponseEntity<>(UserRoles, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<UserRoles> createUserRoles(@RequestBody UserRoles UserRoles) {
        try {
            UserRoles createdUserRoles = userRolesService.save(UserRoles);
            return new ResponseEntity<>(createdUserRoles, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserRoles> updateUserRoles(@PathVariable int id, @RequestBody UserRoles UserRoles) {
        try {
            UserRoles updatedUserRoles = userRolesService.update(UserRoles, id);
            return new ResponseEntity<>(updatedUserRoles, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<Void> deleteUserRoles(@PathVariable int id) {
        try {
            userRolesService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    private UserRolesDTO convertToDto(UserRoless obj) {
//        return mapper.map(obj, UserRolesDTO.class);
//    }
//
//    private UserRoless convertToEntity(UserRolesDTO dto) {
//        return mapper.map(dto, UserRoless.class);
//    }
}