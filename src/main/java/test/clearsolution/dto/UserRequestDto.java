package test.clearsolution.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import test.clearsolution.validation.age.AgeGraterThan;
import test.clearsolution.validation.email.Email;

@Getter
@Setter
public class UserRequestDto {
        @Email
        private String email;
        @NotBlank(message = "First name can't be Null or Empty")
        private String firstName;
        @NotBlank(message = "Last name can't be Null or Empty")
        private String lastName;
        @AgeGraterThan
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
        private LocalDate birthDay;
        private String address;
        private String phone;
}
