INSERT INTO tipo_dispositivo_general(tpdg_nombre, tpdg_control_tiempo, tpdg_activo) VALUES ('Luz', true, true)
INSERT INTO tipo_dispositivo_general(tpdg_nombre, tpdg_control_tiempo, tpdg_activo) VALUES ('Enchufe', false, true)
INSERT INTO tipo_dispositivo_general(tpdg_nombre, tpdg_control_tiempo, tpdg_activo) VALUES ('Regadio', false, true)
INSERT INTO tipo_dispositivo_general(tpdg_nombre, tpdg_control_tiempo, tpdg_activo) VALUES ('Cortina', false, true)
INSERT INTO tipo_dispositivo_general(tpdg_nombre, tpdg_control_tiempo, tpdg_activo) VALUES ('Sensor', false, true)
INSERT INTO tipo_dispositivo_general(tpdg_nombre, tpdg_control_tiempo, tpdg_activo) VALUES ('Camara', false, false)
INSERT INTO tipo_dispositivo_general(tpdg_nombre, tpdg_control_tiempo, tpdg_activo) VALUES ('IR', false, false)

INSERT INTO tipo_dispositivo(tpdg_id, tpdp_nombre, tpdp_activo) VALUES (1, 'Filamento', true)
INSERT INTO tipo_dispositivo(tpdg_id, tpdp_nombre, tpdp_activo) VALUES (1, 'Fluorescente', true)
INSERT INTO tipo_dispositivo(tpdg_id, tpdp_nombre, tpdp_activo) VALUES (1, 'LED', true)
INSERT INTO tipo_dispositivo(tpdg_id, tpdp_nombre, tpdp_activo) VALUES (1, 'Dimmer', true)
INSERT INTO tipo_dispositivo(tpdg_id, tpdp_nombre, tpdp_activo) VALUES (1, 'Halogeno', true)
INSERT INTO tipo_dispositivo(tpdg_id, tpdp_nombre, tpdp_activo) VALUES (2, 'Enchufe', true)
INSERT INTO tipo_dispositivo(tpdg_id, tpdp_nombre, tpdp_activo) VALUES (2, 'Alta Carga', true)
INSERT INTO tipo_dispositivo(tpdg_id, tpdp_nombre, tpdp_activo) VALUES (2, 'Router', true)
INSERT INTO tipo_dispositivo(tpdg_id, tpdp_nombre, tpdp_activo) VALUES (2, 'Ventilador', true)
INSERT INTO tipo_dispositivo(tpdg_id, tpdp_nombre, tpdp_activo) VALUES (2, 'Calienta Cama', true)
INSERT INTO tipo_dispositivo(tpdg_id, tpdp_nombre, tpdp_activo) VALUES (2, 'Radio', true)
INSERT INTO tipo_dispositivo(tpdg_id, tpdp_nombre, tpdp_activo) VALUES (2, 'TV', true)
INSERT INTO tipo_dispositivo(tpdg_id, tpdp_nombre, tpdp_activo) VALUES (2, 'DVD', true)
INSERT INTO tipo_dispositivo(tpdg_id, tpdp_nombre, tpdp_activo) VALUES (2, 'Audio', true)
INSERT INTO tipo_dispositivo(tpdg_id, tpdp_nombre, tpdp_activo) VALUES (2, 'Lampara', true)
INSERT INTO tipo_dispositivo(tpdg_id, tpdp_nombre, tpdp_activo) VALUES (2, 'Cargador', true)
INSERT INTO tipo_dispositivo(tpdg_id, tpdp_nombre, tpdp_activo) VALUES (3, 'Aspersores', true)
INSERT INTO tipo_dispositivo(tpdg_id, tpdp_nombre, tpdp_activo) VALUES (3, 'Difusores', true)
INSERT INTO tipo_dispositivo(tpdg_id, tpdp_nombre, tpdp_activo) VALUES (3, 'Goteo', true)
INSERT INTO tipo_dispositivo(tpdg_id, tpdp_nombre, tpdp_activo) VALUES (3, 'Exudacion', true)
INSERT INTO tipo_dispositivo(tpdg_id, tpdp_nombre, tpdp_activo) VALUES (3, 'Subterraneo', true)
INSERT INTO tipo_dispositivo(tpdg_id, tpdp_nombre, tpdp_activo) VALUES (3, 'Microaspersores', true)
INSERT INTO tipo_dispositivo(tpdg_id, tpdp_nombre, tpdp_activo) VALUES (4, 'Roller', true)
INSERT INTO tipo_dispositivo(tpdg_id, tpdp_nombre, tpdp_activo) VALUES (4, 'Metalica', true)
INSERT INTO tipo_dispositivo(tpdg_id, tpdp_nombre, tpdp_activo) VALUES (5, 'Humo', true)
INSERT INTO tipo_dispositivo(tpdg_id, tpdp_nombre, tpdp_activo) VALUES (5, 'Proximidad', true)
INSERT INTO tipo_dispositivo(tpdg_id, tpdp_nombre, tpdp_activo) VALUES (5, 'Movimiento', true)
INSERT INTO tipo_dispositivo(tpdg_id, tpdp_nombre, tpdp_activo) VALUES (5, 'Temperatura', true)
INSERT INTO tipo_dispositivo(tpdg_id, tpdp_nombre, tpdp_activo) VALUES (6, 'IP', true)
INSERT INTO tipo_dispositivo(tpdg_id, tpdp_nombre, tpdp_activo) VALUES (7, 'IR', true)
INSERT INTO tipo_dispositivo(tpdg_id, tpdp_nombre, tpdp_activo) VALUES (1, 'Bajo Consumo', true)

INSERT INTO tipo_movimiento(tpmv_nombre) VALUES('Encendido');
INSERT INTO tipo_movimiento(tpmv_nombre) VALUES('Apagado');

INSERT INTO tipo_accion_movimiento(tpam_nombre) VALUES('Encender dispositivo');
INSERT INTO tipo_accion_movimiento(tpam_nombre) VALUES('Apagar dispositivo');
INSERT INTO tipo_accion_movimiento(tpam_nombre) VALUES('Envio de Mail');
INSERT INTO tipo_accion_movimiento(tpam_nombre) VALUES('Envio de SMS');


INSERT INTO genero(gnro_nombre) VALUES ('Masculino')
INSERT INTO genero(gnro_nombre) VALUES ('Femenino')

INSERT INTO tipo_usuario(tpus_nombre) VALUES ('Administrador')
INSERT INTO tipo_usuario(tpus_nombre) VALUES ('Normal')
INSERT INTO tipo_usuario(tpus_nombre) VALUES ('Limitado')

INSERT INTO tipo_evento(tpev_nombre) VALUES ('Encendido Manual')
INSERT INTO tipo_evento(tpev_nombre) VALUES ('Apagado Manual')
INSERT INTO tipo_evento(tpev_nombre) VALUES ('Encendido Programado')
INSERT INTO tipo_evento(tpev_nombre) VALUES ('Apagado Programado')
INSERT INTO tipo_evento(tpev_nombre) VALUES ('Apagado Automatico')
INSERT INTO tipo_evento(tpev_nombre) VALUES ('Riego Cancelado')
INSERT INTO tipo_evento(tpev_nombre) VALUES ('Dispositivo Alertado')
INSERT INTO tipo_evento(tpev_nombre) VALUES ('Error en envio de Mail de alerta')
INSERT INTO tipo_evento(tpev_nombre) VALUES ('Error en envio de SMS de alerta')
INSERT INTO tipo_evento(tpev_nombre) VALUES ('Envio de mail')
INSERT INTO tipo_evento(tpev_nombre) VALUES ('Envio de SMS')
INSERT INTO tipo_evento(tpev_nombre) VALUES ('Consulta Global de dispositivos Manual')
INSERT INTO tipo_evento(tpev_nombre) VALUES ('Consulta Global de dispositivos Automatico')
INSERT INTO tipo_evento(tpev_nombre) VALUES ('Consulta de dispositivo')
INSERT INTO tipo_evento(tpev_nombre) VALUES ('Sensor de movimiento detectando presencia')
INSERT INTO tipo_evento(tpev_nombre) VALUES ('Sensor de movimiento sin actividad')
INSERT INTO tipo_evento(tpev_nombre) VALUES ('Encendido por Sensor')
INSERT INTO tipo_evento(tpev_nombre) VALUES ('Apagado por Sensor')
INSERT INTO tipo_evento(tpev_nombre) VALUES ('Envio de mail por sensor')
INSERT INTO tipo_evento(tpev_nombre) VALUES ('Envio de SMS por sensor')


INSERT INTO tipo_clima(tpcm_nombre) VALUES ('Tormenta')
INSERT INTO tipo_clima(tpcm_nombre) VALUES ('Tormenta Tropical')
INSERT INTO tipo_clima(tpcm_nombre) VALUES ('Huracan')
INSERT INTO tipo_clima(tpcm_nombre) VALUES ('Tormentas Electrica Severas')
INSERT INTO tipo_clima(tpcm_nombre) VALUES ('Tormentas Electrica')
INSERT INTO tipo_clima(tpcm_nombre) VALUES ('Luvia y Nieve')
INSERT INTO tipo_clima(tpcm_nombre) VALUES ('Luvia aguanieve')
INSERT INTO tipo_clima(tpcm_nombre) VALUES ('Aguanieve')
INSERT INTO tipo_clima(tpcm_nombre) VALUES ('Llovizna Helada')
INSERT INTO tipo_clima(tpcm_nombre) VALUES ('Llovizna')
INSERT INTO tipo_clima(tpcm_nombre) VALUES ('Luvia Helada')
INSERT INTO tipo_clima(tpcm_nombre) VALUES ('Lluvia')
INSERT INTO tipo_clima(tpcm_nombre) VALUES ('Lluvia')
INSERT INTO tipo_clima(tpcm_nombre) VALUES ('Copos de Nieve')
INSERT INTO tipo_clima(tpcm_nombre) VALUES ('Lluvia Ligera')
INSERT INTO tipo_clima(tpcm_nombre) VALUES ('Nieve y Viento')
INSERT INTO tipo_clima(tpcm_nombre) VALUES ('Nieve')
INSERT INTO tipo_clima(tpcm_nombre) VALUES ('granizo')
INSERT INTO tipo_clima(tpcm_nombre) VALUES ('Aguanieve')
INSERT INTO tipo_clima(tpcm_nombre) VALUES ('Polvo')
INSERT INTO tipo_clima(tpcm_nombre) VALUES ('Brumoso')
INSERT INTO tipo_clima(tpcm_nombre) VALUES ('Neblina')
INSERT INTO tipo_clima(tpcm_nombre) VALUES ('Niebla')
INSERT INTO tipo_clima(tpcm_nombre) VALUES ('Borroscoso')
INSERT INTO tipo_clima(tpcm_nombre) VALUES ('Ventoso')
INSERT INTO tipo_clima(tpcm_nombre) VALUES ('Frio')
INSERT INTO tipo_clima(tpcm_nombre) VALUES ('Nublado')
INSERT INTO tipo_clima(tpcm_nombre) VALUES ('Mayormente Nublado')
INSERT INTO tipo_clima(tpcm_nombre) VALUES ('Mayormente Nublado')
INSERT INTO tipo_clima(tpcm_nombre) VALUES ('Parcialmente Nublado')
INSERT INTO tipo_clima(tpcm_nombre) VALUES ('Parcialmente Nublado')
INSERT INTO tipo_clima(tpcm_nombre) VALUES ('Despejado')
INSERT INTO tipo_clima(tpcm_nombre) VALUES ('Soleado')
INSERT INTO tipo_clima(tpcm_nombre) VALUES ('Despejado')
INSERT INTO tipo_clima(tpcm_nombre) VALUES ('Despejado')
INSERT INTO tipo_clima(tpcm_nombre) VALUES ('Lluvia Granizo')
INSERT INTO tipo_clima(tpcm_nombre) VALUES ('Caluroso')
INSERT INTO tipo_clima(tpcm_nombre) VALUES ('Tormentas Aisladas')
INSERT INTO tipo_clima(tpcm_nombre) VALUES ('Tormentas Electricas Aisladas')
INSERT INTO tipo_clima(tpcm_nombre) VALUES ('Tormentas Electricas Aisladas')
INSERT INTO tipo_clima(tpcm_nombre) VALUES ('Lluvia Dispersa')
INSERT INTO tipo_clima(tpcm_nombre) VALUES ('Nevadas Severas')
INSERT INTO tipo_clima(tpcm_nombre) VALUES ('Nieve Sispersa')
INSERT INTO tipo_clima(tpcm_nombre) VALUES ('Nevadas Severas')
INSERT INTO tipo_clima(tpcm_nombre) VALUES ('Parcialmente Nublado')
INSERT INTO tipo_clima(tpcm_nombre) VALUES ('Chubascos')
INSERT INTO tipo_clima(tpcm_nombre) VALUES ('Nieve')
INSERT INTO tipo_clima(tpcm_nombre) VALUES ('Chubascos Aislados')
INSERT INTO tipo_clima(tpcm_nombre) VALUES ('Sin informacion')


INSERT INTO propiedad(prpd_nombre, prpd_mail, prpd_direccion) VALUES ('Casa', 'camilo.molina.orth@gmail.com', 'Av. Antonio Varas 868 El Bosque Santiago')

INSERT INTO dias(dias_valor, dias_nombre) values (1, 'Lunes')
INSERT INTO dias(dias_valor, dias_nombre) values (2, 'Martes')
INSERT INTO dias(dias_valor, dias_nombre) values (3, 'Miercoles')
INSERT INTO dias(dias_valor, dias_nombre) values (4, 'Jueves')
INSERT INTO dias(dias_valor, dias_nombre) values (5, 'Viernes')
INSERT INTO dias(dias_valor, dias_nombre) values (6, 'Sábado')
INSERT INTO dias(dias_valor, dias_nombre) values (7, 'Domingo')

INSERT INTO tipo_programacion(tppg_nombre) VALUES ('Diaria')
INSERT INTO tipo_programacion(tppg_nombre) VALUES ('Semanal')
INSERT INTO tipo_programacion(tppg_nombre) VALUES ('Mensual')
INSERT INTO tipo_programacion(tppg_nombre) VALUES ('Anual')

INSERT INTO estado_sincronizacion(etsz_nombre) VALUES ('Pendiente')
INSERT INTO estado_sincronizacion(etsz_nombre) VALUES ('Terminado')
INSERT INTO estado_sincronizacion(etsz_nombre) VALUES ('Fallido')


INSERT INTO nivel(nvel_nombre, prpd_id, nvel_activo) VALUES('Primer Piso', 1, true);
INSERT INTO nivel(nvel_nombre, prpd_id, nvel_activo) VALUES('Segundo Piso', 1, true);

INSERT INTO mapa(mapa_nombre, nvel_id, mapa_path, mapa_informacion, mapa_descripcion, mapa_orden, mapa_activo) VALUES('Aereo', 1, '/plcbus/images/planner_level_1-0v2.png', 'Mapa aereo del primer nivel', 'Vista aerea del primer nivel', 1, true);
INSERT INTO mapa(mapa_nombre, nvel_id, mapa_path, mapa_informacion, mapa_descripcion, mapa_orden, mapa_activo) VALUES('Delantero izquierdo', 1, '/plcbus/images/planner_level_1-1.png', 'Vista delantera izquierda', 'Vista delantera izquierda del primer nivel', 2, true);
INSERT INTO mapa(mapa_nombre, nvel_id, mapa_path, mapa_informacion, mapa_descripcion, mapa_orden, mapa_activo) VALUES('Delantero derecho', 1, '/plcbus/images/planner_level_1-2.png', 'Vista delantera derecha', 'Vista delantera derecha del primer nivel', 3, true);
INSERT INTO mapa(mapa_nombre, nvel_id, mapa_path, mapa_informacion, mapa_descripcion, mapa_orden, mapa_activo) VALUES('Trasero izquierdo', 1, '/plcbus/images/planner_level_1-3.png', 'Vista trasera izquierda', 'Vista trasera izquierda del primer nivel', 4, true);
INSERT INTO mapa(mapa_nombre, nvel_id, mapa_path, mapa_informacion, mapa_descripcion, mapa_orden, mapa_activo) VALUES('Trasero derecho', 1, '/plcbus/images/planner_level_1-4.png', 'Vista trasera derecha', 'Vista trasera derecha del primer nivel', 5, true);

INSERT INTO mapa(mapa_nombre, nvel_id, mapa_path, mapa_informacion, mapa_descripcion, mapa_orden, mapa_activo) VALUES('Aereo', 2, '/plcbus/images/planner_level_2-0v2.png', 'Mapa aereo del primer nivel', 'Vista aerea del primer nivel', 1, true);
INSERT INTO mapa(mapa_nombre, nvel_id, mapa_path, mapa_informacion, mapa_descripcion, mapa_orden, mapa_activo) VALUES('Delantero izquierdo', 2, '/plcbus/images/planner_level_2-1.png', 'Vista delantera izquierda', 'Vista delantera izquierda del primer nivel', 2, true);
INSERT INTO mapa(mapa_nombre, nvel_id, mapa_path, mapa_informacion, mapa_descripcion, mapa_orden, mapa_activo) VALUES('Delantero derecho', 2, '/plcbus/images/planner_level_2-2.png', 'Vista delantera derecha', 'Vista delantera derecha del primer nivel', 3, true);
INSERT INTO mapa(mapa_nombre, nvel_id, mapa_path, mapa_informacion, mapa_descripcion, mapa_orden, mapa_activo) VALUES('Trasero izquierdo', 2, '/plcbus/images/planner_level_2-3.png', 'Vista trasera izquierda', 'Vista trasera izquierda del primer nivel', 4, true);
INSERT INTO mapa(mapa_nombre, nvel_id, mapa_path, mapa_informacion, mapa_descripcion, mapa_orden, mapa_activo) VALUES('Trasero derecho', 2, '/plcbus/images/planner_level_2-4.png', 'Vista trasera derecha', 'Vista trasera derecha del primer nivel', 5, true);


INSERT INTO zona(zona_nombre, nvel_id, zona_activo) VALUES('Living', 1, true);
INSERT INTO zona(zona_nombre, nvel_id, zona_activo) VALUES('Sala Pool', 1, true);
INSERT INTO zona(zona_nombre, nvel_id, zona_activo) VALUES('Cocina', 1, true);
INSERT INTO zona(zona_nombre, nvel_id, zona_activo) VALUES('Patio Trasero', 1, true);
INSERT INTO zona(zona_nombre, nvel_id, zona_activo) VALUES('Patio Lateral Izq', 1, true);
INSERT INTO zona(zona_nombre, nvel_id, zona_activo) VALUES('Patio Lateral Der', 1, true);
INSERT INTO zona(zona_nombre, nvel_id, zona_activo) VALUES('Patio delantero', 1, true);
INSERT INTO zona(zona_nombre, nvel_id, zona_activo) VALUES('Balcon', 2, true);
INSERT INTO zona(zona_nombre, nvel_id, zona_activo) VALUES('Pieza Tania', 2, true);
INSERT INTO zona(zona_nombre, nvel_id, zona_activo) VALUES('Pieza Camilo', 2, true);

INSERT INTO zona(zona_nombre, nvel_id, zona_activo) VALUES('Pieza Tareas Tania', 2, true);
INSERT INTO zona(zona_nombre, nvel_id, zona_activo) VALUES('Pieza Trabajo', 2, true);
INSERT INTO zona(zona_nombre, nvel_id, zona_activo) VALUES('Pasillo Juegos', 2, true);
INSERT INTO zona(zona_nombre, nvel_id, zona_activo) VALUES('Pasillo delantero', 2, true);
INSERT INTO zona(zona_nombre, nvel_id, zona_activo) VALUES('Baño', 1, true);
INSERT INTO zona(zona_nombre, nvel_id, zona_activo) VALUES('Baño', 2, true);
INSERT INTO zona(zona_nombre, nvel_id, zona_activo) VALUES('Balcon delantero', 2, true);
INSERT INTO zona(zona_nombre, nvel_id, zona_activo) VALUES('Balcon trasero', 2, true);

INSERT INTO tipo_camara(tpcm_modelo) VALUES('IPC-100');


INSERT INTO dispositivo(dpst_nombre, zona_id, tpdp_id, dpst_activo, dpst_informacion, dpst_estado, dpst_alarmado) VALUES('A1', 1, 1, true, 'Living1', false, false);
INSERT INTO dispositivo(dpst_nombre, zona_id, tpdp_id, dpst_activo, dpst_informacion, dpst_estado, dpst_alarmado) VALUES('A2', 1, 1, true, 'Living2', false, false);
INSERT INTO dispositivo(dpst_nombre, zona_id, tpdp_id, dpst_activo, dpst_informacion, dpst_estado, dpst_alarmado) VALUES('A3', 7, 1, true, 'Frontis', false, false);
INSERT INTO dispositivo(dpst_nombre, zona_id, tpdp_id, dpst_activo, dpst_informacion, dpst_estado, dpst_alarmado) VALUES('A4', 7, 1, false, '', false, false);
INSERT INTO dispositivo(dpst_nombre, zona_id, tpdp_id, dpst_activo, dpst_informacion, dpst_estado, dpst_alarmado) VALUES('A5', 5, 1, true, 'Cocina Exterior', false, false);
INSERT INTO dispositivo(dpst_nombre, zona_id, tpdp_id, dpst_activo, dpst_informacion, dpst_estado, dpst_alarmado) VALUES('A6', 3, 2, true, 'Enchufe Cocina', false, false);
INSERT INTO dispositivo(dpst_nombre, zona_id, tpdp_id, dpst_activo, dpst_informacion, dpst_estado, dpst_alarmado) VALUES('A7', 2, 1, true, 'Pasillo 1° Piso', false, false);
INSERT INTO dispositivo(dpst_nombre, zona_id, tpdp_id, dpst_activo, dpst_informacion, dpst_estado, dpst_alarmado) VALUES('A8', 3, 1, true, 'Cocina', false, false);
INSERT INTO dispositivo(dpst_nombre, zona_id, tpdp_id, dpst_activo, dpst_informacion, dpst_estado, dpst_alarmado) VALUES('A9', 6, 1, true, 'Patio Lateral Vehiculos', false, false);
INSERT INTO dispositivo(dpst_nombre, zona_id, tpdp_id, dpst_activo, dpst_informacion, dpst_estado, dpst_alarmado) VALUES('A10', 6, 2, true, 'Enchufe Patio Lateral Vehiculos', false, false);

INSERT INTO dispositivo(dpst_nombre, zona_id, tpdp_id, dpst_activo, dpst_informacion, dpst_estado, dpst_alarmado) VALUES('B1', 2, 1, true, 'Pool', false, false);
INSERT INTO dispositivo(dpst_nombre, zona_id, tpdp_id, dpst_activo, dpst_informacion, dpst_estado, dpst_alarmado) VALUES('B2', 2, 1, false, '', false, false);
INSERT INTO dispositivo(dpst_nombre, zona_id, tpdp_id, dpst_activo, dpst_informacion, dpst_estado, dpst_alarmado) VALUES('B3', 2, 1, true, 'Ducha', false, false);
INSERT INTO dispositivo(dpst_nombre, zona_id, tpdp_id, dpst_activo, dpst_informacion, dpst_estado, dpst_alarmado) VALUES('B4', 2, 1, true, 'Planzador', false, false);
INSERT INTO dispositivo(dpst_nombre, zona_id, tpdp_id, dpst_activo, dpst_informacion, dpst_estado, dpst_alarmado) VALUES('B5', 4, 1, false, '', false, false);
INSERT INTO dispositivo(dpst_nombre, zona_id, tpdp_id, dpst_activo, dpst_informacion, dpst_estado, dpst_alarmado) VALUES('B6', 4, 1, true, 'Patrio Trasero', false, false);

INSERT INTO dispositivo(dpst_nombre, zona_id, tpdp_id, dpst_activo, dpst_informacion, dpst_estado, dpst_alarmado) VALUES('B7', 1, 1, true, 'Sala TV', false, false);
INSERT INTO dispositivo(dpst_nombre, zona_id, tpdp_id, dpst_activo, dpst_informacion, dpst_estado, dpst_alarmado) VALUES('B8', 1, 2, true, 'Enchufe Sala TV', false, false);
INSERT INTO dispositivo(dpst_nombre, zona_id, tpdp_id, dpst_activo, dpst_informacion, dpst_estado, dpst_alarmado) VALUES('B9', 10, 1, false, 'Balcon', false, false);
INSERT INTO dispositivo(dpst_nombre, zona_id, tpdp_id, dpst_activo, dpst_informacion, dpst_estado, dpst_alarmado) VALUES('B10', 10, 1, false, 'Pasillo', false, false);
INSERT INTO dispositivo(dpst_nombre, zona_id, tpdp_id, dpst_activo, dpst_informacion, dpst_estado, dpst_alarmado) VALUES('C1', 9, 2, true, 'Enchufe Escalera', false, false);
INSERT INTO dispositivo(dpst_nombre, zona_id, tpdp_id, dpst_activo, dpst_informacion, dpst_estado, dpst_alarmado) VALUES('C2', 9, 1, true, 'Escalera', false, false);
INSERT INTO dispositivo(dpst_nombre, zona_id, tpdp_id, dpst_activo, dpst_informacion, dpst_estado, dpst_alarmado) VALUES('C3', 14, 1, true, 'Pieza Camilo 1', false, false);
INSERT INTO dispositivo(dpst_nombre, zona_id, tpdp_id, dpst_activo, dpst_informacion, dpst_estado, dpst_alarmado) VALUES('C4', 14, 1, true, 'Pieza Camilo 2', false, false);

INSERT INTO dispositivo(dpst_nombre, zona_id, tpdp_id, dpst_activo, dpst_informacion, dpst_estado, dpst_alarmado) VALUES('C5', 13, 1, true, 'Biblioteca', false, false);
INSERT INTO dispositivo(dpst_nombre, zona_id, tpdp_id, dpst_activo, dpst_informacion, dpst_estado, dpst_alarmado) VALUES('C6', 13, 1, false, '', false, false);
INSERT INTO dispositivo(dpst_nombre, zona_id, tpdp_id, dpst_activo, dpst_informacion, dpst_estado, dpst_alarmado) VALUES('C7', 13, 1, true, 'Baño Tania', false, false);
INSERT INTO dispositivo(dpst_nombre, zona_id, tpdp_id, dpst_activo, dpst_informacion, dpst_estado, dpst_alarmado) VALUES('C8', 13, 1, true, 'Pieza Tania', false, false);


/*
alter table dispositivo drop COLUMN dpst_host;
alter table dispositivo drop COLUMN dpst_port;
alter table dispositivo drop COLUMN dpst_descripcion ;

alter table evento drop COLUMN tpdp_nombre;


alter table dispositivo ALTER COLUMN dpst_nombre RENAME TO dpst_codigo;
alter table dispositivo ALTER COLUMN dpst_informacion RENAME TO dpst_nombre;
alter table dispositivo add column dpst_descripcion varchar(1000)

create table tipo_dispositivo_general (
	tpdg_id integer GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL PRIMARY KEY
	, tpdg_nombre varchar(60) UNIQUE not null
	, tpdg_control_tiempo boolean not null
	, tpdg_activo boolean not null
);

alter table tipo_dispositivo add column tpdg_id integer
update dispositivo set tpdp_id = 1

update tipo_dispositivo set tpdp_nombre =  'TMP1' where tpdp_id = 1
update tipo_dispositivo set tpdp_nombre =  'TMP2' where tpdp_id = 2
update tipo_dispositivo set tpdp_nombre =  'TMP3' where tpdp_id = 3
update tipo_dispositivo set tpdp_nombre =  'TMP4' where tpdp_id = 4
update tipo_dispositivo set tpdp_nombre =  'TMP5' where tpdp_id = 5
update tipo_dispositivo set tpdp_nombre =  'TMP6' where tpdp_id = 6


update tipo_dispositivo set tpdp_nombre =  'Filamento' where tpdp_id = 1
update tipo_dispositivo set tpdp_nombre =  'Fluorescente' where tpdp_id = 2
update tipo_dispositivo set tpdp_nombre =  'LED' where tpdp_id = 3
update tipo_dispositivo set tpdp_nombre =  'Dimmer' where tpdp_id = 4
update tipo_dispositivo set tpdp_nombre =  'Halogeno' where tpdp_id = 5
update tipo_dispositivo set tpdp_nombre =  'Enchufe' where tpdp_id = 6



INSERT INTO tipo_dispositivo(tpdg_id, tpdp_nombre, tpdp_activo) VALUES (2, 'Alta Carga', true)
INSERT INTO tipo_dispositivo(tpdg_id, tpdp_nombre, tpdp_activo) VALUES (2, 'Router', true)
INSERT INTO tipo_dispositivo(tpdg_id, tpdp_nombre, tpdp_activo) VALUES (2, 'Ventilador', true)
INSERT INTO tipo_dispositivo(tpdg_id, tpdp_nombre, tpdp_activo) VALUES (2, 'Calienta Cama', true)
INSERT INTO tipo_dispositivo(tpdg_id, tpdp_nombre, tpdp_activo) VALUES (2, 'Radio', true)
INSERT INTO tipo_dispositivo(tpdg_id, tpdp_nombre, tpdp_activo) VALUES (2, 'TV', true)
INSERT INTO tipo_dispositivo(tpdg_id, tpdp_nombre, tpdp_activo) VALUES (2, 'DVD', true)
INSERT INTO tipo_dispositivo(tpdg_id, tpdp_nombre, tpdp_activo) VALUES (2, 'Audio', true)
INSERT INTO tipo_dispositivo(tpdg_id, tpdp_nombre, tpdp_activo) VALUES (2, 'Lampara', true)
INSERT INTO tipo_dispositivo(tpdg_id, tpdp_nombre, tpdp_activo) VALUES (2, 'Cargador', true)
INSERT INTO tipo_dispositivo(tpdg_id, tpdp_nombre, tpdp_activo) VALUES (3, 'Aspersores', true)
INSERT INTO tipo_dispositivo(tpdg_id, tpdp_nombre, tpdp_activo) VALUES (3, 'Difusores', true)
INSERT INTO tipo_dispositivo(tpdg_id, tpdp_nombre, tpdp_activo) VALUES (3, 'Goteo', true)
INSERT INTO tipo_dispositivo(tpdg_id, tpdp_nombre, tpdp_activo) VALUES (3, 'Exudacion', true)
INSERT INTO tipo_dispositivo(tpdg_id, tpdp_nombre, tpdp_activo) VALUES (3, 'Subterraneo', true)
INSERT INTO tipo_dispositivo(tpdg_id, tpdp_nombre, tpdp_activo) VALUES (3, 'Microaspersores', true)
INSERT INTO tipo_dispositivo(tpdg_id, tpdp_nombre, tpdp_activo) VALUES (4, 'Roller', true)
INSERT INTO tipo_dispositivo(tpdg_id, tpdp_nombre, tpdp_activo) VALUES (4, 'Metalica', true)
INSERT INTO tipo_dispositivo(tpdg_id, tpdp_nombre, tpdp_activo) VALUES (5, 'Humo', true)
INSERT INTO tipo_dispositivo(tpdg_id, tpdp_nombre, tpdp_activo) VALUES (5, 'Movimiento', true)
INSERT INTO tipo_dispositivo(tpdg_id, tpdp_nombre, tpdp_activo) VALUES (5, 'Proximidad', true)
INSERT INTO tipo_dispositivo(tpdg_id, tpdp_nombre, tpdp_activo) VALUES (6, 'IP', true)
INSERT INTO tipo_dispositivo(tpdg_id, tpdp_nombre, tpdp_activo) VALUES (7, 'IR', true)


alter table programacion drop column prgm_activo
alter table programacion add column prgm_activo boolean default true not null





create table tipo_accion_movimiento (
	tpam_id integer GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL PRIMARY KEY
	, tpam_nombre varchar(60) UNIQUE not null
);

create table tipo_movimiento (
	tpmv_id integer GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL PRIMARY KEY
	, tpmv_nombre varchar(60) UNIQUE not null
);

create table accion_movimiento (
	acmv_id integer GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL PRIMARY KEY
	, tpmv_id integer not null
	, dpst_id integer not null
	, acmv_motivo varchar(1000)
	, acmv_activo boolean not null
	, acmv_usuario_creador varchar(60)
	, acmv_fecha_creacion datetime
	, acmv_usuario_actualizador varchar(60)
	, acmv_fecha_actualizacion datetime

	, foreign key (tpmv_id) references tipo_movimiento (tpmv_id)
	, foreign key (dpst_id) references dispositivo (dpst_id)
);

create table detalle_accion_movimiento (
	damv_id integer GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL PRIMARY KEY
	, acmv_id integer not null
	, tpam_id integer not null
	, dpst_id integer null
	, damv_usuario_creador varchar(60)
	, damv_fecha_creacion datetime
	, damv_usuario_actualizador varchar(60)
	, damv_fecha_actualizacion datetime

	, foreign key (acmv_id) references accion_movimiento (acmv_id)
	, foreign key (tpam_id) references tipo_accion_movimiento (tpam_id)
	, foreign key (dpst_id) references dispositivo (dpst_id)
);

INSERT INTO tipo_movimiento(tpmv_nombre) VALUES('Encendido');
INSERT INTO tipo_movimiento(tpmv_nombre) VALUES('Apagado');

INSERT INTO tipo_accion_movimiento(tpam_nombre) VALUES('Encender dispositivo');
INSERT INTO tipo_accion_movimiento(tpam_nombre) VALUES('Apagar dispositivo');
INSERT INTO tipo_accion_movimiento(tpam_nombre) VALUES('Envio de Mail');
INSERT INTO tipo_accion_movimiento(tpam_nombre) VALUES('Envio de SMS');

update dispositivo set dpst_nivel_ruido = 0, dpst_nivel_senhal = 26

update dispositivo set DPST_ULTIMO_ENCENDIDO = '2015-03-31 15:08:53.095', DPST_ALARMADO = false, DPST_ESTADO = true where DPST_ID = 1


alter table dispositivo ALTER COLUMN dpst_codigo varchar(10)


INSERT INTO camara(dpst_id, cmra_nombre, cmra_ip, cmra_port, tpcm_id) VALUES(99, 'Pool', '191.292.292.4', 10801, 1);


 */