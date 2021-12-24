package com.codegym.controller;

import com.codegym.jwt.JwtUtility;
import com.codegym.model.Employee;
import com.codegym.model.Role;
import com.codegym.model.User;
import com.codegym.payload.request.LoginRequest;
import com.codegym.payload.request.RegisterRequest;
import com.codegym.payload.response.JwtResponse;
import com.codegym.payload.response.MessageResponse;
import com.codegym.service.IEmployeeService;
import com.codegym.service.IRoleService;
import com.codegym.service.IUserService;
import com.codegym.service.impl.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/public")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SecurityController {
    @Autowired
    private JwtUtility jwtUtility;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private IUserService iUserService;
    @Autowired
    private IRoleService iRoleService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private IEmployeeService iEmployeeService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtility.generateJwtToken(loginRequest.getUsername());
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        Optional<User> user = iUserService.findByUsername(loginRequest.getUsername());
        Optional<Employee> employee = iEmployeeService.findByUserId(user.get().getId());
        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), roles, employee.get()));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Validated @RequestBody RegisterRequest registerRequest) {
        if (iUserService.existsByUsername(registerRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!!!"));
        }
        User user = new User(registerRequest.getUsername(), passwordEncoder.encode(registerRequest.getPassword()));
        String stringRoles = registerRequest.getRole();
        Set<Role> roles = new HashSet<>();
        if (stringRoles == null) {
            Role userRole = iRoleService.findByName("ROLE_USER")
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
            roles.add(userRole);
        } else {
            switch (stringRoles) {
                case "admin":
                    Role adminRole = iRoleService.findByName("ROLE_ADMIN")
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                    Role userRole = iRoleService.findByName("ROLE_USER")
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                    roles.add(adminRole);
                    roles.add(userRole);
                    break;
                case "user":
                    Role userRole1 = iRoleService.findByName("ROLE_USER")
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                    roles.add(userRole1);
                    break;
            }
        }
        user.setRoles(roles);
        iUserService.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully"));
    }
}
