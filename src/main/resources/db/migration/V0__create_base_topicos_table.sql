-- La migración V1 modifica topicos, por lo que la tabla debe existir previamente.
-- Hibernate completa el resto de columnas con ddl-auto=update en este proyecto.
CREATE TABLE IF NOT EXISTS topicos (
    id BIGINT NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (id)
);
