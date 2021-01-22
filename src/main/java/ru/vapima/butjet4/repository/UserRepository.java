package com.github.template.repository;

import com.github.template.model.db.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
   Boolean existsUserByEmail(String email);
}
