package ru.vapima.butjet4.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vapima.butjet4.model.db.Plan;

import java.util.List;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {

    List<Plan> findAllByUserId(Long id, Pageable pageable);

    List<Plan> findAllByUserId(Long id);

    Plan findByIdAndUserId(Long id, Long user_id);

}
