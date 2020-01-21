use proyectobases;
-- Número de teléfono de los proveedores de partes para la
-- transmisión de vehículos a los que se les compra crucetas 
create view reporte1 as select pro.nombreEmpresa, p.celular 
from proveedor pro 
inner join persona p on pro.cedula = p.cedula;

create view reporte2 as  select c.cedula, p.nombre, p.apellido, month(o.fecha) Mes, sum(o.total) total
from cliente c
inner join persona p on c.cedula= p.cedula
inner join ordencompra as o on p.cedula=o.idcliente
group by Mes, c.cedula
order by Mes asc;

create view reporte3 as select Nombre, per.mes,  max(per.cantidad) total from 
 (select month(o.fecha) mes, concat(p.nombre,' ',p.apellido) as Nombre, c.cedula, sum(o.total) cantidad
from cliente c
inner join persona p on c.cedula= p.cedula
inner join ordencompra as o on p.cedula=o.idcliente
group by mes, c.cedula ) per
group by per.mes
order by per.mes asc;

create view reporte4 as select inv.codigoinventario, inv.ubicacion, count(p.nombrePro) as totalProductos from producto p
inner join inventario inv on p.codinv=inv.codigoinventario
group by inv.codigoinventario;

create view reporte5 as select p.nombre , p.apellido , prod.codigo, prod.nombrePro from persona p 
inner join  proveedor pro on p.cedula=pro.cedula
inner join pedidoRealizado pdr on pro.cedula=pdr.idProveedor
inner join DetallePedido dp on pdr.idPedido=dp.idpedido
inner join producto prod on dp.CodigoProducto=prod.codigo;

create view reporte6 as select p.cedula,p.nombre, p.apellido,p.estado from persona p 
inner join cliente c on p.cedula=c.cedula;


DELIMITER $$
create trigger iva_Compra before insert on ordencompra 
for each row begin
set new.total=new.total+(new.total*0.12);
end
$$ DELIMITER ;


DELIMITER $$
 create procedure calcularDescuento (in porcentaje decimal(5,2), in valor decimal(10,2), out descuento decimal)
 begin 
 select porcentaje*valor into descuento;
 end;
 $$ DELIMITER ;
 
create index service on servicio (tipo,costo);
create index products on producto(nombrePro, precioUnitario);

DELIMITER $$
create procedure personacliente(in cedula varchar(10),in nombre varchar(15),in apellido varchar(20),in fechanacimiento datetime,in direccion varchar(60),in celular varchar(10))
begin
start transaction;
insert into persona values(cedula,nombre,apellido,fechanacimiento,celular,1);
insert into cliente values(cedula,direccion,1);
commit;
end;
$$ DELIMITER ;


DELIMITER $$
create procedure personaproveedor(in cedula varchar(10),in nombre varchar(15),in apellido varchar(20),in fechanacimiento datetime,in direccion varchar(60),in nombreempresa varchar(40),in celular varchar(10))
begin
start transaction;
insert into persona values(cedula,nombre,apellido,fechanacimiento,celular,1);
insert into proveedor values(cedula,nombreempresa,direccion,1);
commit;
end;
$$ DELIMITER ;


-- drop user 'lanchundia'@'localhost';
-- CREATE USER 'lanchundia'@'localhost' IDENTIFIED BY 'Luis12345';
-- GRANT all privileges ON proyectobases.* TO 'lanchundia'@'localhost' ;
-- flush privileges;

DELIMITER $$
create procedure modificarCliente(in cedulan varchar(10),in nombren varchar(15), 
in apellidon varchar(20),in fechanacimienton datetime,in direccionn varchar(60),in celularn varchar(10))
begin
start transaction;
UPDATE persona SET nombre=nombren,apellido=apellidon,fechaNacimiento=fechanacimienton,celular=celularn
                 WHERE cedula=cedulan;

UPDATE cliente set direccion=direccionn where cedula=cedulan;
commit;
end;
$$ DELIMITER ;

DELIMITER $$
create procedure modificarProveedor(in cedulan varchar(10),in nombren varchar(15),
in apellidon varchar(20),in fechanacimienton datetime,in direccionn varchar(60),in nombreempresan varchar(40),in celularn varchar(10))
begin
start transaction;
UPDATE persona SET nombre=nombren,apellido=apellidon,fechaNacimiento=fechanacimienton,celular=celularn
                WHERE cedula=cedulan;
UPDATE proveedor set nombreEmpresa=nombreempresan
                ,direccion=direccionn where cedula=cedulan;
commit;
end;
$$ DELIMITER ;

DELIMITER $$
create procedure eliminarProveedor(in cedulan varchar(10))
begin
start transaction;
update persona set estado=0 WHERE cedula=cedulan;
update proveedor set estado=0 WHERE cedula=cedulan;


commit;
end;
$$ DELIMITER ;



DELIMITER $$
create procedure eliminarCliente(in cedulan varchar(10))
begin
start transaction;
update persona set estado=0 WHERE cedula=cedulan;
update cliente set estado=0 WHERE cedula=cedulan;


commit;
end;
$$ DELIMITER ;



