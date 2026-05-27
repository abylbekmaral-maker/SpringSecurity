package habsida.spring.boot_security.demo.controller;

import habsida.spring.boot_security.demo.dto.UserRequestDto;
import habsida.spring.boot_security.demo.dto.UserResponseDto;
import habsida.spring.boot_security.demo.model.Role;
import habsida.spring.boot_security.demo.model.User;
import habsida.spring.boot_security.demo.service.UserService;
import org.springframework.web.bind.annotation.*;
import habsida.spring.boot_security.demo.repository.RoleRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    private final UserService userService;
    private final RoleRepository roleRepository;

    public UserRestController(UserService userService,   RoleRepository roleRepository){

        this.userService=userService;
        this.roleRepository = roleRepository;
    }


    @GetMapping
    public List<UserResponseDto> getAllUsers() {
        return userService.findAll()
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
    }

    private UserResponseDto convertToDto(User user) {

        Set<String> roles = user.getRoles()
                .stream()
                .map(Role::getRole)
                .collect(Collectors.toSet());
        return new UserResponseDto(
                user.getId(),
                user.getName(),
                user.getLastName(),
                user.getAge(),
                user.getUsername(),
                roles
        );
    }


    @PostMapping
    public UserResponseDto addUser(@RequestBody UserRequestDto dto) {
        User user = convertToEntity(dto);
        User savedUser = userService.save(user);
        return convertToDto(savedUser);
    }
    @GetMapping("/{id}")
    public UserResponseDto getUserById(@PathVariable Long id) {

        User user = userService.findById(id);

        return convertToDto(user);
    }



    @PutMapping("/{id}")
    public UserResponseDto updateUser(@PathVariable Long id,
                                      @RequestBody UserRequestDto dto) {

        User user = convertToEntity(dto);

        user.setId(id);

        User updatedUser = userService.save(user);

        return convertToDto(updatedUser);
    }


    private User convertToEntity(UserRequestDto dto) {

        User user = new User();

        user.setName(dto.getName());
        user.setLastName(dto.getLastName());
        user.setAge(dto.getAge());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());

        Set<Role> roles = dto.getRoleIds()
                .stream()
                .map(id -> roleRepository.findById(id).orElse(null))
                .collect(Collectors.toSet());

        user.setRoles(roles);

        return user;
    }

}
