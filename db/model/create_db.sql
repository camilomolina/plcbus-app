--==============================================================
-- DBMS name:      ANSI Level 2
-- Created on:     21-11-2013 12:01:54
--==============================================================


drop index DPST__TIENE__TPDP_FK;

drop index DISPOSIVITO__TIENE__NIVEL_FK;

drop index DISPOSITIVO_PK;

drop table DISPOSITIVO cascade;

drop index NIVEL_PK;

drop table NIVEL cascade;

drop index TIPO_DISPOSITIVO_PK;

drop table TIPO_DISPOSITIVO cascade;

--==============================================================
-- Table: TIPO_DISPOSITIVO
--==============================================================
create table TIPO_DISPOSITIVO (
TPDP_ID              INTEGER              not null,
TPDP_NOMBRE          VARCHAR(100)         not null,
TPDP_ACTIVO          smallint             not null,
TPDP_USUARIO_CREADOR VARCHAR(60),
TPDP_FECHA_CREACION  DATE,
TPDP_USUARIO_ACTUALIZADOR VARCHAR(60),
TPDP_FECHA_ACTUALIZACION_7 DATE,
primary key (TPDP_ID)
);

--==============================================================
-- Table: NIVEL
--==============================================================
create table NIVEL (
NVEL_ID              INTEGER              not null,
NVEL_NOMBRE          VARCHAR(100)         not null,
NVEL_ACTIVO          smallint             not null,
NVEL_USUARIO_CREADOR VARCHAR(60),
NVEL_FECHA_CREACION  DATE,
NVEL_USUARIO_ACTUALIZADOR VARCHAR(60),
NVEL_FECHA_ACTUALIZACION DATE,
primary key (NVEL_ID)
);

--==============================================================
-- Table: DISPOSITIVO
--==============================================================
create table DISPOSITIVO (
DPST_ID              INTEGER              not null,
NVEL_ID              INTEGER              not null,
TPDP_ID              INTEGER,
DPST_NOMBRE          VARCHAR(3)           not null,
DPST_INFORMACION     VARCHAR(1000),
DPST_NIVEL_RUIDO     INTEGER,
DPST_NIVEL_SENHAL    INTEGER,
DPST_ACTIVO          smallint             not null,
DPST_ESTADO          smallint             not null,
DPST_ALARMADO        smallint             not null,
DPST_ULTIMO_ENCENDIDO DATE,
DPST_USUARIO_CREADOR VARCHAR(60),
DPST_FECHA_CREACION  DATE,
DPST_USUARIO_ACTUALIZADOR VARCHAR(60),
DPST_FECHA_ACTUALIZACION DATE,
primary key (DPST_ID),
foreign key (TPDP_ID)
      references TIPO_DISPOSITIVO (TPDP_ID),
foreign key (NVEL_ID)
      references NIVEL (NVEL_ID)
);

--==============================================================
-- Index: DISPOSITIVO_PK
--==============================================================
create unique index DISPOSITIVO_PK on DISPOSITIVO (
DPST_ID ASC
);

--==============================================================
-- Index: DISPOSIVITO__TIENE__NIVEL_FK
--==============================================================
create  index DISPOSIVITO__TIENE__NIVEL_FK on DISPOSITIVO (
NVEL_ID ASC
);

--==============================================================
-- Index: DPST__TIENE__TPDP_FK
--==============================================================
create  index DPST__TIENE__TPDP_FK on DISPOSITIVO (
TPDP_ID ASC
);

--==============================================================
-- Index: NIVEL_PK
--==============================================================
create unique index NIVEL_PK on NIVEL (
NVEL_ID ASC
);

--==============================================================
-- Index: TIPO_DISPOSITIVO_PK
--==============================================================
create unique index TIPO_DISPOSITIVO_PK on TIPO_DISPOSITIVO (
TPDP_ID ASC
);

