package epam.com.springBoot.model;


import lombok.Data;


import java.util.Set;

@Data
public class User {
    private int id;
    private String email;
    private String password;
    private String name;
    private String surname;
    private Role role;
    private Set<Integer> activitiesId;
}
