package com.app.repository;

import com.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for the User entity.
 *
 * This interface extends Spring Data JPA's JpaRepository, providing a full set of
 * standard CRUD (Create, Read, Update, Delete) operations for the User entity.
 * It abstracts the data access layer, allowing services to interact with the
 * user data in the database without writing boilerplate DAO code.
 *
 * The primary key for the User entity is of type Long.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // No custom query methods are needed at this time.
    // The default methods provided by JpaRepository (e.g., findById, save, findAll)
    // are sufficient for the current application requirements as seen in UserService.
}