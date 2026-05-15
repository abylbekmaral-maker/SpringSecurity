package habsida.spring.boot_security.demo.configs;

import habsida.spring.boot_security.demo.model.Role;
import habsida.spring.boot_security.demo.model.User;
import habsida.spring.boot_security.demo.repository.RoleRepository;
import habsida.spring.boot_security.demo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {

        Role adminRole = new Role();
        adminRole.setName("ROLE_ADMIN");

        Role userRole = new Role();
        userRole.setName("ROLE_USER");

        roleRepository.save(adminRole);
        roleRepository.save(userRole);

        User admin = new User(
                "admin",
                passwordEncoder.encode("admin"),
                "Admin",
                "Admin",
                30
        );

        admin.setRoles(Set.of(adminRole));

        User user = new User(
                "user",
                passwordEncoder.encode("user"),
                "User",
                "User",
                25
        );

        user.setRoles(Set.of(userRole));

        userRepository.save(admin);
        userRepository.save(user);
    }
}