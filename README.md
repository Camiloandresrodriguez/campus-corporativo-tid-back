# Campus TID API (Backend)

API REST en Java (Spring Boot) para soportar el frontend del proyecto Campus TID. Expone endpoints de autenticación simulada, catálogo de cursos, inscripciones, asistencia, calificaciones, anuncios y métricas de dashboard.

## Qué resuelve

- Autenticación sin Spring Security (login por petición GET).
- Persistencia en MySQL (phpMyAdmin) con generación automática de tablas vía JPA/Hibernate.
- CRUD completo para los módulos requeridos por el frontend.
- Validaciones de entrada y manejo consistente de errores.
- Documentación automática con Swagger UI.

## Tecnologías

- Java 21
- Spring Boot (WebMVC, Data JPA, Validation)
- MySQL (runtime)
- H2 (solo tests)
- Swagger/OpenAPI (springdoc)

## Encarpetado (paquetes)

Ruta base del código: `src/main/java/com/campustid/campus_tid`

- `controllers/`: define los endpoints HTTP (rutas, query params, body). Delegan la lógica a `services`.
- `services/`: contiene la lógica de negocio del módulo (validaciones de negocio, reglas, conversiones a DTO, uso de repositorios).
- `repositories/`: acceso a datos con Spring Data JPA (consultas y persistencia).
- `models/`: entidades JPA que representan tablas (y enums de dominio).
  - `models/dto/`: contratos de entrada/salida de la API (requests/responses). Se usan para validar, controlar lo que se expone y evitar devolver campos sensibles.
- `exception/`: excepciones de negocio y un manejador global para serializar errores.
- `config/`: configuración de CORS y OpenAPI/Swagger.
- `util/`: utilidades (hash de contraseñas).

## Modelos (models)

Entidades (tablas) principales:

- `UserEntity`: usuarios del sistema. Campos: nombres, email (único), `passwordHash`, datos opcionales (teléfono, documento, dirección), rol, timestamps.
- `CategoryEntity`: categoría de cursos (nombre único).
- `CourseEntity`: curso (título, descripción, categoría, fechas, capacidad, activo).
- `EnrollmentEntity`: inscripción (usuario + curso, estado, fecha de inscripción). Evita duplicados por usuario+curso.
- `GradeEntity`: calificación (usuario + curso, nota numérica, observación, fecha).
- `AttendanceEntity`: asistencia (usuario + curso + fecha, estado). Evita duplicados por usuario+curso+fecha.
- `AnnouncementEntity`: anuncio (título, contenido, fecha, createdAt).

Enums:

- `EnrollmentStatus`: `INSCRITO | CANCELADO | COMPLETADO`
- `AttendanceStatus`: `PRESENTE | AUSENTE | TARDANZA`

## Repositorios (repositories)

Todos extienden `JpaRepository<Entidad, Long>` y se usan desde los servicios.

- `UserRepository`
  - `findByEmailIgnoreCase(email)`: búsqueda para login/validaciones.
  - `existsByEmailIgnoreCase(email)`: evitar duplicados en registro.
- `CategoryRepository`: CRUD de categorías.
- `CourseRepository`
  - `findByCategoryId(categoryId)`: filtro de cursos por categoría.
  - `countByCategoryId(categoryId)`: contador por categoría (opcional avanzado del frontend).
- `EnrollmentRepository`
  - `findByUserId(userId)`: “Mis Cursos”.
  - `findByUserIdAndCourseId(userId, courseId)`: evitar doble inscripción.
- `GradeRepository`
  - `findByUserId(userId)`: “Mis Calificaciones”.
- `AttendanceRepository`
  - `findByUserId(userId)`: “Historial Asistencia”.
- `AnnouncementRepository`: CRUD de anuncios.

## Servicios (services)

- `AuthService`: valida credenciales contra `UserRepository` usando `PasswordHasher` y retorna el usuario (sin exponer el hash).
- `UserService`: CRUD de usuarios, cambio de contraseña y mapeo `UserEntity -> UserResponse`.
- `CategoryService`: CRUD de categorías y (opcional) retorno con contador de cursos.
- `CourseService`: CRUD de cursos y listado filtrable por categoría.
- `EnrollmentService`: CRUD de inscripciones, listado por usuario, validación de duplicados y manejo de estado.
- `GradeService`: CRUD de calificaciones y listado por usuario.
- `AttendanceService`: CRUD de asistencias y listado por usuario; valida `AttendanceStatus`.
- `AnnouncementService`: CRUD de anuncios.
- `DashboardService`: calcula métricas simples: total usuarios, total cursos, total inscripciones.

## Autenticación (sin Spring Security)

- No hay rutas protegidas en el backend.
- El login se hace con `GET /api/auth/login` y, si es correcto, devuelve el objeto `user`.
- La “sesión” se maneja en el frontend (por ejemplo, guardando `user` en localStorage) y enviando `userId` en consultas como query param.

Hash de contraseñas:

- Se almacena `passwordHash` con sal + SHA-256 (formato `v1$<salt>$<hash>`).
- Implementación: `util/PasswordHasher`.

## Manejo de errores

El backend responde errores con un JSON consistente:

```json
{
  "message": "Texto del error",
  "fieldErrors": {
    "campo": "mensaje"
  }
}
```

- Validaciones de DTO: HTTP 400 con `fieldErrors`.
- No encontrado: HTTP 404.
- Conflicto (por ejemplo, email duplicado o inscripción duplicada): HTTP 409.
- Login/contraseña inválida: HTTP 401.

## Documentación Swagger

- Swagger UI: `http://localhost:8080/swagger-ui.html`
- OpenAPI JSON: `http://localhost:8080/api-docs`

## Base URL

Por defecto, el backend corre en:

- `http://localhost:8080`

## Endpoints (completos)

### Módulo 1: Autenticación y Registro

- `GET  http://localhost:8080/api/auth/login?email={email}&password={password}`
- `POST http://localhost:8080/api/auth/recover`

### Usuarios (incluye registro/perfil/seguridad)

- `GET    http://localhost:8080/api/users`
- `GET    http://localhost:8080/api/users/{id}`
- `POST   http://localhost:8080/api/users`
- `PATCH  http://localhost:8080/api/users/{id}`
- `POST   http://localhost:8080/api/users/{id}/change-password`
- `DELETE http://localhost:8080/api/users/{id}`

### Módulo 2: Categorías y Cursos

Categorías:

- `GET    http://localhost:8080/api/categories?withCounts={true|false}`
- `GET    http://localhost:8080/api/categories/{id}`
- `POST   http://localhost:8080/api/categories`
- `PUT    http://localhost:8080/api/categories/{id}`
- `DELETE http://localhost:8080/api/categories/{id}`

Cursos:

- `GET    http://localhost:8080/api/courses?categoryId={categoryId}`
- `GET    http://localhost:8080/api/courses/{id}`
- `POST   http://localhost:8080/api/courses`
- `PUT    http://localhost:8080/api/courses/{id}`
- `DELETE http://localhost:8080/api/courses/{id}`

### Módulo 3: Inscripciones

- `GET    http://localhost:8080/api/enrollments?userId={userId}`
- `GET    http://localhost:8080/api/enrollments/{id}`
- `POST   http://localhost:8080/api/enrollments`
- `PATCH  http://localhost:8080/api/enrollments/{id}`
- `DELETE http://localhost:8080/api/enrollments/{id}`

### Módulo 4: Calificaciones y Asistencias

Calificaciones:

- `GET    http://localhost:8080/api/grades?userId={userId}`
- `GET    http://localhost:8080/api/grades/{id}`
- `POST   http://localhost:8080/api/grades`
- `PUT    http://localhost:8080/api/grades/{id}`
- `DELETE http://localhost:8080/api/grades/{id}`

Asistencias:

- `GET    http://localhost:8080/api/attendance?userId={userId}`
- `GET    http://localhost:8080/api/attendance/{id}`
- `POST   http://localhost:8080/api/attendance`
- `PUT    http://localhost:8080/api/attendance/{id}`
- `DELETE http://localhost:8080/api/attendance/{id}`

### Módulo 6: Anuncios

- `GET    http://localhost:8080/api/announcements`
- `GET    http://localhost:8080/api/announcements/{id}`
- `POST   http://localhost:8080/api/announcements`
- `PUT    http://localhost:8080/api/announcements/{id}`
- `DELETE http://localhost:8080/api/announcements/{id}`

### Módulo 7: Dashboard

- `GET http://localhost:8080/api/dashboard/metrics`

## Cómo ejecutar

### Requisitos

- Java 21
- MySQL (XAMPP/WAMP) + phpMyAdmin

### 1) Configurar MySQL

En MySQL crea una base de datos, por ejemplo: `campus_tid`.

La conexión se configura en `src/main/resources/application.properties`. Puedes editar el archivo o usar variables de entorno:

- `DB_URL` (ejemplo: `jdbc:mysql://localhost:3306/campus_tid?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC`)
- `DB_USERNAME`
- `DB_PASSWORD`

La generación de tablas está activa con:

- `spring.jpa.hibernate.ddl-auto=update`

### 2) Levantar el backend

```bash
.\mvnw spring-boot:run
```

## Pruebas

Los tests usan H2 en memoria (no requieren MySQL):

```bash
.\mvnw test
```

