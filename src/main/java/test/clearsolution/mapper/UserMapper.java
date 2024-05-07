package test.clearsolution.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import test.clearsolution.dto.CreateRequestUserDto;
import test.clearsolution.dto.UserDto;
import test.clearsolution.model.User;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

@Component
public class UserMapper implements Mapper<CreateRequestUserDto, User, UserDto> {
    @Override
    public User toModel(CreateRequestUserDto dto) {
        User user = new User();
        user.setEmail(dto.email());
        user.setFirsName(dto.firstName());
        user.setLastName(dto.lastName());
        user.setBirthday(dto.birthDay());
        user.setAddress(dto.address());
        user.setPhone(dto.phone());
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

    public CreateRequestUserDto merge(CreateRequestUserDto user, Map<String, Object> fieldMap) {
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.readValues(fieldMap, user);


        Class<? extends CreateRequestUserDto> userClass = user.getClass();

        fieldMap.forEach((key, value) -> {
            try {
                Field field = userClass.getDeclaredField(key);
                Annotation[] annotations = field.getAnnotations();
                for (Annotation annotation : annotations) {
                    Class<? extends Annotation> aClass = annotation.annotationType();
                    Method[] declaredMethods = aClass.getDeclaredMethods();
//                    for (Method declaredMethod : declaredMethods) {
////                        declaredMethod.invoke()
//                    }
                }
                field.setAccessible(true);
                field.set(user, value);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
        return user;
    }
}
