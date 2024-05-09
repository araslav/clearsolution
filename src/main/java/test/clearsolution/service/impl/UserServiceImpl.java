package test.clearsolution.service.impl;


import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import test.clearsolution.mapper.Mapper;
import test.clearsolution.dto.CreateRequestUserDto;
import test.clearsolution.dto.UserDto;
import test.clearsolution.exception.EntityNotFoundException;
import test.clearsolution.model.User;
import test.clearsolution.repository.UserRepository;
import test.clearsolution.service.UserService;
import java.util.Map;
import java.util.Set;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final Mapper<CreateRequestUserDto, User, UserDto> userMapper;
    private final Validator validator;

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
    public UserDto update(Long id, CreateRequestUserDto dto) {
        User user = findById(id);
        user = userMapper.toModel(dto);
        return userMapper.toDto(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDto updateFields(Long id, Map<String, Object> fieldMap) {
        User user = findById(id);
        CreateRequestUserDto updatedUser = userMapper.updateByFields(user, fieldMap);

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
