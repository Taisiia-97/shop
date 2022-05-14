package com.taisiia.shop.controller;

import com.taisiia.shop.domain.dto.UserDto;
import com.taisiia.shop.mapper.UserMapper;
import com.taisiia.shop.service.UserService;
import com.taisiia.shop.validator.group.Create;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Validated
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public UserDto findUserById(@PathVariable Long id) {
        return userMapper.toDto(userService.findById(id));
    }

    @PostMapping
    @Validated(Create.class)
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto saveUser(@RequestBody @Valid UserDto user) {
        return userMapper.toDto(userService.save(userMapper.toDao(user)));
    }

    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated() && @securityService.hasAccessToUser(#id)")
    public UserDto updateUser(@RequestBody @Valid UserDto user, @PathVariable Long id) {
        return userMapper.toDto(userService.update(userMapper.toDao(user), id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Page<UserDto> getUserPage(@RequestParam int page, @RequestParam int size) {
        return userService.getPage(PageRequest.of(page, size)).map(userMapper::toDto);
    }

}
