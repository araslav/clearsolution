package test.clearsolution.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import test.clearsolution.model.User;
import test.clearsolution.repository.UserRepository;
import test.clearsolution.service.UserService;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    @DisplayName("Should return False if user isset in data base.")
    void findUserByEmailBoolean_Ok() {
        String email = "test@test.com";
        User user = new User();
        user.setId(1L);
        user.setEmail(email);
        user.setFirsName("Bob");
        user.setLastName("Bobson");
        user.setBirthday(LocalDate.of(1990, 1, 1));

        Mockito.when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        Boolean actual = userService.isIsset(email);

        Boolean expected = false;
        Assertions.assertEquals(expected, actual);
    }
}