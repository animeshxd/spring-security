package io.github.animeshxd.database;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.github.animeshxd.model.CustomUser;

@Repository
public interface UserRepository extends CrudRepository<CustomUser, String>{

}
