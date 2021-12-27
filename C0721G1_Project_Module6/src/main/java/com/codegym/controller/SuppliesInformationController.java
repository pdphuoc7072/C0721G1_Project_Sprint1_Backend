package com.codegym.controller;

import com.codegym.dto.CustomerDTO;
import com.codegym.model.Customer;
import com.codegym.model.Supplies;
import com.codegym.service.ICustomerService;
import com.codegym.service.ISuppliesService;
import com.codegym.service.ISuppliesTypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EnableWebMvc
@RequestMapping("home")
@CrossOrigin
@RestController
public class SuppliesInformationController {
    // TanTN code 24/12/2021
    @Autowired
    ICustomerService iCustomerService;
    @Autowired
    public JavaMailSender emailSender;
    @Autowired
    ISuppliesService iSuppliesService;
    @Autowired
    ISuppliesTypeService iSuppliesTypeService;

    @GetMapping("/payment")
    public ResponseEntity<?> createCustomer(@RequestBody @Valid CustomerDTO customerDTO, BindingResult bindingResult) {
        Iterable<Customer> customerList = iCustomerService.findAll();
        customerDTO.setCustomers(customerList);
        new CustomerDTO().validate(customerDTO, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        iCustomerService.save(customer);
        sendEmail(customer);
        return new ResponseEntity<>(HttpStatus.OK);
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
    private void sendEmail (Customer customer){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(customer.getEmail());
        message.setSubject("[Thông báo] Xác nhận thanh toán thành công");
        message.setText("Kính gửi: Quý khách " +customer.getName()+". Vật tư y tế CodeGym xin trân trọng gửi đến Quý khách"+
                "THÔNG BÁO XÁC NHẬN THANH TOÁN THÀNH CÔNG");
        // Send Message!
        this.emailSender.send(message);
    }

//    @GetMapping("list")
//    public ResponseEntity<?> getSuppliesList(@RequestParam int page) {
//        Pageable pageable = PageRequest.of(page, 3);
//        List<Supplies> suppliesList = iSuppliesService.getSuppliesList(page);
//        if (suppliesList != null) {
//            return new ResponseEntity<>(suppliesList, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

    @GetMapping("/list")
    public ResponseEntity<Page<Supplies>> findAll (@PageableDefault(size = 3) Pageable pageable) {
        Page<Supplies> suppliesList = iSuppliesService.findAll(pageable);
        if (!suppliesList.isEmpty()) {
            return new ResponseEntity<>(suppliesList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
//    ABCXYZ

}
