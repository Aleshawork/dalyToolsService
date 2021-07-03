-- function exists_task_by_person_date()
-- проверяет есть ли запись в date_task на указанную дату
CREATE OR REPLACE FUNCTION exists_task_by_person_date(user_name varchar, date_task date) RETURNS int AS $$
select
    case
        when exists (select dt.id from date_task dt join person p on
                p.id=dt.fr_id where p.username=user_name and dt.date=date_task) then 1
        else 0
        end;
$$ LANGUAGE SQL;
-- ***


-- INSERT_START_TASK
-- добавляет первую  задачу "text" пользователю, использую при регистрации пользователя
CREATE OR REPLACE FUNCTION add_start_task(user_name varchar, date_task date, name_task varchar,priority int) RETURNS VOID AS $$
insert into date_task (fr_id,date,kol_task) values
((select id  from person  where username=user_name),date_task,priority);
insert into day_task (fr_nom,task,priority) values
((select dt.id from date_task dt join person p on
        p.id=dt.fr_id where
          p.username=user_name and dt.date=date_task),
 name_task,1);

$$ LANGUAGE SQL;
-- ***

-- добавляет в таблицу task, который имеет запись на выбранную дату в date_task
CREATE OR REPLACE FUNCTION add_task_in_exists_place(user_name varchar, date_t date, task_t varchar, priority_t int) RETURNS void AS $$
insert into day_task (fr_nom,task,priority) values(
                                                      (select dt.id from date_task dt join person p on dt.fr_id=p.id where p.username=user_name and dt.date=date_t),
                                                      task_t, priority_t);
update date_task  set kol_task=kol_task+1 where fr_id=(select id from person where username=user_name ) and date=date_t;
$$ LANGUAGE SQL;

-- ***

-- добавляет новый task использует add_task_in_exists_place()
CREATE OR REPLACE FUNCTION add_new_task(user_name varchar, date_task date, task varchar,priority int) RETURNS void AS $$
select
    case
        when exists_task_by_person_date(user_name,date_task)=0 then add_start_task(user_name,date_task,task,priority)
        else  add_task_in_exists_place(user_name,date_task,task,priority)
        end;
$$ LANGUAGE SQL;
