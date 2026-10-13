# API REST - LIBRERIA

## Descripción de la API
API REST desarrollada en Java con Spring Boot para administrar las operaciones de una biblioteca o librería. Permite gestionar el catálogo de libros, registrar usuarios y administrar préstamos y devoluciones mediante peticiones HTTP con respuestas en formato JSON.

---

## Tecnologías Utilizadas
* **Lenguaje:** Java 21
* **Framework:** Spring Boot
  * Spring Web MVC (Construcción de API REST)
  * Spring Data JPA / Hibernate (Persistencia y ORM)
  * Thymeleaf (Renderizado de plantillas del servidor)
* **Base de Datos:** MySQL Server (Driver mysql-connector-j)
* **Gestor de Dependencias y Compilación:** Apache Maven
* **Cliente de Pruebas:** Postman

---

## Variables de Entorno Necesarias
Para configurar la aplicación en diferentes entornos sin exponer credenciales directamente en el código fuente:

* **PORT:** Puerto de escucha de la aplicación (Por defecto: 3000)
* **DB_URL:** Cadena de conexión JDBC a MySQL (Por defecto: jdbc:mysql://localhost:3306/libreria?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC)
* **DB_USERNAME:** Usuario de la base de datos (Por defecto: root)
* **DB_PASSWORD:** Contraseña de la base de datos (Por defecto: tu_contraseña_local)

---

## Pasos de Instalación y Ejecución Local

### Prerrequisitos
* Java JDK 21 instalado en el sistema
* MySQL Server activo en el puerto 3306
* Base de datos llamada libreria creada previamente:
  CREATE DATABASE libreria;

### Pasos de Ejecución
* 1. Clonar el repositorio:
  git clone https://github.com/ferch2709/libreria-API.git
  cd libreria-API

* 2. Configurar la base de datos:
  Verificar en src/main/resources/application.properties que el usuario y la contraseña coincidan con los de tu MySQL local.

* 3. Ejecutar la aplicación:
  En la terminal ejecutar:
  ./mvnw spring-boot:run
  O ejecutar la clase LibreriaApplication.java desde el IDE.

* 4. Acceso:
  El servidor estará disponible en http://localhost:3000

---

## Listado de Endpoints

### 1. Módulo Libros (/libros)
* **GET /libros:** Retorna todos los libros registrados
* **POST /libros:** Registra un nuevo libro en el catálogo
* **GET /libros/{isbn}:** Retorna un libro específico según su ISBN
* **GET /libros/autor/{autor}:** Filtra libros por nombre de autor
* **PUT /libros/{isbn}:** Actualiza los datos de un libro existente
* **DELETE /libros/{isbn}:** Elimina un libro del catálogo

### 2. Módulo Usuarios (/usuarios)
* **GET /usuarios:** Retorna todos los usuarios registrados
* **POST /usuarios:** Registra un nuevo usuario en el sistema
* **GET /usuarios/{id}:** Retorna un usuario según su ID
* **PUT /usuarios/{id}:** Actualiza los datos de un usuario
* **DELETE /usuarios/{id}:** Elimina un usuario por su ID

### 3. Módulo Préstamos (/prestamos)
* **GET /prestamos:** Retorna todos los préstamos registrados
* **POST /prestamos:** Registra un nuevo préstamo vinculando libro y usuario
* **GET /prestamos/{id}:** Retorna un préstamo por su ID
* **GET /prestamos/usuario/{usuarioId}:** Retorna los préstamos de un usuario específico
* **PUT /prestamos/{id}:** Actualiza los datos de un préstamo (ej. fecha de devolución)
* **DELETE /prestamos/{id}:** Elimina un registro de préstamo

---

## Ejemplos de Peticiones y Respuestas

### 1. Registrar un Libro
* **Método:** POST
* **URL:** http://localhost:3000/libros
* **Headers:** Content-Type: application/json
* **Cuerpo de la Petición:**
```json
{
  "titulo": "Cien Años de Soledad",
  "autor": "Gabriel García Márquez",
  "editorial": "Editorial Sudamericana",
  "anioPublicacion": "1967",
  "numEjemplares": 5
}
```
* **Estado HTTP:** 200 OK
* **Cuerpo de la Respuesta:**
```json
{
  "isbn": 1,
  "titulo": "Cien Años de Soledad",
  "autor": "Gabriel García Márquez",
  "editorial": "Editorial Sudamericana",
  "anioPublicacion": "1967",
  "numEjemplares": 5
}
```

---

### 2. Listar Todos los Libros
* **Método:** GET
* **URL:** http://localhost:3000/libros
* **Estado HTTP:** 200 OK
* **Cuerpo de la Respuesta:**
```json
[
  {
    "isbn": 1,
    "titulo": "Cien Años de Soledad",
    "autor": "Gabriel García Márquez",
    "editorial": "Editorial Sudamericana",
    "anioPublicacion": "1967",
    "numEjemplares": 5
  },
  {
    "isbn": 2,
    "titulo": "Don Quijote de la Mancha",
    "autor": "Miguel de Cervantes",
    "editorial": "Francisco de Robles",
    "anioPublicacion": "1605",
    "numEjemplares": 3
  }
]
```

---

### 3. Registrar un Usuario
* **Método:** POST
* **URL:** http://localhost:3000/usuarios
* **Headers:** Content-Type: application/json
* **Cuerpo de la Petición:**
```json
{
  "nombre": "Carlos",
  "apellidos": "Pérez Gómez",
  "correoElectronico": "carlos.perez@example.com",
  "contrasena": "carlos123",
  "rol": "LECTOR",
  "estado": "ACTIVO"
}
```
* **Estado HTTP:** 200 OK
* **Cuerpo de la Respuesta:**
```json
{
  "id": 1,
  "nombre": "Carlos",
  "apellidos": "Pérez Gómez",
  "correoElectronico": "carlos.perez@example.com",
  "contrasena": "carlos123",
  "rol": "LECTOR",
  "estado": "ACTIVO"
}
```

---

### 4. Registrar un Préstamo
* **Método:** POST
* **URL:** http://localhost:3000/prestamos
* **Headers:** Content-Type: application/json
* **Cuerpo de la Petición:**
```json
{
  "libro": {
    "isbn": 1
  },
  "usuario": {
    "id": 1
  },
  "fechaPrestamo": "2026-10-01",
  "fechaDevolucion": null
}
```
* **Estado HTTP:** 200 OK
* **Cuerpo de la Respuesta:**
```json
{
  "id": 1,
  "libro": {
    "isbn": 1,
    "titulo": "Cien Años de Soledad",
    "autor": "Gabriel García Márquez",
    "editorial": "Editorial Sudamericana",
    "anioPublicacion": "1967",
    "numEjemplares": 5
  },
  "usuario": {
    "id": 1,
    "nombre": "Carlos",
    "apellidos": "Pérez Gómez",
    "correoElectronico": "carlos.perez@example.com",
    "contrasena": "carlos123",
    "rol": "LECTOR",
    "estado": "ACTIVO"
  },
  "fechaPrestamo": "2026-10-01",
  "fechaDevolucion": null
}
```

---

### 5. Consultar Préstamos por Usuario
* **Método:** GET
* **URL:** http://localhost:3000/prestamos/usuario/1
* **Estado HTTP:** 200 OK
* **Cuerpo de la Respuesta:**
```json
[
  {
    "id": 1,
    "libro": {
      "isbn": 1,
      "titulo": "Cien Años de Soledad",
      "autor": "Gabriel García Márquez",
      "editorial": "Editorial Sudamericana",
      "anioPublicacion": "1967",
      "numEjemplares": 5
    },
    "usuario": {
      "id": 1,
      "nombre": "Carlos",
      "apellidos": "Pérez Gómez",
      "correoElectronico": "carlos.perez@example.com",
      "contrasena": "carlos123",
      "rol": "LECTOR",
      "estado": "ACTIVO"
    },
    "fechaPrestamo": "2026-10-01",
    "fechaDevolucion": null
  }
]
```

---

### 6. Actualizar un Libro
* **Método:** PUT
* **URL:** http://localhost:3000/libros/1
* **Headers:** Content-Type: application/json
* **Cuerpo de la Petición:**
```json
{
  "titulo": "Cien Años de Soledad (Edición Ilustrada)",
  "autor": "Gabriel García Márquez",
  "editorial": "Editorial Sudamericana",
  "anioPublicacion": "2007",
  "numEjemplares": 10
}
```
* **Estado HTTP:** 200 OK
* **Cuerpo de la Respuesta:**
```json
{
  "isbn": 1,
  "titulo": "Cien Años de Soledad (Edición Ilustrada)",
  "autor": "Gabriel García Márquez",
  "editorial": "Editorial Sudamericana",
  "anioPublicacion": "2007",
  "numEjemplares": 10
}
```

---

### 7. Eliminar un Libro
* **Método:** DELETE
* **URL:** http://localhost:3000/libros/3
* **Estado HTTP:** 200 OK
* **Cuerpo de la Respuesta:**
```text
Se eliminó el libro con ISBN: 3
```

