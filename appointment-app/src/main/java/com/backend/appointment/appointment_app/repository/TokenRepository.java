package com.backend.appointment.appointment_app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.appointment.appointment_app.entity.Token;

public interface TokenRepository extends JpaRepository<Token, Long>{
    
     @Query(value = """
      select t from Token t inner join User u\s
      on t.user.user_id = u.user_id\s
      where u.user_id = :userId and (t.expired = false or t.revoked = false)\s
      """)
  List<Token> findAllValidTokenByUser(Long userId);

  Optional<Token> findByToken(String token);
}
