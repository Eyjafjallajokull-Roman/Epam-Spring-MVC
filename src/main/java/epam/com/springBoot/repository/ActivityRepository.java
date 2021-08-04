package epam.com.springBoot.repository;

import epam.com.springBoot.model.Activity;
import epam.com.springBoot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

    Optional<Activity> findById(Long id);

    boolean existsById(Long id);







}
