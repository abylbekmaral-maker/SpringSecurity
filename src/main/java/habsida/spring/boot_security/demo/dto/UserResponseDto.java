package habsida.spring.boot_security.demo.dto;

import java.util.Set;

public class UserResponseDto {

    private Long id;

    private String name;

    private String lastName;

    private int age;

    private String username;

    private Set<String> roles;

    public UserResponseDto() {
    }

    public UserResponseDto(Long id, String name,
                           String lastName, int age,
                           String username, Set<String> roles) {

        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.username = username;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getUsername() {
        return username;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}