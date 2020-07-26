package com.dawn.ebms.repository;

import com.dawn.ebms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAuthRepository extends JpaRepository<User, Integer> {
    User findByNameEquals(String username);
}
