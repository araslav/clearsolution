package test.clearsolution.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import test.clearsolution.dto.UserRequestDto;
import test.clearsolution.dto.UserDto;
import test.clearsolution.exception.CustomNoSuchFieldException;
import test.clearsolution.model.User;
import test.clearsolution.util.DateUtil;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class UserMapper {

    public User toModel(UserRequestDto dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setBirthDay(dto.getBirthDay());
        user.setAddress(dto.getAddress());
        user.setPhone(dto.getPhone());
        return user;
    }

    public UserDto toDto(User model) {
        return new UserDto(
                model.getId(),
                model.getEmail(),
                model.getFirstName(),
                model.getLastName(),
                model.getBirthDay(),
                model.getAddress(),
                model.getPhone()
        );
    }

    public void mergeDtoToModel(UserRequestDto dto, User user) {
        user.setEmail(dto.getEmail());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setAddress(dto.getAddress());
        user.setPhone(dto.getPhone());
//        return user;
    }

    public void mergeByFields(User user, Map<String, Object> fieldMap) {
        UserRequestDto requestUserDto = toRequestUserDto(user);

        Class<? extends UserRequestDto> createUserClass = requestUserDto.getClass();
        fieldMap.forEach((key, value) -> {
            try {
                Field field = createUserClass.getDeclaredField(key);
                field.setAccessible(true);
                field.set(requestUserDto, field.getName().equals("birthDay") ?
                        LocalDate.parse(value.toString(), DateTimeFormatter.ofPattern(DateUtil.getDatePattern())) : value);
                field.setAccessible(false);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new CustomNoSuchFieldException("Field " + key + " doesn't exist");
            }
        });

//        return mergeDtoToModel(requestUserDto, user);
    }

    public UserRequestDto toRequestUserDto(User user) {
        UserRequestDto createDto = new UserRequestDto();
        createDto.setFirstName(user.getFirstName());
        createDto.setLastName(user.getLastName());
        createDto.setEmail(user.getEmail());
        createDto.setBirthDay(user.getBirthDay());
        createDto.setPhone(user.getPhone());
        createDto.setAddress(user.getAddress());
        return createDto;
    }
}
