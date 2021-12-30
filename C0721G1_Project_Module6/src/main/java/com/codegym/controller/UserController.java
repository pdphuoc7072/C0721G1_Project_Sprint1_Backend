package com.codegym.controller;

import com.codegym.dto.UserDto;
import com.codegym.model.User;
import com.codegym.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api")
@CrossOrigin
public class UserController {

    @Autowired
    private IUserService iUserService;

    //TinhBT
    @PatchMapping("/editPass")
    public ResponseEntity<HttpStatus> editPassword(@RequestBody UserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            User user = new User();
            BeanUtils.copyProperties(userDto, user);
            iUserService.save(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }

    }

    //TinhBT
    @GetMapping("/find/{id}")
    public ResponseEntity<Optional<User>> findUser(@PathVariable Long id) {
        Optional<User> user = this.iUserService.findById(id);
        if (user.isPresent()) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
