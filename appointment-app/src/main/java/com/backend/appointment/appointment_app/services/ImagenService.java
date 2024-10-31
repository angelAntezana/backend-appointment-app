// package com.example.demo.services;

// import java.io.IOException;
// import java.util.List;
// import java.util.Optional;
// import java.util.UUID;

// import org.springframework.stereotype.Service;
// import org.springframework.web.multipart.MultipartFile;

// import com.example.demo.entity.Imagen;
// import com.example.demo.repository.ImagenRepository;
// import com.example.demo.util.ImagenUtils;

// import lombok.RequiredArgsConstructor;

// @Service
// @RequiredArgsConstructor
// public class ImagenService {
    
//     private final ImagenRepository imagenRepository;

//     public String uploadImagen(MultipartFile file,String generateName) throws IOException {
//         Imagen imagen;
//         String url = "nada";
//         if(generateName.equals("no-image")) {
//             imagen = imagenRepository.save(
//                 Imagen.builder()
//                     .name(file.getOriginalFilename())
//                     .type(file.getContentType())
//                     .generateName(UUID.randomUUID().toString()+file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")))
//                     .imageData(ImagenUtils.compressImage(file.getBytes()))
//                     .build()
//             );
//             url = imagen.getGenerateName();
//         } else { 
//             imagen = imagenRepository.findByGenerateName(generateName).get();
//             imagen.setImageData(ImagenUtils.compressImage(file.getBytes()));
//             imagen.setGenerateName(imagen.getGenerateName().substring(0, imagen.getGenerateName().lastIndexOf("."))+file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")));
//             imagen.setName(file.getOriginalFilename());
//             imagen.setType(file.getContentType());
//             Imagen img2 = imagenRepository.save(imagen);
//             url = img2.getGenerateName();
//         }


//         if (url.equals("nada")) {
//             return null;
//         }
//         return url;
        
//     }


//     public byte[] downloadImagen(String filename) throws IOException {
//         Optional<Imagen> imagen = imagenRepository.findByGenerateName(filename);
//         try {
            
//         if (imagen.isEmpty()) {
//             System.out.println("File not found: " + filename);
//             throw new RuntimeException("File not found: " + filename);
//         }
//         byte[] images = ImagenUtils.decompressImage(imagen.get().getImageData());
//         return images;
//     } catch (Exception e) {
//         System.out.println("ttttttttttttttttt " + e.getMessage());
//         return null;
//     }
//     }

//     public String deleteImagen(String generateName) throws IOException {
//         try {
//             imagenRepository.deleteByGenerateName(generateName);
//         return "Deleted";
//         } catch (Exception e) {
//             System.out.println("ttttttttttttttttt " + e.getMessage());
//             return "Error";
//         }
//     }

//     public List<Imagen> findAll() throws IOException {
//         try {
//             return imagenRepository.findAll();
        
//         } catch (Exception e) {
//             System.out.println("ttttttttttttttttt " + e.getMessage());
//         }
//         return null;
//     }

// }
