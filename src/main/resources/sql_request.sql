
--  select all task by date and fr_id :
select  task from day_task where fr_nom = (
 select id from date_task where fr_id =1 and date='2020-02-12');

