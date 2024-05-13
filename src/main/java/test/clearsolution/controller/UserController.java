package test.clearsolution.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import test.clearsolution.dto.RequestUserDto;
import test.clearsolution.dto.UserDto;
import test.clearsolution.dto.UserSearchParameters;
import test.clearsolution.repository.UserRepository;
import test.clearsolution.service.UserService;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@RequestBody @Valid RequestUserDto dto) {
        return userService.create(dto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UserDto updateUser(@PathVariable Long id,
                              @RequestBody @Valid RequestUserDto dto) {
        return userService.update(id, dto);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UserDto updateFields(@PathVariable Long id,
                                @RequestBody Map<String, Object> fieldMap) {
        return userService.updateFields(id, fieldMap);
    }

    @GetMapping("/search")
    public List<UserDto> getAllByBirtDay(@Valid UserSearchParameters searchParameters) {
        return userService.search(searchParameters);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
}
