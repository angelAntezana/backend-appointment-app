# Usa una imagen base de Java
FROM openjdk:21-jdk-slim

# Establece el directorio de trabajo en el contenedor
WORKDIR /app

# Copia el archivo JAR generado al contenedor
COPY target/*.jar app.jar

# Expone el puerto en el que tu aplicación escucha
EXPOSE 8080

# Comando para ejecutar tu aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]