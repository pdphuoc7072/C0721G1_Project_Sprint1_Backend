package com.codegym.controller;

import com.codegym.dto.Cart;
import com.codegym.dto.CustomerDTO;
import com.codegym.dto.CustomerTransfer;
import com.codegym.dto.PaymentDTO;
import com.codegym.model.Address;
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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@EnableWebMvc
@RequestMapping("api/public/home/")
@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
@RestController
public class SuppliesInformationController {
    // Start TanTN code
    @Autowired
    ICustomerService iCustomerService;
    @Autowired
    public JavaMailSender emailSender;
    // End TanTN code

    @Autowired
    ISuppliesService iSuppliesService;
    @Autowired
    ISuppliesTypeService iSuppliesTypeService;

    // Start TanTN code
    @PostMapping(value="payment", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createCustomer(@Validated @RequestBody PaymentDTO paymentDTO, BindingResult bindingResult) throws MessagingException {
        CustomerTransfer customerTransfer = paymentDTO.getCustomerTransfer();
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties( customerTransfer,customerDTO);
        List<Cart> cartList = paymentDTO.getCartList();
        Iterable<Customer> customerList = iCustomerService.findAll();
        customerDTO.setCustomers(customerList);
        new CustomerDTO().validate(customerDTO, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        customer.setAddress(new Address(customerTransfer.getAddress().getId(),customerTransfer.getAddress().getName()));
        iCustomerService.save(customer);
        sendEmail(customer);
//        sendHtmlEmail(customer,cartList);
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
        String textMessage = "Kính gửi: Quý khách" +customer.getName()+ "." +
                "Vật tư y tế CodeGym xin trân trọng gửi đến Quý khách" +
                "THÔNG BÁO XÁC NHẬN ĐẶT HÀNG THÀNH CÔNG";
        message.setText(textMessage);
        // Send Message!
        this.emailSender.send(message);
    }

    //    private void sendHtmlEmail (Customer customer, List<Cart> cartList) throws MessagingException {
//        MimeMessage message = emailSender.createMimeMessage();
//        boolean multipart = true;
//        MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "utf-8");
//        String htmlMsg = "<h3>Kính gửi: Quý khách : "+customer.getName()+" </h3>"
//               + "<p> \" Vật tư y tế CodeGym xin trân trọng gửi đến Quý khách\"+\n" +
//                "                \"THÔNG BÁO XÁC NHẬN ĐẶT HÀNG THÀNH CÔNG</p>"
//                +"<img src='http://www.apache.org/images/asf_logo_wide.gif'>";
//        message.setContent(htmlMsg, "text/html");
//        helper.setTo(customer.getEmail());
//        helper.setSubject("[Thông báo] Xác nhận thanh toán thành công");
//        this.emailSender.send(message);
//    }
    @GetMapping("detail/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Supplies> supplies = iSuppliesService.findById(id);
        if(supplies.isPresent()) {
            return new ResponseEntity<>(supplies.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    // End TanTN code


    //Start Nhat code
    @GetMapping("list/{page}")
    public ResponseEntity<?> findAll(@PathVariable int page) {
        Page<Supplies> suppliesList = iSuppliesService.findAll(PageRequest.of(page, 3));
        if (suppliesList != null) {
            return new ResponseEntity<>(suppliesList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    } //End Nhat code

}
