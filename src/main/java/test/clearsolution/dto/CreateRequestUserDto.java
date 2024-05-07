package test.clearsolution.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import test.clearsolution.validation.age.AgeGraterThan;
import test.clearsolution.validation.email.Email;
import test.clearsolution.validation.email.UniqueEmail;

public record CreateRequestUserDto(
        @Email
        @UniqueEmail
        String email,
        @NotBlank(message = "First name can't be Null or Empty")
        String firstName,
        @NotBlank(message = "Last name can't be Null or Empty")
        String lastName,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
        @AgeGraterThan
        LocalDate birthDay,
        String address,
        String phone
) {
}
