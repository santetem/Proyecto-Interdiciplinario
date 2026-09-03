-- Procedure Registrar Usuario
delimiter //
create procedure registrar_usuario(
    in p_nombre varchar(100),
    in p_apellido varchar(100),
    in p_tag varchar(50),
    in p_correo varchar(100),
    in p_password varchar(255),
    in p_dni varchar(20)
)
begin

    insert into Usuarios (
        nombre,
        apellido,
        tag,
        correo,
        upassword,
        dni
    )
    values (
        p_nombre,
        p_apellido,
        p_tag,
        p_correo,
        p_password,
        p_dni
    );
end //
delimiter ;