package test.clearsolution.mapper;

import org.springframework.stereotype.Component;
import test.clearsolution.dto.CreateRequestUserDto;
import test.clearsolution.dto.UserDto;
import test.clearsolution.model.User;
import java.lang.reflect.Field;
import java.util.Map;

@Component
public class UserMapper implements Mapper<CreateRequestUserDto, User, UserDto> {

    @Override
    public User toModel(CreateRequestUserDto dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setFirsName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setBirthday(dto.getBirthDay());
        user.setAddress(dto.getAddress());
        user.setPhone(dto.getPhone());
        return user;
    }

    @Override
    public UserDto toDto(User model) {
        return new UserDto(
                model.getId(),
                model.getEmail(),
                model.getFirsName(),
                model.getLastName(),
                model.getBirthday(),
                model.getAddress(),
                model.getPhone()
        );
    }

    @Override
    public CreateRequestUserDto updateByFields(User user, Map<String, Object> fieldMap) {
        CreateRequestUserDto createDto = new CreateRequestUserDto();
        createDto.setFirstName(user.getFirsName());
        createDto.setLastName(user.getLastName());
        createDto.setEmail(user.getEmail());
        createDto.setBirthDay(user.getBirthday());
        createDto.setPhone(user.getPhone());
        createDto.setAddress(user.getAddress());

        Class<? extends CreateRequestUserDto> createUserClass = createDto.getClass();

        fieldMap.forEach((key, value) -> {
            try {
                Field field = createUserClass.getDeclaredField(key);
                field.setAccessible(true);
                field.set(createDto, value);
                field.setAccessible(false);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });

        return createDto;
    }
}
