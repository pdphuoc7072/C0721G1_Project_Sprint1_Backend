package com.codegym.controller;

import com.codegym.model.Supplies;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class SuppliesInformationController_findAll {
    @Autowired
    private SuppliesInformationController suppliesInformationController;

    @Test
    public void findAll_5() {
        ResponseEntity<Page<Supplies>> responseEntity = this.suppliesInformationController.findAll(PageRequest.of(11,3));
        Assertions.assertEquals(400, responseEntity.getStatusCodeValue());
    }
    @Test
    public void findAll_6() {
        // http client get parameter angular

        ResponseEntity<Page<Supplies>> responseEntity
                = this.suppliesInformationController.findAll(PageRequest.of(0, 2));
        Assertions.assertEquals(200, responseEntity.getStatusCodeValue());
        Assertions.assertEquals(3, responseEntity.getBody().getTotalPages());
        Assertions.assertEquals(6, responseEntity.getBody().getTotalElements());
        Assertions.assertEquals("MÁY TẠO OXY GIA ĐÌNH VARON 1-7 LÍT",
                responseEntity.getBody().getContent().get(1).getName());
    }
}
