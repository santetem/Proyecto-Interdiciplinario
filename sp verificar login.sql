DELIMITER //
create procedure sp_verificar_login(
    in p_correo varchar(100), 
    in p_contrasena varchar(100)
)
begin
    select * from usuarios 
    where correo = p_correo and upassword = p_contrasena;
end //
DELIMITER ;
