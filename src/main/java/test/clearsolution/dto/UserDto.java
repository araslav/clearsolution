package test.clearsolution.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record UserDto(
        Long id,
        String email,
        String firstName,
        String lastName,
        @JsonFormat(pattern = "dd-MM-yyyy")
        LocalDate birthDay,
        String address,
        String phone
) {
}
