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




}
