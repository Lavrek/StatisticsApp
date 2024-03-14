package com.statistics.controller;


import com.statistics.LoginMessage;
import com.statistics.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import statistics.LoginDto;

@RestController
@CrossOrigin
@RequestMapping("/statistics/security/login")
public class SecurityController {
    @Autowired
    SecurityService securityService;

    @CrossOrigin(methods = {RequestMethod.POST})
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> loginEmployee(@RequestBody LoginDto loginDto)
    {
        LoginMessage loginMessage = securityService.loginSecurity(loginDto);
        return ResponseEntity.ok(loginMessage);
    }
}
