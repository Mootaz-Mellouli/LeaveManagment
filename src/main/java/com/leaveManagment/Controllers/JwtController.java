package com.leaveManagment.controllers;

import com.leaveManagment.dto.JwtResponse;
import com.leaveManagment.dto.LoginDTO;
import com.leaveManagment.services.Jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class JwtController {

    private final JwtService jwtService;

    @PostMapping({"/authenticate"})
    public JwtResponse createJwtToken(@RequestBody LoginDTO loginDTO) throws Exception {
        return jwtService.createJwtToken(loginDTO);
    }
}
