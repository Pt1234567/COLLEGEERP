package com.project.COLLEGEERP.controller;

import com.project.COLLEGEERP.Service.Impl.CustomUserDetailsService;
import com.project.COLLEGEERP.Service.AdminService;
import com.project.COLLEGEERP.config.JwtProvider;
import com.project.COLLEGEERP.config.SecurityConfig;
import com.project.COLLEGEERP.entities.User;
import com.project.COLLEGEERP.response.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

      @Autowired
      private AdminService adminService;

      @Autowired
      private CustomUserDetailsService customUserDetailsService;

      @Autowired
      private SecurityConfig securityConfig;




      @PostMapping("/signIn")
      public ResponseEntity<AuthResponse> login(@RequestBody User user){
          String userName= user.getUserName();
          String userPassword=user.getPassword();

          Authentication authentication=authenicate(userName,userPassword);
          SecurityContextHolder.getContext().setAuthentication(authentication);
          String jwt=JwtProvider.generateToken(authentication);

          User authUser=adminService.findUserByUserName(userName);

          AuthResponse authResponse=new AuthResponse();
          authResponse.setJwt(jwt);
          authResponse.setPasswordSet(true);
          authResponse.setMessage("Login Success");

          return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
      }

    private Authentication authenicate(String userName,String password){

        UserDetails userDetails= customUserDetailsService.loadUserByUsername(userName);

        if(userDetails==null){
            throw  new BadCredentialsException("Invalid");
        }
        if(!password.equals(userDetails.getPassword())){
            throw  new BadCredentialsException("Invalid password");
        }

        return new UsernamePasswordAuthenticationToken(userDetails,password,userDetails.getAuthorities());
    }


}
