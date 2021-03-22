package com.andrei.springbank.user.query.api.repositories;

import com.andrei.springbank.user.core.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
