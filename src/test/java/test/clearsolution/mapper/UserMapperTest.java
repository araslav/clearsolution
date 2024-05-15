package test.clearsolution.mapper;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import test.clearsolution.dto.UserRequestDto;
import test.clearsolution.model.User;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class UserMapperTest {
    private final UserMapper mapper = new UserMapper();

//    @Test
//    void merge_Ok() {
//        UserRequestDto user = new UserRequestDto();
//        user.setEmail("test@test.com");
//        user.setFirstName("BOb");
//        user.setLastName("Bobson");
//        user.setBirthDay(LocalDate.of(1987, 05, 25));
//
//        Map<String, Object> fields = new HashMap<>();
//        fields.put("firstName", "Bob");
//        fields.put("birthDay", LocalDate.of(2020, 05, 25));
//    }

    @Test
    void mergeUserDtoToUserModel_Ok() {
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setFirstName("Bob");
        userRequestDto.setLastName("Bobson");
        userRequestDto.setEmail("test2@test.com");
        userRequestDto.setBirthDay(LocalDate.of(1997, 2, 2));
        userRequestDto.setPhone("+380931112233");
        userRequestDto.setAddress("Odessa, str. Big Arnautska, 4b");

        User user = new User();
        user.setId(1L);
        user.setFirstName("Bob");
        user.setLastName("Bobson");
        user.setEmail("test@test.com");
        user.setBirthDay(LocalDate.of(2007, 12, 23));

        mapper.mergeDtoToModel(userRequestDto, user);

        Assertions.assertEquals(userRequestDto.getFirstName(), user.getFirstName());
        Assertions.assertEquals(userRequestDto.getLastName(), user.getLastName());
        Assertions.assertEquals(userRequestDto.getEmail(), user.getEmail());
        Assertions.assertEquals(userRequestDto.getBirthDay(), user.getBirthDay());
        Assertions.assertEquals(userRequestDto.getAddress(), user.getAddress());
        Assertions.assertEquals(userRequestDto.getPhone(), user.getPhone());
    }



//    UserMapper userMapper = new UserMapper();
//    userMapper.merge();
//    mapper.

}