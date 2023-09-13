package tz.go.ega.shambamkononibackend.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tz.go.ega.shambamkononibackend.model.UserAccount;
import tz.go.ega.shambamkononibackend.payload.dto.LoginDto;
import tz.go.ega.shambamkononibackend.payload.dto.RegisterDto;
import tz.go.ega.shambamkononibackend.payload.response.LoginResponse;
import tz.go.ega.shambamkononibackend.payload.response.RegisterResponse;
import tz.go.ega.shambamkononibackend.payload.response.Response;
import tz.go.ega.shambamkononibackend.service.AnonymousService;
import tz.go.ega.shambamkononibackend.userserviceImpl.UserDetailsServiceImpl;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/")
public class AnonymousController {

    @Autowired
    private AnonymousService anonymousService;

    @Autowired
    private UserDetailsServiceImpl userAccountService;

    @PostMapping("auth/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        log.info("{} ++++ {}", loginDto.getPassword(), loginDto.getUsername());
        Response<LoginResponse> loginResponse = anonymousService.login(loginDto);
        return ResponseEntity.ok()
                .body(loginResponse);
    }

    @PostMapping("auth/register")
    public ResponseEntity<?> registerAccount(@RequestBody RegisterDto registerDto) {
        System.out.println("TUPO PAMOJA");
        Response<RegisterResponse> response = anonymousService.registerAccount(registerDto);
        return ResponseEntity.ok().body(response);
    }


    @GetMapping("all-user")
    public Response<List<UserAccount>> allAccounts() {
        return anonymousService.getRegisteredUserAccounts();
    }

}

