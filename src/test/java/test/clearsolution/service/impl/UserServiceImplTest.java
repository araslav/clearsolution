package test.clearsolution.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import test.clearsolution.dto.UserDto;
import test.clearsolution.dto.UserRequestDto;
import test.clearsolution.mapper.UserMapper;
import test.clearsolution.model.User;
import test.clearsolution.repository.UserRepository;
import java.time.LocalDate;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.assertArg;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    @DisplayName("Verify create methode works")
    public void save_ValidateCreateUserRequestDto_ReturnsUserDto() {
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setEmail("test@test.com");
        userRequestDto.setFirstName("Bob");
        userRequestDto.setLastName("Bobson");
        userRequestDto.setBirthDay(LocalDate.of(1990, 1, 1));

        User user = new User();
        user.setEmail(userRequestDto.getEmail());
        user.setFirstName(userRequestDto.getFirstName());
        user.setLastName(userRequestDto.getLastName());
        user.setBirthDay(userRequestDto.getBirthDay());

        UserDto userDto = new UserDto(user.getId(), user.getEmail(), user.getFirstName(),
                user.getLastName(), user.getBirthDay(), null, null);

        when(userMapper.toModel(userRequestDto)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.toDto(user)).thenReturn(userDto);

        UserDto savedUserDto = userService.save(userRequestDto);

        Assertions.assertEquals(savedUserDto, userDto);
    }

    @Test
    @DisplayName("Should return False if user isset in data base.")
    void findUserByEmailBoolean_Ok() {
        String email = "test@test.com";
        User user = new User();
        user.setId(1L);
        user.setEmail(email);
        user.setFirstName("Bob");
        user.setLastName("Bobson");
        user.setBirthDay(LocalDate.of(1990, 1, 1));

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        Boolean actual = userService.isIsset(email);

        Boolean expected = false;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should return True if user not isset in data base.")
    void findUserByEmailBoolean_Not_Ok() {
        String userEmail = "test@test.com";
        String searchEmail = "test2@test.com";
        User user = new User();
        user.setId(1L);
        user.setEmail(userEmail);
        user.setFirstName("Bob");
        user.setLastName("Bobson");
        user.setBirthDay(LocalDate.of(1990, 1, 1));

        when(userRepository.findByEmail(searchEmail)).thenReturn(Optional.empty());
        Boolean actual = userService.isIsset(searchEmail);

        Boolean expected = true;
        Assertions.assertEquals(expected, actual);
    }
}