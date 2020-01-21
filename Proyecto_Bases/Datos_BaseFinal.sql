use ProyectoBases;

insert into persona values("0912546589","Juan", "Castro",'1978/5/12',"0956235487",1),
("1365234879","Ulises", "Coloma",'1966/4/25',"0998745618",1),
("0965975461","Renato", "Valle",'1969/7/1',"0968547554",1),
("1021547698","Pedro", "Solis",'1963/5/8',"0968745686",1),
("0968745678","Jaime", "Spriano",'1958/8/2',"0956235487",1),
("1325478645","Julian", "Perez",'1998/2/11',"0968457124",1),
("1025478695","Alejandro", "Figuroa",'1959/12/7',"0935421268",1),
("1356987845","Nicolas", "Ramirez",'1965/10/9',"0963512486",1),
("1154786954","Luis", "Anchundia",'1996/6/17',"0784567812",1),
("0875489635","Jorge", "Altamirano",'1977/9/2',"0987451457",1);


insert into persona values("0698745687","Felipe", "Ibañez",'1977/12/1',"0987546823",1),
("0254786598","Rodrigo", "Valdez",'1965/1/25',"0987465245",1),
("0365487569","Dario", "Roman",'1984/7/12',"0956235487",1),
("0698745684","Carlos", "Ricaute",'1968/7/16',"0935487459",1),
("0598645781","Ricardo", "Suarez",'1987/3/25',"0968745785",1),
("0254687965","Ramon", "Figueroa",'1962/9/17',"0925478639",1),
("0565563248","Karla", "Sevilla",'1977/8/6',"0956235487",1),
("0968746578","julia", "Jimenez",'1989/2/27',"0956235487",1),
("0875647824","Odalys", "Guaman",'1991/7/30',"0956235487",1),
("0364785468","Gabriela", "Carrion",'1993/4/11',"0956235487",1);


insert into inventario (descripcion,ubicacion,categoria,estado) values("Repuestos", "Bodega principal", "Repuestos varios",1),
("Baterias","Bodega secundaria", "Baterias multimarcas",1);


insert into cliente values ("0912546589","Alborada primera etapa",1),
("1365234879","Miraflores",1),
("0965975461","Guayacanes",1),
("1021547698","Alborada decima etapa",1),
("0968745678","Mapasingue oeste",1),
("0698745687","Mucho lote",1),
("0254786598","Av las aguas",1),
("0365487569","Guayacanes",1),
("0698745684","Alborada quinta etapa",1),
("0598645781","Alborada octava etapa",1);

insert into proveedor values ("1325478645","Distribuidora perez", "Alborada tercera etapa",1),
("1025478695","Distribuidora Remi", "Av las aguas",1),
("1356987845","Distribuidora Serna", "Ceibos norte",1),
("1154786954","Distrimax", "Mapasingue este",1),
("0875489635","Distriproduct", "Mucho lote",1),
("0254687965","Distribuy", "Mucho lote 2",1),
("0565563248","DistriSevilla", "Urdesa central",1),
("0968746578","DistriJalisco", "El paraiso",1),
("0875647824","DistriGuaman", "Miraflores",1),
("0364785468","DistriCarrion", "Centro",1);


insert into producto (nombrepro,preciounitario,codinv,estado) values("GuardaChoque",60,1,1),
("Capo",110,1,1),
("Radiador",90,1,1),
("Faros",30,1,1),
("Bujias",10,1,1),
("Llanta",37,1,1),
("Bateria bosh",60,2,1),
("Bateria Ecuador",68,2,1),
("Bateria Tekno", 65,2,1),
("Tapacubos",10,1,1);


insert into servicio (tipo,costo,estado) values("Diagnostico del motor con escaner",50,1),
("Limpieza de inyectores con ultrasonido",56,1),
("Mantenimiento cuerpo aceleracion",65,1),
("Encendido electronico",25,1),
("Borrado de codigos de falla",35,1),
("Pruebas de sensores", 32,1),
("Prueba de bomba de gasolina",15,1),
("Prueba de gasolina",10,1);







insert into ordencompra (idcliente,fecha,total,estado) values("0912546589",'2018/01/25',200,1),
("1365234879",'2018/02/24',350,1),
("0965975461",'2018/02/18',320,1),
("1021547698",'2018/02/18',220,1),
("0968745678",'2018/02/20',250,1),
("0698745687",'2018/03/12',110,1),
("0254786598",'2018/03/15',150,1),
("0365487569",'2018/05/20',360,1),
("0698745684",'2018/06/25',270,1),
("0598645781",'2018/07/12',180,1);


insert into pedidorealizado (idproveedor,fecha,total,estado) values("1325478645",'2017/02/21',1000,1),
("1025478695",'2017/03/20',2500,1),
("1356987845",'2017/06/25',3000,1),
("1154786954",'2017/07/2',3200,1),
("0875489635",'2017/08/4',2600,1),
("0254687965",'2017/09/6',3800,1),
("0565563248",'2018/01/10',4000,1),
("0968746578",'2018/01/25',1100,1),
("0875647824",'2018/02/14',2400,1),
("0364785468",'2018/03/18',3580,1);


insert into detallecompra values(1,null,1,5,100),
(1,null,2,2,30),
(1,null,3,3,60),
(1,1,null,1,50),
(2,2,null,1,40),
(2,null,1,3,100),
(2,null,5,2,70),
(3,1,null,1,60),
(3,null,6,3,80),
(3,null,8,1,25);


insert into detallepedido values(1,2,3,200),
(1,1,2,150),
(1,3,1,70),
(2,1,3,220),
(2,2,5,390),
(2,4,2,210),
(3,9,1,80),
(4,1,6,650),
(4,3,3,360),
(4,2,2,180);



