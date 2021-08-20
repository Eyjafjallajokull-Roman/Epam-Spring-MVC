package epam.com.springBoot.controller;

import epam.com.springBoot.controller.assembler.ActivityAssembler;
import epam.com.springBoot.controller.model.ActivityModel;
import epam.com.springBoot.controller.model.UserModel;
import epam.com.springBoot.dto.activity.ActivityAdminDTO;
import epam.com.springBoot.dto.user.UserDTO;
import epam.com.springBoot.model.TypeOfActivity;
import epam.com.springBoot.service.ActivityService;
import epam.com.springBoot.service.UserService;
import epam.com.springBoot.test.config.TestConfig;
import epam.com.springBoot.util.ActivityDataUtil;
import epam.com.springBoot.util.TestDataUtil;
import org.junit.jupiter.api.BeforeAll;
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
import org.springframework.security.oauth2.common.util.JacksonJsonParser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;
import java.sql.Timestamp;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ActivityController.class)
@Import({TestConfig.class, ActivityController.class})
@ContextConfiguration(classes = {PasswordEncoder.class, UserDetailsService.class})

public class ActivityControllerTest {

    @MockBean
    ActivityService activityService;

    @MockBean
    private ActivityAssembler activityAssembler;

    @Autowired
    private MockMvc mockMvc;



    @Test
    @WithMockUser(authorities = "Client")
    void getActivityTest() throws Exception {
        ActivityAdminDTO activityAdminDTO = ActivityDataUtil.createActivityDto();
        ActivityModel activityModel = new ActivityModel(activityAdminDTO);
        when(activityService.getById(ActivityDataUtil.ID)).thenReturn(activityAdminDTO);
        when(activityAssembler.toModel(activityAdminDTO)).thenReturn(activityModel);

        mockMvc.perform(get("/api/v1/activities/" + ActivityDataUtil.ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(TestDataUtil.ID));
    }

    @Test
    @WithMockUser(authorities = "CLIENT")
    void createActivityTest() throws Exception {
        ActivityAdminDTO dto = ActivityDataUtil.createActivityDto();
        ActivityModel model = new ActivityModel(dto);
        when(activityService.createActivity(dto)).thenReturn(dto);
        when(activityAssembler.toModel(dto)).thenReturn(model);

        mockMvc.perform(post("/api/v1/activities/").contentType("application/json")
                        .content(ActivityDataUtil.jsonMapper(dto))
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
        ActivityAdminDTO dto = ActivityDataUtil.createActivityDto();
        ActivityModel model = new ActivityModel(dto);
        when(activityService.updateActivity(dto, dto.getId())).thenReturn(dto);
        when(activityAssembler.toModel(dto)).thenReturn(model);

        mockMvc.perform(patch("/api/v1/activities/" + ActivityDataUtil.ID).contentType("application/json")
                        .content(ActivityDataUtil.jsonMapper(dto))
                        .with(csrf())
                        .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(ActivityDataUtil.ID));


    }



}
