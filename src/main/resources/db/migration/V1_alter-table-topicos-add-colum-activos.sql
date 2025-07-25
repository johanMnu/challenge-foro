alter table topicos add activo tinyint(1);
update topicos set activo = 1;