package epam.com.springBoot.controller;


import epam.com.springBoot.controller.assembler.UserAssembler;
import epam.com.springBoot.controller.model.UserModel;
import epam.com.springBoot.dto.user.UserDTO;
import epam.com.springBoot.service.ActivityService;
import epam.com.springBoot.service.UserService;
import epam.com.springBoot.test.config.TestConfig;
import epam.com.springBoot.util.TestDataUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = UserController.class)
@Import({TestConfig.class, UserController.class})
@ContextConfiguration(classes = {PasswordEncoder.class, UserDetailsService.class})
@WebAppConfiguration
public class UserControllerTest {


    @MockBean
    private UserService userService;

    @MockBean
    private UserAssembler userAssembler;

    @MockBean
    private ActivityService activityService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(authorities = "ADMIN")
    void getUserByEmailTest() throws Exception {
        UserDTO userDTO = TestDataUtil.createUserDto();
        UserModel userModel = new UserModel(userDTO);
        when(userService.getByEmail("email@email.com")).thenReturn(userDTO);
        when(userAssembler.toModel(userDTO)).thenReturn(userModel);

        mockMvc.perform(get("/api/v1/users/" + "email@email.com")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.email").value("email@email.com"));
    }

    @Test
    @WithMockUser(authorities = "CLIENT")
    void createUserTest() throws Exception {
        UserDTO userDTO = TestDataUtil.createUserDto();
        UserModel userModel = new UserModel(userDTO);
        when(userService.createUser(userDTO)).thenReturn(userDTO);
        when(userAssembler.toModel(userDTO)).thenReturn(userModel);

        mockMvc.perform(post("/api/v1/users/create/").contentType("application/json").content(TestDataUtil.jsonMapper(userDTO))
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .with(csrf()))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    void updateUserTest() throws Exception {
        UserDTO userDTO = TestDataUtil.createUserDto();
        UserDTO userDTOUpdate = userDTO.setName("testt111").setPassword(null).setConfirmPassword(null);
        UserModel userModel = new UserModel(userDTO);
        when(userService.update(userDTO, userDTO.getEmail())).thenReturn(userDTOUpdate);
        when(userAssembler.toModel(userDTOUpdate)).thenReturn(userModel);

        mockMvc.perform(patch("/api/v1/users/" + TestDataUtil.TEST_EMAIL).contentType("application/json").content(TestDataUtil.jsonMapper(userDTOUpdate))
                        .with(csrf()))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.email").value(TestDataUtil.TEST_EMAIL));


    }

}
