# challenge-foro
<h1>API REST para la gestión de un foro de discusión, desarrollada con Spring Boot.</h1>

<h2>Descripcion</h2>
ForoHub es un backend que permite crear, listar, actualizar y eliminar tópicos (temas de discusión), junto con sus autores y cursos asociados. La aplicación implementa autenticación JWT para la seguridad y utiliza PostgreSQL como base de datos.

<h2>Tecnologías</h2>
Java 17+

Spring Boot 3

Spring Data JPA (Hibernate)

Spring Security con JWT

PostgreSQL

Maven

Lombok

<h2>Funcionalidades principales</h2>
Crear un nuevo tópico con título, mensaje, autor y curso.

Listar tópicos con paginación.

Obtener detalles de un tópico específico.

Actualizar datos de un tópico.

Eliminar un tópico (borrado físico).

Autenticación mediante tokens JWT para asegurar endpoints.

<h2>Endpoints principales</h2>
Método	URI	Descripción
POST	/topicos	Crear un nuevo tópico
GET	/topicos	Listar tópicos paginados
GET	/topicos/{id}	Obtener detalle de un tópico
PUT	/topicos/{id}	Actualizar un tópico existente
DELETE	/topicos/{id}	Eliminar un tópico

<h3>Uso</h3>
Clonar el repositorio

bash
Copiar
Editar
git clone https://github.com/tuUsuario/ForoHub.git
cd ForoHub
Configurar base de datos en application.properties o application.yml.

<h3>Ejecutar la aplicación</h3>

bash
Copiar
Editar
./mvnw spring-boot:run
Acceder a los endpoints usando Postman o cualquier cliente HTTP, enviando el token JWT en el header Authorization: Bearer <token> para las rutas protegidas.
