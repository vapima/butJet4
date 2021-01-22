package ru.vapima.butjet4.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.vapima.butjet4.model.db.AccountRecord;

import java.util.List;

@Repository
public interface AccountRecordRepository extends JpaRepository<AccountRecord, Long> {
    List<AccountRecord> findAllByAccountId(Long id, Pageable pageable);

    @Transactional
    void deleteAccountRecordByAccountId(Long id);
}
