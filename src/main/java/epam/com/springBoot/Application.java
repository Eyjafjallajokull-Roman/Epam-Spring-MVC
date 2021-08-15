package epam.com.springBoot;

import epam.com.springBoot.dto.activity.ActivityAdminDTO;
import epam.com.springBoot.dto.user.UserDTO;
import epam.com.springBoot.service.ActivityService;
import epam.com.springBoot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Timestamp;


@SpringBootApplication
@Slf4j
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean(name = "application userService")
    public CommandLineRunner demoUser(UserService service) {
        return args -> {
            UserDTO userDto = new UserDTO()
                    .setName("TestUserF")
                    .setSurname("TestUserL")
                    .setEmail("email@email")
                    .setPassword("password")
                    .setConfirmPassword("password");
            log.info("Creating default user with email: {}", userDto.getEmail());
            service.createUser(userDto);
        };
    }

    @Bean(name = "application activityService")
    public CommandLineRunner demoUser(ActivityService service) {
        return args -> {
            ActivityAdminDTO activityAdminDTO = new ActivityAdminDTO()
                    .setId(300L)
                    .setTypeOfActivity("EVENT")
                    .setName("NAME")
                    .setDescriptionEng("DESCRIPTION_ENG")
                    .setDescriptionRus("DESCRIPTION_RU")
                    .setStartTime(Timestamp.valueOf("2022-12-12 01:02:03.123456789"))
                    .setEndTime(Timestamp.valueOf("2023-12-12 01:02:03.123456789"))
                    .setCreatedByUserId(300L);
            log.info("Creating default user with email: {}", activityAdminDTO.getId());

            service.createActivity(activityAdminDTO);
        };
    }


}
