package epam.com.springBoot.scheduler;

import epam.com.springBoot.model.Activity;
import epam.com.springBoot.model.Status;
import epam.com.springBoot.repository.ActivityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Component
@Slf4j
public class ActivityStatusScheduler {
    @Autowired
    ActivityRepository activityRepository;


    @Scheduled(fixedDelay = 1000000L)
    public void SetStatusForPassedActivities() {
        log.info("Scheduler working");
        List<Activity> all = activityRepository.findActivitiesByStatus(Status.ACCEPT);
        all.forEach(a -> {
            if (a.getEndTime() != null) {
                int change = a.getEndTime().compareTo(Timestamp.valueOf(LocalDateTime.now()));
                if (change <= 0) {
                    a.setStatus(Status.FINISHED);
                    activityRepository.save(a);
                    log.info("Scheduler do something");
                }
            }
        });
    }
}
