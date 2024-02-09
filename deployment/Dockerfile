# Cree una nueva etapa de construcción a partir de una imagen base.
FROM openjdk:17-alpine@sha256:4b6abae565492dbe9e7a894137c966a7485154238902f2f25e9dbd9784383d81
# Opcional con valor por defecto, se usa:
# para Personalizar la construcción de la imagen,
# variables de tiempo de construcción.
ARG VAR_PROFILE=dev

# Se usa para configurar la aplicación
# funciona durante la compilación y ejecución
ENV PROFILE=${VAR_PROFILE}

# Cambiar directorio de trabajo.
WORKDIR /app

# Copiar archivos y directorios.
COPY ./applications/app-service/build/libs/users.jar users.jar

# Describe en qué puertos escucha tu aplicación.
EXPOSE 8081

# Especifique el ejecutable predeterminado.
ENTRYPOINT ["java", "-Dspring.profiles.active=${PROFILE}", "-jar", "users.jar"]