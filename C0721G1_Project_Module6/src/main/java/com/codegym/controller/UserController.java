package com.codegym.controller;


import com.codegym.dto.UserDto;
import com.codegym.model.User;
import com.codegym.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api")
@CrossOrigin
public class UserController {

    @Autowired
    private IUserService iUserService;


    @PatchMapping("/editPass" )
    public ResponseEntity<?> editPassword (@RequestBody @Valid UserDto userDto , BindingResult bindingResult) {

        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            User user = new User();
            BeanUtils.copyProperties(userDto, user);
            iUserService.save(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        @ExceptionHandler(MethodArgumentNotValidException.class)
        public Map<String, String> handleValidationExceptions(
                MethodArgumentNotValidException ex) {
            Map<String, String> errors = new HashMap<>();
            ex.getBindingResult().getAllErrors().forEach((error) -> {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            });
            return errors;
        }

}
