package tz.go.ega.shambamkononibackend.payload.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tz.go.ega.shambamkononibackend.model.Fertilizer;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {
    private String fullName;
    private String username;
    private String phoneNumber;
    private String password;
}