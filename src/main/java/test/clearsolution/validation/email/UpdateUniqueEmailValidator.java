package test.clearsolution.validation.email;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import test.clearsolution.model.User;
import test.clearsolution.service.UserService;

@Component
public class UpdateUniqueEmailValidator {
    private final UserService userService;

    public UpdateUniqueEmailValidator(@Lazy UserService userService) {
        this.userService = userService;
    }

    public boolean isValid(User user, String email) {
        if (user.getEmail().equals(email)) {
            return true;
        }

        return userService.isIsset(email);
    }
}
