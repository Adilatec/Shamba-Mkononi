package tz.go.ega.shambamkononibackend.serviceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tz.go.ega.shambamkononibackend.jwt.JWTUtils;
import tz.go.ega.shambamkononibackend.model.UserAccount;
import tz.go.ega.shambamkononibackend.payload.dto.LoginDto;
import tz.go.ega.shambamkononibackend.payload.dto.RegisterDto;
import tz.go.ega.shambamkononibackend.payload.response.LoginResponse;
import tz.go.ega.shambamkononibackend.payload.response.RegisterResponse;
import tz.go.ega.shambamkononibackend.payload.response.Response;
import tz.go.ega.shambamkononibackend.repositories.UserAccountRepository;
import tz.go.ega.shambamkononibackend.service.AnonymousService;
import tz.go.ega.shambamkononibackend.userserviceImpl.UserDetailsServiceImpl;
import tz.go.ega.shambamkononibackend.util.ResponseCode;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
@RequiredArgsConstructor
public class AnonymousServiceImpl implements AnonymousService {
    private final AuthenticationManager authenticationManager;
    private final UserAccountRepository accountRepository;
    private final JWTUtils jwtUtils;
    private final UserDetailsServiceImpl detailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Response<LoginResponse> login(LoginDto loginDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));

            System.out.println("***********************");
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwtToken = jwtUtils.generateJwtToken(authentication);
            String refreshToken = jwtUtils.generateJwtRefreshToken(authentication);

            Optional<UserAccount> accountOptional = accountRepository.findByUsername(loginDto.getUsername());

            return getLoginResponseResponse(accountOptional, jwtToken, refreshToken);

        } catch (Exception e) {
            log.error(e.getMessage());
            return new Response<>(true, ResponseCode.FAIL, e.getMessage());
        }
    }

    @NotNull
    private Response<LoginResponse> getLoginResponseResponse(Optional<UserAccount> accountOptional, String jwtToken, String refreshToken) {
        if (accountOptional.isPresent()) {

            UserAccount account = accountOptional.get();
            LoginResponse response = new LoginResponse(
                jwtToken,
                refreshToken,
                    "Bearer",
                    account.getFullName(),
                    account.getPhone()
            );

            return new Response<>(false, ResponseCode.SUCCESS, response, null, "Login successfully");

        }

        return new Response<>(true, ResponseCode.FAIL, "Failed to login");
    }


    private boolean isValidEmail(String emailStr) {
        Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    private boolean isPhoneNumberValid(String phone){
        if (phone.matches("\\d{12}")) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isTinValid(String tinNo){
        if (tinNo.matches("\\d{9}")) {
           return true;
        } else {
            return false;
        }
    }

    @Override
    public Response<RegisterResponse> registerAccount(RegisterDto registerDto) {
        try {
            String checkTheRequired = "";

            String errorMessages = "";
            if (!isPhoneNumberValid(registerDto.getPhoneNumber())){
                errorMessages = errorMessages + "Phone Number Is Not Valid ie. Length Must Be Equal To 12 and Must Be Number \\n";
            }
            if (!isValidPassword(registerDto.getPassword())){
                errorMessages = errorMessages + "Weak Password \\n ";
            }

            Optional<UserAccount> accountOptional1 = accountRepository.findByPhone(registerDto.getPhoneNumber());
            if (accountOptional1.isPresent())
                return new Response<>(true, ResponseCode.PHONE_ALREADY_EXIST, "Phone number already exist");

            UserAccount account = new UserAccount();

            account.setUsername(registerDto.getUsername());
            account.setPhone(registerDto.getPhoneNumber());
            account.setFullName(registerDto.getFullName());
            account.setPassword(passwordEncoder.encode(registerDto.getPassword()));
            System.out.println("TUPO PAMOJA");
            accountRepository.save(account);

            return new Response<>(false, ResponseCode.SUCCESS, "Registered Successful");

        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new Response<>(true, ResponseCode.FAIL, ResponseCode.MSG_OPERATION_UNSUCCESSFUL);
    }



    @Override
    public Response<List<UserAccount>> getRegisteredUserAccounts() {
        return new Response<>(false, ResponseCode.SUCCESS, accountRepository.findAll());
    }


    private boolean isValidPassword(String passphrase) {
        String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(passphrase);
        return matcher.matches();
    }
}
