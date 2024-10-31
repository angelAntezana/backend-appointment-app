// package com.example.demo.entity;

// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.Lob;
// import jakarta.persistence.Table;
// import lombok.AllArgsConstructor;
// import lombok.Builder;
// import lombok.Data;
// import lombok.NoArgsConstructor;

// @Entity
// @Table(name = "imagen")
// @Data
// @AllArgsConstructor
// @NoArgsConstructor
// @Builder
// public class Imagen {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;
    
//     private String name;
//     private String type;
//     private String generateName;
//     @Lob
//     @Column(name = "imagedata", length = Integer.MAX_VALUE)
//     private byte[] imageData;
// }
