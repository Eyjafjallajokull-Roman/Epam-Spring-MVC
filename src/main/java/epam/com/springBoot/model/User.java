package epam.com.springBoot.model;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String email;
    private String password;
    private String name;
    private String surname;
    @Enumerated(EnumType.STRING)
    private Role role;
    @ManyToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<Activity> activities;

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public User setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public User setRole(Role role) {
        this.role = role;
        return this;
    }

    public User setActivities(List<Activity> activities) {
        this.activities = activities;
        return this;
    }



}
