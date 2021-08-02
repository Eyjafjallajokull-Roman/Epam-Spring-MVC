package epam.com.springBoot.controller;

import epam.com.springBoot.dto.UserDTO;
import epam.com.springBoot.dto.group.OnCreate;
import epam.com.springBoot.dto.group.OnUpdate;
import epam.com.springBoot.model.User;
import epam.com.springBoot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Validated
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User save(@RequestBody @Validated(OnCreate.class) UserDTO dto) {
        return userService.save(dto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User getUser(@PathVariable Long id) {
        return userService.getById(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public User update(@RequestBody @Validated(OnUpdate.class) UserDTO dto) {
        return userService.update(dto);
    }


}
