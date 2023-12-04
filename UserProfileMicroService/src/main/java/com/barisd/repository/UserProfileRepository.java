package com.barisd.repository;

import com.barisd.repository.entity.UserProfile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends MongoRepository<UserProfile,String> {
}
