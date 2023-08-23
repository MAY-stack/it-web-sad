package com.it.web.sad.itwebsad.repository;
import com.it.web.sad.itwebsad.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
