package com.codewithmosh.store.controllers;

import com.codewithmosh.store.dtos.UserDto;
import com.codewithmosh.store.mappers.UserMapper;
import com.codewithmosh.store.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @GetMapping
    public Iterable<UserDto> getAllUsers(
            @RequestParam(required = false, defaultValue = "", name = "sort") String sort
    ){
    if (!Set.of("name", "email").contains(sort))
        sort = "name";

    return userRepository.findAll(Sort.by(sort).descending())
    .stream()
            .map(userMapper::toDto)
    .toList();
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id){
        var user = userRepository.findById(id).orElse(null);

        if(user == null){
            return ResponseEntity.notFound().build();
        }
//        return new ResponseEntity<>(user, HttpStatus.OK);
//        var userDto = new UserDto(user.getId(), user.getName(), user.getEmail());
        return ResponseEntity.ok(userMapper.toDto(user));

    }
    @PostMapping
    public UserDto createUser(@RequestBody UserDto data){
        return data;
    }
@DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        var user = userRepository.findById(id).orElse(null);

        if(user == null){
            return ResponseEntity.notFound().build();
        }
        userRepository.delete(user);
        return ResponseEntity.noContent().build();

    }
@PostMapping("/{id}/change-password")
    public ResponseEntity<Void> changePasword(
            @PathVariable Long id,
            @RequestBody ChangePasswordRequest request){
    var user = userRepository.findById(id).orElse(null);

    if(user == null){
        return ResponseEntity.notFound().build();
    }
    if (!user.getPassword().equals(request.getOldPassword())){
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    user.getPassword(request.getNewPassword());
    userRepository.save(user);
//        userRepository.delete(user);
    return ResponseEntity.noContent().build();

    }
}
