// package com.example.demo.controller;

// import java.io.IOException;

// import org.springframework.http.HttpStatus;
// import org.springframework.http.MediaType;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.multipart.MultipartFile;

// import com.example.demo.services.ImagenService;

// import lombok.RequiredArgsConstructor;

// @RestController
// @RequestMapping("/imagen")
// @RequiredArgsConstructor
// public class ImagenController {
    
//     private final ImagenService imagenService;
    
//     @PostMapping("/upload")
//     public ResponseEntity<?> uploadImagen(@RequestParam("file") MultipartFile file, @RequestParam("generateName") String generateName) throws IOException {
//         String uploadImagen = imagenService.uploadImagen(file, generateName);
//         return ResponseEntity.status(HttpStatus.OK).body(uploadImagen);
//     }

//     @GetMapping("/download/{filename}")
//     public ResponseEntity<?> downloadImagen(@PathVariable("filename") String filename) throws IOException {
//         try {
//             byte[] downloadImagen = imagenService.downloadImagen(filename);
//         return ResponseEntity.status(HttpStatus.OK)
//         .contentType(MediaType.valueOf("image/png"))
//         .body(downloadImagen);
//     } catch (Exception e) {
//         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage()+"zzzzzzzzzzzzzzzzzzz");
//     }
//     }

//     @GetMapping("/todas")
//     public ResponseEntity<?> todasLasImagenes() throws IOException {
//         try {
//         return ResponseEntity.status(HttpStatus.OK)
//         .body(imagenService.findAll().stream().map(img -> "hol" + img.getGenerateName()));
//     } catch (Exception e) {
//         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage()+"zzzzzzzzzzzzzzzzzzz");
//     }
//     }

//     @DeleteMapping("/delete/{generateName}")
//     public ResponseEntity<?> deleteImagen(@PathVariable("generateName") String generateName) throws IOException {
//         try {
//             String deleteImagen = imagenService.deleteImagen(generateName);
//             if(deleteImagen.equals("Deleted")) {
//                 return ResponseEntity.status(HttpStatus.OK).body("Imagen eliminada");
//             }
//             throw new RuntimeException("File not found: " + generateName);
       
//     } catch (Exception e) {
//         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
//     }
//     }
// }
