// package com.example.demo.repository;

// import java.util.Optional;

// import org.springframework.data.jpa.repository.JpaRepository;

// import com.example.demo.entity.Imagen;

// import jakarta.transaction.Transactional;

// public interface ImagenRepository extends JpaRepository<Imagen, Long> {
    
//     Optional<Imagen> findByName(String filename);

//     Optional<Imagen> findByGenerateName(String generateName);
//     @Transactional
//     void deleteByGenerateName(String generateName);
// }
