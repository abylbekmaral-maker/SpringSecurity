package habsida.spring.boot_security.demo.controller;

import habsida.spring.boot_security.demo.model.User;
import habsida.spring.boot_security.demo.repository.RoleRepository;
import habsida.spring.boot_security.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import habsida.spring.boot_security.demo.model.Role;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class UserController {

    private final UserService userService;
    private final RoleRepository roleRepository;

    public UserController(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @GetMapping
    public String getAllUsers(Model model) {
        User user = new User();

        user.setUsername("");
        user.setPassword("");

        model.addAttribute("users", userService.findAll());
        model.addAttribute("user", new User());
        return "index";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute User user, @RequestParam("roles") Long[] roleIds) {
        Set<Role> roles = new HashSet<>();

        for (Long roleId : roleIds) {
            habsida.spring.boot_security.demo.model.Role role = roleRepository.findById(roleId).orElse(null);

            if (role != null) {
                roles.add(role);
            }
        }
        user.setRoles(roles);
        userService.save(user);

        return "redirect:/admin";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }

    @GetMapping("/edit")
    public String editUser(@RequestParam("id") Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        model.addAttribute("users", userService.findAll());
        return "index";
    }
}