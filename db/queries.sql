select e.dpst_codigo, e.dpst_nombre, e.evnt_fecha, (select top 1 e2.evnt_fecha from evento e2 where  e2.dpst_codigo = e.dpst_codigo and e2.tpev_id in (2,4, 5))
from evento e
where e.tpev_id in (1,3)

select e.dpst_codigo, e.dpst_nombre, e.evnt_fecha
from evento e
where e.tpev_id = 7

select e.dpst_codigo, count(e.dpst_codigo)
from evento e
where e.tpev_id = 7
group by e.dpst_codigo

update sincronizacion set snzc_fin = current_timestamp where snzc_fin is null

select e.tpev_id
from evento e
group by e.tpev_id

select c.dpst_codigo
, count(c.dpst_codigo)
from consumo c
where c.cnsm_fecha_fin is not null
and c.cnsm_fecha_fin between '2015-02-01 00:00:00.000' and '2015-02-14 23:59:59.000'
group by c.dpst_codigo

select
this_.dpst_codigo as y0_,
this_.dpst_nombre as y1_,
this_.tpdp_id as y2_,
 this_.cnsm_fecha_inicio as y3_,
this_.cnsm_fecha_fin as y4_,
this_.dpst_codigo as y5_,
this_.dpst_nombre as y6_,
 this_.tpdp_id as y7_,
 this_.cnsm_fecha_inicio as y8_,
  this_.cnsm_fecha_fin as y9_
from
  consumo this_
where
    this_.cnsm_fecha_fin is not null
   and this_.cnsm_fecha_fin between ? and ?
group by
 this_.dpst_codigo,
 this_.dpst_nombre,
 this_.tpdp_id,
 this_.cnsm_fecha_inicio,
 this_.cnsm_fecha_fin
order by
 y8_ asc