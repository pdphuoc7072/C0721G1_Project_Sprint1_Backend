package com.example.statsticals.controller;

<<<<<<< HEAD
import com.example.statsticals.dto.PotentialCustomerDto;
=======
>>>>>>> 560e47ad99e674d8d51dae1ff2772c65e9d066c9
import com.example.statsticals.dto.SuppiliesDtoInterface;
import com.example.statsticals.service.ISuppliesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.time.LocalDate;
=======
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

>>>>>>> 560e47ad99e674d8d51dae1ff2772c65e9d066c9
import java.util.List;

@RestController
@EnableWebMvc
@RequestMapping("/api/admin/stats/supplies-stats")
@CrossOrigin
public class SuppliesController {
    @Autowired
    ISuppliesService iSuppliesService;

    @GetMapping("")
    public ResponseEntity<?> getSuppliesStats() {
        List<SuppiliesDtoInterface> suppiliesDtoInterfaceList = iSuppliesService.getAll();
        return new ResponseEntity<>(suppiliesDtoInterfaceList, HttpStatus.OK);
    }
<<<<<<< HEAD

    @GetMapping("/fetch")
    public ResponseEntity<?> getPotentialCustomerByTime(@RequestParam String startDate,
                                                        @RequestParam String endDate) {
        LocalDate ld = LocalDate.parse(startDate);
        LocalDate ld1 = LocalDate.parse(endDate);
        List<SuppiliesDtoInterface> suppiliesDtoInterfaceList = iSuppliesService.getSuppliesByTime(ld, ld1);
        if (!suppiliesDtoInterfaceList.isEmpty()) {
            return new ResponseEntity<>(suppiliesDtoInterfaceList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
=======
}
>>>>>>> 560e47ad99e674d8d51dae1ff2772c65e9d066c9
