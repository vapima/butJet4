package ru.vapima.butjet4.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vapima.butjet4.model.db.Account;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findAllByUserId(Long id, Pageable pageable);
    List<Account> findAllByUserId(Long id);
}
