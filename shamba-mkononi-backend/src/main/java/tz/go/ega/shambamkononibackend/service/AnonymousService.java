package tz.go.ega.shambamkononibackend.service;


import tz.go.ega.shambamkononibackend.model.UserAccount;
import tz.go.ega.shambamkononibackend.payload.dto.LoginDto;
import tz.go.ega.shambamkononibackend.payload.dto.RegisterDto;
import tz.go.ega.shambamkononibackend.payload.response.LoginResponse;
import tz.go.ega.shambamkononibackend.payload.response.RegisterResponse;
import tz.go.ega.shambamkononibackend.payload.response.Response;

import java.util.List;

public interface AnonymousService {

    Response<LoginResponse> login(LoginDto loginDto);
    Response<RegisterResponse> registerAccount(RegisterDto registerDto);
//    Response<RegisterResponse> changePassword(ResetPasswordDto resetPasswordDto);
    Response<List<UserAccount>> getRegisteredUserAccounts();

}
