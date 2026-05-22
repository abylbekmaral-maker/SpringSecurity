package habsida.spring.boot_security.demo.controller;

import habsida.spring.boot_security.demo.model.User;
import habsida.spring.boot_security.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserRestController {

    private UserService userService;

    public UserRestController(UserService userService){
        this.userService=userService;
    }


    @GetMapping("/api/users")
    public List<User> allU(){
        return userService.findAll();
    }

    @DeleteMapping("/api/users/{id}")
    public void deleteUser(@PathVariable Long id) {

        userService.deleteById(id);
    }

    @PostMapping("/api/users")
    public User addUser(@RequestBody User user) {

        userService.save(user);

        return user;
    }
}
