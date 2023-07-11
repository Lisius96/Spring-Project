package org.example.springsecurityclient.Repository;

import org.example.springsecurityclient.Entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
