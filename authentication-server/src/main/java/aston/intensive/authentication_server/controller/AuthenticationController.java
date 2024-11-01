package aston.intensive.authentication_server.controller;

import aston.intensive.authentication_server.dto.UserDto;
import aston.intensive.authentication_server.service.api.AuthenticationService;
import aston.intensive.authentication_server.service.impl.AuthenticationServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    public final AuthenticationService authenticationService;

    public ResponseEntity<String> login(@RequestBody UserDto userDto){
        Object o = new Object();
        o.hashCode();
        o.equals(o);
        return ResponseEntity.ok("123");
    }
}
