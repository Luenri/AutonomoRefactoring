drop database proyectobases;
create database ProyectoBases;
 use ProyectoBases;
 
 create  table  persona(
cedula varchar(10) primary key,
nombre varchar(25),
apellido varchar(20),
fechaNacimiento date,
celular varchar(10),
estado int
);

create table  cliente(
cedula varchar (10),
direccion varchar(60),
estado int,
foreign key (cedula) references persona(cedula)
);

create table  proveedor(
cedula varchar (10) ,
nombreEmpresa varchar(40),
direccion varchar(60),
estado int,
foreign key (cedula) references persona(cedula)
);

create table  inventario(
codigoInventario int primary key auto_increment,
descripcion varchar(50),
ubicacion varchar(50),
categoria varchar(50),
estado int
);

create table producto(
codigo int primary key auto_increment,
nombrePro varchar(30),
precioUnitario decimal(10,2),
codInv int,
estado int,
foreign key (codInv) references inventario(codigoInventario)
);

create table servicio(
codigoServicio int primary key auto_increment,
tipo varchar(40),
costo decimal(10,2),
estado int
);



create table ordenCompra(
idCompra int primary key auto_increment,
idCliente varchar(10),
fecha date,
total decimal(10,2),
estado int,
foreign key (idCliente) references persona (cedula)
);

create table detalleCompra(
idCompra int,
codigoServicio int,
codigoProducto int,
cantidad int,
subtotal decimal(10,2),
foreign key (idCompra) references ordenCompra(idCompra),
foreign key (codigoServicio) references servicio(codigoServicio),
foreign key (codigoProducto) references producto (codigo)

);

create table pedidoRealizado(
idPedido int primary key auto_increment,
idProveedor varchar(10),
fecha date,
total decimal(10,2),
estado int,
foreign key (idProveedor) references persona(cedula)
);

create table detallePedido(
idPedido int,
codigoProducto int,
cantidad int,
subtotal decimal(10,2),
foreign key (idPedido) references pedidoRealizado(idPedido)
);

