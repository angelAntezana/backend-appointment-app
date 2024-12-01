package com.backend.appointment.appointment_app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.appointment.appointment_app.entity.Token;

public interface TokenRepository extends JpaRepository<Token, Long>{
    
     @Query(value = """
      select t from Token t inner join User u\s
      on t.user.userId = u.userId\s
      where u.userId = :userId and (t.isExpired = false or t.isRevoked = false)\s
      """)
  List<Token> findAllValidTokenByUser(Long userId);

  Optional<Token> findByToken(String token);
}
