package test.clearsolution.mapper;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import test.clearsolution.dto.CreateRequestUserDto;
import test.clearsolution.dto.UserDto;
import test.clearsolution.model.User;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@RequiredArgsConstructor
class UserMapperTest {
    private final UserMapper mapper;

    @Test
    void merge_Ok() {
        CreateRequestUserDto user = new CreateRequestUserDto();
        user.setEmail("test@test.com");
        user.setFirstName("BOb");
        user.setLastName("Bobson");
        user.setBirthDay(LocalDate.of(1987, 05, 25));

        Map<String, Object> fields = new HashMap<>();
        fields.put("firstName", "Bob");
        fields.put("birthDay", LocalDate.of(2020, 05, 25));
//        UserMapper mapper = new UserMapper();
//        mapper.merge(user, fields);
    }

//    UserMapper userMapper = new UserMapper();
//    userMapper.merge();
//    mapper.


}