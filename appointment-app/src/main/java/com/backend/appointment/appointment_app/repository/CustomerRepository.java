package com.backend.appointment.appointment_app.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.appointment.appointment_app.entity.Customer;


public interface CustomerRepository extends JpaRepository<Customer, Long>{
    
    Optional<Customer> findByEmail(String email);
    
    @Query("SELECT c FROM Customer c WHERE " +
       "LOWER(c.firstName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
       "LOWER(c.secondName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
       "LOWER(c.email) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
       "LOWER(c.phoneNumber) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    Page<Customer> findBySearchTerm(@Param("searchTerm") String searchTerm, Pageable pageable);

}
