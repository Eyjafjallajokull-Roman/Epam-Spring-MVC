package epam.com.springBoot.controller;

import epam.com.springBoot.controller.assembler.ActivityAssembler;
import epam.com.springBoot.controller.model.ActivityModel;
import epam.com.springBoot.controller.model.UserModel;
import epam.com.springBoot.dto.activity.ActivityAdminDTO;
import epam.com.springBoot.dto.user.UserDTO;
import epam.com.springBoot.service.ActivityService;
import epam.com.springBoot.service.UserService;
import epam.com.springBoot.test.config.TestConfig;
import epam.com.springBoot.util.ActivityDataUtil;
import epam.com.springBoot.util.TestDataUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ActivityController.class)
@Import(TestConfig.class)
public class ActivityControllerTest {

    @MockBean
    ActivityService activityService;

    @MockBean
    private ActivityAssembler activityAssembler;

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;


    @Test
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
    void createActivityTest() throws Exception {
        ActivityAdminDTO dto = ActivityDataUtil.createActivityDto();
        ActivityModel model = new ActivityModel(dto);
        when(activityService.createActivity(dto)).thenReturn(dto);
        when(activityAssembler.toModel(dto)).thenReturn(model);

        mockMvc.perform(post("/api/v1/activities/").contentType("application/json").content(ActivityDataUtil.jsonMapper(dto)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }




}
