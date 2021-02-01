package ru.vapima.butjet4.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.vapima.butjet4.model.db.Account;
import ru.vapima.butjet4.model.db.AccountRecord;

import java.util.List;

@Repository
public interface AccountRecordRepository extends JpaRepository<AccountRecord, Long> {
    List<AccountRecord> findAllByAccountId(Long id, Pageable pageable);
    List<AccountRecord> findAllByAccountId(Long id);

    @Transactional
    void deleteAccountRecordByAccountId(Long id);

    @Query(value = "SELECT amount FROM account_records WHERE account_id=:id ORDER BY date_time DESC LIMIT 1",
    nativeQuery = true)
    Long getAmountFromLastRecordByAccountId(Long id);

    AccountRecord findByIdAndAccountId(Long id, Long account_id);
}
