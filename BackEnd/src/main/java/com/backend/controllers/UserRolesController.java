package com.backend.controllers;

import com.backend.model.UserRoles;
import com.backend.model.dto.UserRoleDTO;
import com.backend.service.impl.UserRoleImpl;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/blog/userRoles")
@PreAuthorize("hasRole('ADMIN')")
public class UserRolesController {

    private final UserRoleImpl userRolesService;
    private final ModelMapper modelMapper;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/searchUserRoles")
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

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/findById/{id}")
    public ResponseEntity<UserRoles> getUserRolesById(@PathVariable int id) {
        try {
            UserRoles UserRoles = userRolesService.readById(id);
            return new ResponseEntity<>(UserRoles, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<UserRoles> createUserRoles(@RequestBody UserRoles UserRoles) {
        try {
            UserRoles createdUserRoles = userRolesService.save(UserRoles);
            return new ResponseEntity<>(createdUserRoles, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<UserRoles> updateUserRoles(@PathVariable int id, @RequestBody UserRoles UserRoles) {
        try {
            UserRoles updatedUserRoles = userRolesService.update(UserRoles, id);
            return new ResponseEntity<>(updatedUserRoles, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<Void> deleteUserRoles(@PathVariable int id) {
        try {
            userRolesService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}