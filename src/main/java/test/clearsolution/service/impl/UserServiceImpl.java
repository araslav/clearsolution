package test.clearsolution.service.impl;

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

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final Mapper<CreateRequestUserDto, User, UserDto> userMapper;

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
        return null;
    }

    private User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Didn't find User with id " + id));
    }
}
