package epam.com.springBoot.controller;

import epam.com.springBoot.controller.assembler.UserAssembler;
import epam.com.springBoot.controller.model.UserModel;
import epam.com.springBoot.dto.UserDTO;
import epam.com.springBoot.dto.group.OnCreate;
import epam.com.springBoot.dto.group.OnUpdate;
import epam.com.springBoot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Validated
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserAssembler userAssembler;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserModel createUser(@RequestBody @Validated(OnCreate.class) UserDTO dto) {
        return userAssembler.toModel(userService.createUser(dto));
    }



    @PatchMapping("/{email}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UserModel update(@RequestBody @Validated(OnUpdate.class) UserDTO dto, @PathVariable String email) {
        return userAssembler.toModel(userService.update(dto, email));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> delete(@PathVariable String email) {
        userService.deleteByEmail(email);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{email}")
    @ResponseStatus(HttpStatus.OK)
    public UserModel getUserByEmail(@PathVariable String email) {
        return userAssembler.toModel(userService.getByEmail(email));
    }

    //    @GetMapping
//    @ResponseStatus(HttpStatus.ACCEPTED)
//    public List<User> findAll() {
//        return userService.findAll();
//    }

}
