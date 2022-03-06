
create database if exists Lab;

use Lab;

Create Table Contactos(
	Id int not null auto_increment,
    Nombre varchar(50),
    Edad int,
    Email varchar(30),
    NumeroDeTelefono varchar(10),
    primary key(Id)
);

--proc para mostrar contactos
delimiter $$
Create procedure sp_MostrarContactos()
	
begin
	select * from Contactos;
end$$
delimiter ;
call sp_MostrarContactos();

--proc para insertar contactos
delimiter $$
create procedure sp_GuardarContacto(
	Nombre varchar(50),
    Edad int,
    Email varchar(50),
    NumeroDeTelefono varchar(50)
)
begin
	insert into Contactos(Nombre,Edad,Email,NumeroDeTelefono) 
				values(Nombre,Edad,Email,NumeroDeTelefono);
end
$$
delimiter ;

call sp_GuardarContacto('Manuel Perlera', 23, 'manuenitoo@gmail.com', '7589-3215');

--proc para actualizar contacto
delimiter $$
create procedure sp_ActualizarContacto(
	id int,
	nombre varchar(50),
    edad int,
    email varchar(50),
    numeroDeTelefono varchar(50)
)
begin
	update Contactos set 
    Nombre = nombre,
    Edad= edad,
    Email = email, 
    NumeroDeTelefono = numeroDeTelefono
    where Contactos.Id = id;
end
$$
delimiter ;

call sp_ActualizarContacto(2,'Ruben Hernández', 16, 'rb@gmail.com', '7777-3215');

--proc eliminar contacto
delimiter $$
Create procedure sp_EliminarContacto(
	id int
)
begin
	delete from Contactos where Contactos.Id = id;
end
$$
delimiter ;

call sp_MostrarContactos();
call sp_EliminarContacto(9);

