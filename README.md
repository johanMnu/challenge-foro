# ForoHub — API REST para un foro

ForoHub es uno de los proyectos que desarrollé durante mi formación en Java Backend con Oracle Next Education / Alura. La idea fue construir la parte del servidor de un foro: registrar tópicos, consultarlos, actualizarlos y eliminarlos, incorporando autenticación con JWT.

Me sirvió especialmente para empezar a trabajar con Spring Security, separar los datos de entrada y salida usando DTOs y conectar una API REST con una base de datos relacional.

## ¿Qué permite hacer?

- Crear, listar, consultar, modificar y eliminar tópicos.
- Relacionar cada tópico con un usuario (autor) y un curso.
- Listar tópicos con paginación.
- Iniciar sesión y obtener un token JWT para acceder a rutas protegidas.
- Registrar usuarios sin token para poder crear la primera cuenta, guardando sus contraseñas con BCrypt.

## Tecnologías utilizadas

- **Java 17**
- **Spring Boot 3.5.3**
- **Spring Web**, **Spring Data JPA / Hibernate** y **Spring Security**
- **JWT** (biblioteca `java-jwt`)
- **MySQL**
- **Maven**, **Lombok** y **Flyway**

> Nota: el proyecto usa **MySQL**, como se ve en las dependencias de `pom.xml`. Versiones anteriores de este README mencionaban PostgreSQL.

## Endpoints principales

| Método | Ruta | Acción |
| --- | --- | --- |
| `POST` | `/login` | Iniciar sesión y recibir un JWT |
| `POST` | `/topicos` | Crear un tópico |
| `GET` | `/topicos` | Listar tópicos con paginación |
| `GET` | `/topicos/{id}` | Consultar un tópico |
| `PUT` | `/topicos/{id}` | Actualizar un tópico |
| `DELETE` | `/topicos/{id}` | Eliminar un tópico |
| `POST` | `/usuarios` | Registrar usuario (ruta pública) |
| `POST` | `/cursos` | Registrar curso (ruta protegida) |

### Ejemplo de solicitud de tópico

```json
{
  "titulo": "Duda sobre Spring Boot",
  "mensaje": "¿Cómo organizar la capa de servicios?",
  "idAutor": 1,
  "idCurso": 1
}
```

Para crear ese tópico deben existir previamente el usuario y el curso con esos identificadores.

### Autenticación

El inicio de sesión se realiza enviando una solicitud `POST /login`:

```json
{
  "login": "usuario@ejemplo.com",
  "contrasena": "tu_contrasena"
}
```

Antes del login, registrá un usuario mediante `POST /usuarios` con un cuerpo como este:

```json
{
  "nombre": "Ana",
  "correoElectronico": "ana@ejemplo.com",
  "contrasena": "una_contrasena_de_prueba"
}
```

Después podés iniciar sesión con ese correo electrónico como `login` y la contraseña usada en el registro. Con un usuario válido, la API devuelve un JWT. Para las rutas protegidas se utiliza la cabecera:

```http
Authorization: Bearer TU_TOKEN
```

Ahora `POST /usuarios` y `POST /login` son públicos para permitir el primer registro. El resto de las rutas requiere JWT. En una aplicación pública habría que sumar controles de abuso y políticas de registro.

## Estructura del proyecto

- `controller/`: endpoints HTTP.
- `DTO/`: datos que recibe y devuelve la API.
- `service/`: servicios del proyecto.
- `Repositorio/`: acceso a datos mediante Spring Data JPA.
- `model/`: entidades del dominio.
- `infra/security/`: autenticación, configuración y validación de JWT.
- `infra/exception/`: excepciones y respuestas de error.
- `src/main/resources/db/migration/`: archivos SQL de migración incluidos en el repositorio.

## Cómo ejecutarlo localmente

### Requisitos

- JDK 17
- MySQL
- Git
- Una terminal. El repositorio incluye Maven Wrapper.

1. Clonar el repositorio:

```bash
git clone https://github.com/johanMnu/challenge-foro.git
cd challenge-foro
```

2. Crear una base de datos MySQL (por defecto se usa `forohub`):

```sql
CREATE DATABASE forohub;
```

3. Definir la contraseña de tu propia base de datos con `DB_PASSWORD`. También podés ajustar `DB_USERNAME` y `DB_URL` si tu configuración es diferente.

**PowerShell (Windows):**

```powershell
$env:DB_PASSWORD = "tu_contrasena_local"
./mvnw.cmd spring-boot:run
```

**Bash (Linux/macOS):**

```bash
export DB_PASSWORD="tu_contrasena_local"
./mvnw spring-boot:run
```

No compartas tu contraseña real en archivos versionados. La clave del token JWT también debe configurarse con un valor privado según `application.properties`.

> Las migraciones Flyway ahora tienen nombres válidos y el proyecto cuenta con una prueba de arranque mediante GitHub Actions y MySQL. Si ya tenías una base de datos creada previamente, revisá el historial de migraciones antes de aplicar los cambios: no se debe modificar una base con datos sin respaldo.

## Qué me aportó este proyecto

Pude practicar operaciones CRUD, paginación, relaciones entre entidades y protección de rutas. La parte de autenticación fue especialmente útil para entender cómo intervienen Spring Security, el filtro JWT y el servicio que genera y verifica tokens.

El proyecto todavía tiene puntos para mejorar, como validar mejor los datos de registro, agregar pruebas de los endpoints y reforzar el tratamiento de errores. Prefiero dejar esas mejoras identificadas antes que presentar el sistema como terminado.
