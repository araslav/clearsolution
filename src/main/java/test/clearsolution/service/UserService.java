package test.clearsolution.service;

import test.clearsolution.dto.CreateRequestUserDto;
import test.clearsolution.dto.UpdateRequestUserDto;
import test.clearsolution.dto.UserDto;

import java.util.Map;

public interface UserService {
    UserDto create(CreateRequestUserDto createRequestUserDto);

    Boolean isIsset(String email);

    UserDto update(Long id, UpdateRequestUserDto dto);

    void deleteById(Long id);

    UserDto updateFields(Long id, Map<String, Object> fieldMap);
}
