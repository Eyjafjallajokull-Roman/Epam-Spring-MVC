package epam.com.springBoot.repository;

import epam.com.springBoot.model.Activity;
import epam.com.springBoot.model.Status;
import epam.com.springBoot.model.TypeOfActivity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

    Optional<Activity> findById(Long id);

    List<Activity> findActivitiesByStatus(Status status);

    boolean existsById(Long id);

    Page<Activity> findActivitiesByTypeOfActivityAndStatus(TypeOfActivity typeOfActivity, Status status, Pageable pageable);

    Page<Activity> findActivitiesByStatus(Status status, Pageable pageable);

    Page<Activity> findActivitiesByCreatedByUserIdAndStatus(Long createdId, Status status, Pageable pageable);

    @Query(nativeQuery = true, value = "select * from activity a left join users_activity ua on a.id = ua.activity_id " +
            "left join user u on u.id = ua.user_id where (u.id = ?1 or a.created_by_user_id = ?1) and a.status = ?2")
    Page<Activity> findActivitiesByCreatedByUserEmailOrUserId(Long userId, String status, Pageable pageable);

    @Query(nativeQuery = true, value = "select * from activity a left join users_activity ua on a.id = ua.activity_id " +
            "left join user u on u.id = ua.user_id where (u.id = ?1 or a.created_by_user_id = ?1) " +
            "and a.type_of_activity = ?2 and a.status =?3 ")
    Page<Activity> findActivitiesByCreatedByUserIdOrUserIdAndTypeOfActivity(Long userId, String typeOfActivity,
                                                                            String status, Pageable pageable);

}
