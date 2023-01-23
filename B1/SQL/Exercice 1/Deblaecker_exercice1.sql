-- exercice 1 : Les sélections 

-- Rqt 1
-- Afficher le numéro, le nom, le prénom, le genre et la date de naissance de chaque employé. ( emp_no, last_name, first_name,  gender, birth_date )
SELECT emp_no,last_name,first_name,gender,birth_date from employees;
-- Rqt 2 
-- Trouver tous les employés dont le prénom est 'Troy'. ( emp_no, first_name, last_name, gender )
SELECT emp_no,first_name,last_name,gender from employees WHERE first_name='Troy';
-- Rqt 3 
-- Trouver tous les employés de sexe féminin ( * ).
SELECT emp_no,first_name,last_name,gender from employees WHERE gender='F';
-- Rqt 4 
-- Trouver tous les employés de sexe masculin nés après le 31 janvier 1965 ( * ).
SELECT emp_no,first_name,last_name,gender, birth_date from employees WHERE gender='M' AND birth_date>'1965-01-31';
-- Rqt 5
-- Lister uniquement les titres des employés, sans que les valeurs apparaissent plusieurs fois. (title)
SELECT DISTINCT title from titles;
-- Rqt 6
-- Combien y a t'il de département ? ( nombreDep )
SELECT count(*) AS 'nombreDep'FROM departments;
-- Rqt 7
-- Qui a eu le salaire maximum de tous les temps, et quel est le montant de ce salaire ? (emp_no, maxSalary)
SELECT e.emp_no, last_name, first_name, MAX(salary) AS maxSalary FROM employees e, salaries t WHERE e.emp_no=t.emp_no;
-- Rqt 8
-- Quel est salaire moyen de l'employé numéro 287323 toute période confondue ?  (emp_no, salaireMoy)
SELECT AVG(salary)as 'salaireMoy' from salaries WHERE emp_no=287323;
-- Rqt 9
-- Qui sont les employés dont le prénom fini par 'ard' (*)
SELECT first_name from employees WHERE first_name LIKE('%ard');
-- Rqt 10
-- Combien de personnes dont le prénom est 'Richard' sont des femmes
SELECT count(*) as 'name' FROM employees WHERE gender='F' AND first_name='Richard';
-- Rqt 11
-- Combien y a t'il de titre différents d'employés (nombreTitre) 
SELECT count(DISTINCT title) as 'nombreTitre' from titles;
-- Rqt 12
-- Dans combien de département différents a travaillé l'employé numéro 287323 (nombreDep)
select COUNT(*) as'nombreDep', dept_no from  dept_emp where emp_no='287323';
-- Rqt 13
-- Quels sont les employés qui ont été embauchés en janvier 2000. (*)
-- Les résultats doivent être ordonnés par date d'embauche chronologique
SELECT hire_date, first_name, last_name  FROM employees WHERE hire_date between '2000-01-01' AND '2000-01-31' ORDER BY hire_date;
-- Rqt 14
-- Quelle est la somme cumulée des salaires de toute la société (sommeSalaireTotale)
SELECT SUM(salary) AS sommeSalaireTotale FROM salaries;
-- Rqt 15
-- Quel était le titre de Danny Rando le 12 janvier 1990 ? (emp_no, first_name, last_name, title)
SELECT employees.emp_no, first_name, last_name, title FROM titles INNER JOIN employees ON titles.emp_no = employees.emp_no WHERE employees.emp_no = ( SELECT emp_no FROM employees WHERE first_name = 'Danny' AND last_name = 'RANDO' ) AND from_date >= '1990-01-12';
-- Rqt 16
-- Combien d'employés travaillaient dans le département 'Sales' le 1er Janvier 2000 (nbEmp)
SELECT COUNT(c.emp_no) AS nbEmp, d.dept_name FROM dept_emp c, departments d WHERE d.dept_name='Sales' AND d.dept_no=c.dept_no AND c.from_date<='2000-01-01' AND c.to_date>='2000-01-01';
-- Rqt 17
-- Combien d'employés travaillaient dans le département 'Sales' le 1er Janvier 2000 (nbEmp)
SELECT COUNT(c.emp_no) AS nbEmp, d.dept_name FROM dept_emp c, departments d WHERE d.dept_name='Sales' AND d.dept_no=c.dept_no AND c.from_date<='2000-01-01' AND c.to_date>='2000-01-01';
-- Rqt 18
-- Quelle est la somme cumulée des salaires de tous les employés dont le prénom est Richard (emp_no, first_name, last_name, sommeSalaire)
SELECT SUM(s.salary)AS sommeSalaire,e.first_name ,count(e.first_name) as totalRichard FROM salaries s, employees e WHERE e.emp_no=s.emp_no AND e.first_name='Richard';
-- Rqt 19
-- Indiquer pour chaque prénom 'Richard', 'Leandro', 'Lena', le nombre de chaque genre (first_name, gender, nombre). 
-- Les résultats seront ordonnés par prénom décroissant et genre. 
SELECT first_name,gender, count(first_name) as 'nombre' FROM employees WHERE first_name='Leandro' OR first_name='Richard' OR first_name='Lena' GROUP BY first_name,gender ORDER BY first_name DESC;
--Alternative
SELECT first_name, gender,COUNT(gender) AS 'nombre' FROM employees WHERE `first_name` IN ('Richard', 'Leandro', 'Lena') GROUP BY first_name, gender ORDER BY first_name DESC;
-- Rqt 20
-- Quels sont les noms de familles qui apparaissent plus de 200 fois (last_name, nombre)
-- Les résultats seront triés par leur nombre croissant et le nom de famille.
SELECT last_name, COUNT(last_name) AS nombre FROM employees WHERE last_name IN (SELECT last_name FROM employees GROUP BY last_name HAVING COUNT(last_name)>=200) GROUP BY last_name ORDER BY nombre, last_name;
-- Rqt 21
-- Qui sont les employés dont le prénom est Richard qui ont gagné en somme cumulée plus de 1 000 000 (emp_no, first_name, last_name, hire_date, sommeSalaire)

-- Rqt 22
-- Quel est le numéro, nom, prénom  de l'employé qui a eu le salaire maximum de tous les temps, et quel est le montant de ce salaire ? (emp_no, first_name, last_name, title, maxSalary)
-- Alternative 1 :
-- Alternative 2

-- Rqt Bonus ? 
-- Qui est le manager de Martine Hambrick actuellement et quel est son titre (emp_no, first_name, last_name, title) 