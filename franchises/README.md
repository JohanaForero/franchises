Proyecto API Spring Boot con WebFlux y Java 21
Este proyecto es una API construida con Spring Boot utilizando WebFlux para soporte reactivo, Java 21 como versión de
JDK y Gradle como herramienta de construcción.

Este archivo proporciona las instrucciones necesarias para limpiar, construir y arrancar el proyecto con BootRoom.

Requisitos previos
Antes de comenzar, asegúrate de tener instalado lo siguiente en tu máquina:

JDK 21 o superior

Gradle

Docker (opcional para ejecución en contenedor)

Instrucciones

1. Limpiar el Proyecto (Clean)
   Primero, asegúrate de que el proyecto esté limpio y no contenga archivos generados previamente. Para limpiar el
   proyecto, ejecuta el siguiente comando:

bash
gradle clean

2. Construir el Proyecto (Build)
   Después de limpiar el proyecto, construye el proyecto para compilar el código y generar el archivo JAR. Ejecuta el
   siguiente comando:

bash
gradle build

3. Arrancar el Proyecto con BootRoom
   Una vez que el proyecto esté construido, puedes arrancarlo utilizando BootRoom. Asegúrate de que Docker esté
   instalado y en funcionamiento si deseas ejecutar el proyecto en un contenedor. Ejecuta el siguiente comando:

bash
bootroom start

4. Verificar el Despliegue
   Para verificar que el proyecto se ha desplegado correctamente, abre tu navegador web y navega a la siguiente URL:

http://localhost:8081
Docker
Si prefieres ejecutar el proyecto en un contenedor Docker, sigue estos pasos adicionales:

1. Crear el Dockerfile
   Crea un archivo llamado Dockerfile en el directorio raíz del proyecto con el siguiente contenido:

dockerfile

# Usa una imagen base de OpenJDK 21

FROM openjdk:21-jdk

# Establece el directorio de trabajo dentro del contenedor

WORKDIR /app

# Copia el archivo JAR de tu aplicación al contenedor

COPY build/libs/tu-aplicacion.jar /app/tu-aplicacion.jar

# Expone el puerto en el que tu aplicación se ejecutará

EXPOSE 8080

# Comando para ejecutar tu aplicación

ENTRYPOINT ["java", "-jar", "/app/tu-aplicacion.jar"]

2. Construir la Imagen Docker
   Construye la imagen Docker utilizando el siguiente comando:

bash
docker build -t mi-aplicacion .

3. Ejecutar el Contenedor Docker
   Ejecuta el contenedor Docker utilizando el siguiente comando:

bash
docker run -p 8080:8080 mi-aplicacion
Contribuciones
Si deseas contribuir a este proyecto, por favor sigue las siguientes pautas:

Haz un fork del repositorio.

Crea una nueva rama (git checkout -b feature/nueva-funcionalidad).

Realiza tus cambios y haz commit (git commit -am 'Añadir nueva funcionalidad').

Sube tus cambios a la rama (git push origin feature/nueva-funcionalidad).

Abre un Pull Request.