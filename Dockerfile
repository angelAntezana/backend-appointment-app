# Usa una imagen base de Java
FROM openjdk:21-jdk-slim

# Establece el directorio de trabajo
WORKDIR /app

# Copia el archivo JAR generado en la subcarpeta appointment-app/target
COPY appointment-app/target/*.jar app.jar

# Expone el puerto en el que escucha la app
EXPOSE 8080

# Comando para ejecutar tu aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]