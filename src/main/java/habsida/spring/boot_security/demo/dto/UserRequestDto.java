package habsida.spring.boot_security.demo.dto;

import java.util.Set;

public class UserRequestDto {

    private String name;
    private String lastName;
    private int age;
    private String username;
    private String password;
    private Set<Long> roleIds;

    public UserRequestDto() {
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

    public String getPassword() {
        return password;
    }

    public Set<Long> getRoleIds() {
        return roleIds;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoleIds(Set<Long> roleIds) {
        this.roleIds = roleIds;
    }
}