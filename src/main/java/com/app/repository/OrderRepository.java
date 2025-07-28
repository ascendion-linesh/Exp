package com.app.repository;

import com.app.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for the Order entity.
 *
 * This interface extends Spring Data JPA's JpaRepository, which provides
 * out-of-the-box functionality for data access operations such as saving,
 * deleting, and finding Order entities. This simplifies the data persistence
 * layer of the application.
 *
 * The primary key for the Order entity is of type Long.
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // No custom query methods are required for the Order entity at this stage.
    // The standard save() method provided by JpaRepository is used by the
    // OrderService to persist new orders.
}