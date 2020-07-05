package ir.parto.crm.modules.authorization.controller;


import ir.parto.crm.modules.admin.model.entity.Admin;
import ir.parto.crm.modules.admin.model.entity.AdminRole;
import ir.parto.crm.modules.admin.model.repository.AdminRepository;
import ir.parto.crm.modules.admin.model.repository.AdminRoleRepository;
import ir.parto.crm.modules.authenticate.controller.exception.AppException;
import ir.parto.crm.modules.authorization.model.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    AdminRoleRepository adminRoleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser( @RequestBody LoginRequest loginRequest) {
        System.out.println(loginRequest.getPassword()+"---------"+passwordEncoder.encode(loginRequest.getPassword()));
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        passwordEncoder.encode(loginRequest.getPassword())
                )
        );
        System.out.println("------60---------");
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser( @RequestBody Admin admin) {
        if(adminRepository.existsByUsername(admin.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        Admin adminSet = new Admin(admin.getFirstName(), admin.getUsername(),
                admin.getPassword());

        admin.setPassword(passwordEncoder.encode(admin.getPassword()));

        AdminRole adminRole = adminRoleRepository.findById(admin.getAdminRole().getAdminRoleId())
                .orElseThrow(() -> new AppException("ADmin Role not set."));

        admin.setAdminRole(adminRole);

        Admin result = adminRepository.save(admin);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }
}