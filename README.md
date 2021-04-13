# UDCIne
Base de datos

Crear una base de datos llamada jersey y dar permisos al usuario por defecto

    CREATE DATABASE cine;
    CREATE USER IF NOT EXISTS 'spq'@'localhost' IDENTIFIED BY 'spq';
    GRANT ALL ON cine.* TO 'spq'@'localhost';

La configuración por defecto para la base de datos y los usuarios puede ser actualizada en el fichero resources/datanucleus.properties.

Creación de las tablas

Para la creación de las tablas se debe ejecutar el comando de maven

    mvn compile datanucleus:schema-create


Datos de prueba

Se pueden introducir datos de prueba en la aplicación utilizando el comando de maven

    mvn exec::java -Pdatos

Inicio del servidor

El servidor REST de la aplicación se lanza utilizando el comando

    mvn exec::java

Si el servidor ha sido iniciado correctamente se pueden obtener los datos de prueba accediendo con el navegador a la URL http://localhost:8080/myapp/users.


Inicio de la aplicación cliente

La aplicación cliente puede iniciarse usando el comando

    mvn exec::java -Pcliente
