package ru.vapima.butjet4.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.vapima.butjet4.model.db.Account;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findAllByUserId(Long id, Pageable pageable);

    List<Account> findAllByUserId(Long id);

    @Query(value = "SELECT SUM(t1.amount) FROM account_records t1 " +
            "INNER JOIN (SELECT account_id, MAX(date_time) date_time FROM account_records GROUP BY account_id) t2 " +
            "ON t1.account_id=t2.account_id AND t1.date_time=t2.date_time " +
            "WHERE t2.account_id IN (SELECT id FROM accounts WHERE is_active=true AND user_id=3) ",
            nativeQuery = true)
    Long getSumAmountAccountsByUserIdAAndIsActive(Long id, Boolean isActive);


    Account findByIdAndUserId(Long id, Long user_id);
}
