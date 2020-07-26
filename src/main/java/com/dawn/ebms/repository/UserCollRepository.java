package com.dawn.ebms.repository;

import com.dawn.ebms.entity.UserColl;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserCollRepository extends MongoRepository<UserColl, Integer> {
    Optional<UserColl> findUserCollByUserIdEquals(Integer userId);
}
