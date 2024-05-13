package test.clearsolution.service.impl;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import test.clearsolution.dto.RequestUserDto;
import test.clearsolution.dto.UserDto;
import test.clearsolution.dto.UserSearchParameters;
import test.clearsolution.exception.EntityNotFoundException;
import test.clearsolution.mapper.UserMapper;
import test.clearsolution.model.User;
import test.clearsolution.repository.UserRepository;
import test.clearsolution.service.UserService;
import java.util.List;
import java.util.Map;

@Validated
@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto create(RequestUserDto createRequestUserDto) {
        return userMapper.toDto(
                userRepository.save(
                        userMapper.toModel(createRequestUserDto)));
    }

    @Override
    public Boolean isIsset(String email) {
        return userRepository.findByEmail(email).isEmpty();
    }

    @Override
    public UserDto update(Long id, RequestUserDto dto) {
        User user = findById(id);
        userMapper.mergeDtoToModel(dto, user);
        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public UserDto updateFields(Long id, Map<String, Object> fieldMap) {
        User user = findById(id);
        userMapper.mergeByFields(user, fieldMap);
        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public List<UserDto> search(UserSearchParameters searchParameters) {
        userRepository.findByBir
        return List.of();
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    private User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Didn't find User with id " + id));
    }
}
