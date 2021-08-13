package epam.com.springBoot.repository;

import epam.com.springBoot.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User getByEmail(String email);

    void delete(User user);

    Optional<User> findByEmail(String email);

    User findByName(String name);

    boolean existsByEmail(String email);

    List<User> findAll();

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "insert into users_activity (user_id, activity_id) values (?1,?2)")
    void addUserToActivity(Long userId, Long activityId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "delete from users_activity  where user_id =?1 and activity_id = ?2 ")
    void deleteUserFromActivity(Long userId, Long activityId);

//    @Query(nativeQuery = true, value = "select * from user u join users_activity ua on u.id=ua.user_id " +
//            "join activity a on a.id=ua.activity_id where ua.activity_id = ?1 or ?1 " +
//            " in (select * from user u " +
//            "join activity a on a.created_by_user_id = u.id where a.id = ?1)")
//    Page<User> findAllByActivitiesId(Long id, Pageable pageable);

    @Query(nativeQuery = true, value = "select * from user u where u.id in( " +
            " select u1.id from user u1 join users_activity ua on u1.id=ua.user_id " +
            " join activity a on a.id=ua.activity_id where ua.activity_id = ?1) " +
            " or u.id in( select u2.id from user u2" +
            " join activity a1 on a1.created_by_user_id = u2.id where a1.id = ?1)")
    Page<User> findAllUsersByActivityId(Long id, Pageable pageable);


}
