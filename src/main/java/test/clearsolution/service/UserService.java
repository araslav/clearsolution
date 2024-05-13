package test.clearsolution.service;

import test.clearsolution.dto.RequestUserDto;
import test.clearsolution.dto.UserDto;
import test.clearsolution.dto.UserSearchParameters;

import java.util.List;
import java.util.Map;

public interface UserService {
    UserDto create(RequestUserDto createRequestUserDto);

    Boolean isIsset(String email);

    UserDto update(Long id, RequestUserDto dto);

    UserDto updateFields(Long id, Map<String, Object> fieldMap);

    List<UserDto> search(UserSearchParameters searchParameters);

    void deleteById(Long id);
}
