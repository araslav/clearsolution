package test.clearsolution.validation.email;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import test.clearsolution.model.User;
import test.clearsolution.service.UserService;

@RequiredArgsConstructor
@Component
public class UniqueEmailValidator {
    private final UserService userService;

    public boolean validate(String email, User user) {
        if (user.getEmail().equals(email)) {
            return false;
        }

        return userService.isIsset(email);
    }

}
