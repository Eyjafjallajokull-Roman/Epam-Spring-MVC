package epam.com.springBoot.model;


import lombok.Data;


import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;
    private String password;
    private String name;
    private String surname;
    @Enumerated(EnumType.STRING)
    private Role role;
    @ManyToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<Activity> activities;
}
