package test.clearsolution.service.impl;


import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import test.clearsolution.dto.UpdateRequestUserDto;
import test.clearsolution.dto.CreateRequestUserDto;
import test.clearsolution.dto.UserDto;
import test.clearsolution.exception.CustomValidationException;
import test.clearsolution.exception.EntityNotFoundException;
import test.clearsolution.mapper.UserMapper;
import test.clearsolution.model.User;
import test.clearsolution.repository.UserRepository;
import test.clearsolution.service.UserService;
import test.clearsolution.validation.email.UpdateUniqueEmailValidator;
import java.util.Map;
import java.util.Set;

@Validated
@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final Validator validator;
    private final UpdateUniqueEmailValidator emailValidator;

    @Override
    public UserDto create(CreateRequestUserDto createRequestUserDto) {
        return userMapper.toDto(
                userRepository.save(
                        userMapper.toModel(createRequestUserDto)));
    }

    @Override
    public Boolean isIsset(String email) {
        return userRepository.findByEmail(email).isEmpty();
    }

    @Override
    public UserDto update(Long id, UpdateRequestUserDto dto) {
        User user = findById(id);

        if (!emailValidator.isValid(user, dto.getEmail())) {
            throw new CustomValidationException("Email already exists", e);
        }

        userRepository.save(userMapper.mergeDtoToModel(dto, user));
        return userMapper.toDto(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDto updateFields(Long id, Map<String, Object> fieldMap) {
        User user = findById(id);
        CreateRequestUserDto updatedUser = userMapper.mergeByFields(user, fieldMap);

        Set<ConstraintViolation<CreateRequestUserDto>> violations = validator.validate(updatedUser);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

//        return userMapper.toDto(updatedUser);
        return null;
    }

    private User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Didn't find User with id " + id));
    }
}
