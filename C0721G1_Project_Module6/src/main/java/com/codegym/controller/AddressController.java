package com.codegym.controller;

import com.codegym.model.Address;
import com.codegym.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

@RestController
@CrossOrigin
@EnableWebMvc
@RequestMapping("address")
public class AddressController {
    @Autowired
    IAddressService iAddressService;

    @GetMapping("")
    public ResponseEntity<?> getAddressList(){
        List<Address> addressList = iAddressService.getAll();
        if(!addressList.isEmpty()){
            return new ResponseEntity<>(addressList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
