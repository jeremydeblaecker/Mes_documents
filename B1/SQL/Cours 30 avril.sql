time : 'hh:mm:sc'
date: 'aaaa-mm-jj'
timeStamp : int
datetime: 'aaaa-mm-jj hh:mm:ss'

select current_timestamp; --Affiche la date et l'heure actuel
select now();--Affiche la date et l'heure actuel
select current_date(); --Affiche la date actuel
select curdate(); --Affiche la date actuel
select current_time; --Affiche l'heure actuel
select curtime();--Affiche l'heure actuel
select convert_tz(now(), '+02:00', '+10:00'); --Converti la time zone du deuxième paramètre en celle de la 3ème
select utc_timestamp(); --Affiche la date et l'heure de la zone utc
select unix_timestamp(); --Affiche la valeur uniz en secondes depuis '1970-01-01 00:00:00'
select day(now()); --Affiche le numéro du jour actuel
select dayname(now()); --Affiche le nom du jour actuel 
select day('1954-07-26'); --Affiche le jour de la date demandé
select dayname('1954-07-26'); --Affiche le nom du jour demandé
select dayofweek('1954-07-26'); --Affiche en nombre le jour de la semaine
select dayofyear('1956-07-26'); --Affiche en nombre le jour de l'année
select minute(now()); --Affiche la minute actuel
select sec_to_time(2768); --Convertir des secondes en minutes
select time_to_sec(); --Convertir des minutes en secondes
select yearweek('1999-04-12'); --Donne l'année et le numéro de la semaine
select weekofyear('1999-04-12'); -- Donne le numéro de la semaine
select quarter(now()); --Affiche le trimestre en cours
select last_day('1999-04-12'); --Affiche le dernier jour du mois
select adddate(now(), INTERVAL 47 DAY); --Ajoute 47 jours à la date actuel
select adddate(now(), INTERVAL 47 HOUR); --Ajoute 47 heures à la date actuel
select adddate(now(), INTERVAL 47 MINUTE); --Ajoute 47 minutes à la date actuel
select addtime('02:00:00', '00:12:15'); --Ajoute la deuxième valeur à la première
select extract(year from now()); --Affiche l'année actuel
select timestampdiff(MONTH, '2000-01-01', now()); --Affiche le nombre de mois entre les deux valeurs
select timestampdiff(year, '2002-01-01', now()); --Pour voir si quelqu'un est majeur
select get_format(DATE, 'EUR'); --Affiche le format de l'année en europe
select date_format(now(), '%d/%m/%Y'); --Permet de formater la date 
select date_format(now(), '%d/%m/%Y %T'); --Permet de formater la date et l'heure
select e.emp_no, last_name, first_name, salary case when salary < 100000 then 'oui' end as 'Petit salaire' case when salary between 100000 and 200000 then 'oui' end as 'Salaire moyen' case when salary > 200000 then 'oui' end as 'Gros salaire' FROM employees e join salaries s on e.emp_no=s.emp_no where now() between from_date and to_date order by salary;


EXERCICE I:
Débutant embauché il y a moins de 20 ans
Confirmé entre 20 et 30
Expert après 30
select last_name, first_name, hire_date, CASE WHEN timestampdiff(YEAR, hire_date, now()) <20 THEN 'DEBUTANT'  when timestampdiff(YEAR, hire_date, now()) between 20 and 30 then 'CONFIRME' when timestampdiff(YEAR, hire_date, now())>30 then 'EXPERT' else null end AS STATUS FROM employees LIMIT 30;


select STATUS, count(STATUS) from (select e.emp_no, e.last_name, e.first_name, e.hire_date, CASE WHEN timestampdiff(YEAR, hire_date, now()) <20 THEN 'DEBUTANT'  when timestampdiff(YEAR, hire_date, now()) between 20 and 30 then 'CONFIRME' when timestampdiff(YEAR, hire_date, now())>30 then 'EXPERT' else null end AS STATUS FROM employees e) as r GROUP BY status;