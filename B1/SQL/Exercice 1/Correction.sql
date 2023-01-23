-- exercice 1 : Les sélections 

-- Rqt 1
-- Afficher le numéro, le nom, le prénom, le genre et la date de naissance de chaque employé. ( emp_no, last_name, first_name,  gender, birth_date )
SELECT emp_no, last_name, first_name,  gender, birth_date
FROM employees; 

-- Rqt 2 
-- Trouver tous les employés dont le prénom est 'Troy'. ( emp_no, first_name, last_name, gender )
SELECT emp_no, first_name, last_name, gender
FROM employees 
WHERE first_name = 'Troy'; 

-- Rqt 3 
-- Trouver tous les employés de sexe féminin ( * ).
SELECT * 
FROM employees 
WHERE gender = 'F'; 

-- Rqt 4 
-- Trouver tous les employés de sexe masculin nés après le 31 janvier 1965 ( * ).
SELECT * 
FROM employees 
WHERE gender = 'M' and birth_date >= '1965-01-31' ;

-- Rqt 5
-- Lister uniquement les titres des employés, sans que les valeurs apparaissent plusieurs fois. (title)
SELECT DISTINCT title 
FROM titles; 

-- Rqt 6
-- Combien y a t'il de département ? ( nombreDep )
SELECT count(*) AS nombreDep
FROM departments ;

-- Rqt 7
-- Qui a eu le salaire maximum de tous les temps, et quel est le montant de ce salaire ? (emp_no, maxSalary)
SELECT emp_no, max(salary)
FROM salaries; 

-- Rqt 8
-- Quel est salaire moyen de l'employé numéro 287323 toute période confondue ?  (emp_no, salaireMoy)
SELECT emp_no, AVG(salary) AS salaireMoy
FROM salaries 
WHERE emp_no = 287323 ;

-- Rqt 9
-- Qui sont les employés dont le prénom fini par 'ard' (*)
SELECT *
FROM employees 
WHERE first_name like '%ard' ;

-- Rqt 10
-- Combien de personnes dont le prénom est 'Richard' sont des femmes
SELECT count(*) 
FROM employees 
WHERE first_name = 'Richard' and gender = 'F'; 

-- Rqt 11
-- Combien y a t'il de titre différents d'employés (nombreTitre) 
SELECT COUNT(DISTINCT title ) AS nombreTitre
FROM titles; 

-- Rqt 12
-- Dans combien de département différents a travaillé l'employé numéro 287323 (nombreDep)
SELECT count(dept_no) as nombreDep
FROM dept_emp 
WHERE emp_no = 287323; 

-- Rqt 13
-- Quels sont les employés qui ont été embauchés en janvier 2000. (*)
-- Les résultats doivent être ordonnés par date d'embauche chronologique
SELECT * 
FROM employees 
WHERE hire_date BETWEEN '2000-01-01' AND '2000-01-31' 
ORDER BY hire_date;

-- Rqt 14
-- Quelle est la somme cumulée des salaires de toute la société (sommeSalaireTotale)
SELECT SUM(salary) AS sommeSalaireTotale
FROM salaries ; 

-- Rqt 15
-- Quel était le titre de Danny Rando le 12 janvier 1990 ? (emp_no, first_name, last_name, title)
SELECT emp_no, first_name, last_name, title
FROM employees NATURAL JOIN titles 
WHERE first_name = 'Danny' AND last_name = 'Rando' AND '1990-01-12' BETWEEN from_date AND to_date ;
;

-- Rqt 16
-- Combien d'employés travaillaient dans le département 'Sales' le 1er Janvier 2000 (nbEmp)
SELECT COUNT(*)  AS nb_emp 
FROM departments d JOIN dept_emp de ON d.dept_no = de.dept_no 
WHERE dept_name = 'Sales' AND '2000-01-01' BETWEEN from_date AND to_date;

-- Rqt 17
-- Combien d'employés travaillaient dans le département 'Sales' le 1er Janvier 2000 (nbEmp)
SELECT (*)  
FROM departments d JOIN dept_emp de ON d.dept_no = de.dept_no JOIN employees e on de.emp_no = e.emp_no JOIN titles t ON t.emp_no = e.emp_no   
WHERE dept_name = 'Sales' and '2000-01-01' BETWEEN de.from_date AND de.to_date and title = 'Staff' and '2000-01-01' BETWEEN t.from_date  AND t.to_date;

-- Rqt 18
-- Quelle est la somme cumulée des salaires de tous les employés dont le prénom est Richard (emp_no, first_name, last_name, sommeSalaire)
SELECT e.emp_no, first_name, last_name, SUM(salary) AS sommeSalaire
FROM employees e JOIN salaries s ON e.emp_no = s.emp_no 
WHERE first_name = 'Richard' 
GROUP BY s.emp_no ; 


-- Rqt 19
-- Indiquer pour chaque prénom 'Richard', 'Leandro', 'Lena', le nombre de chaque genre (first_name, gender, nombre). 
-- Les résultats seront ordonnés par prénom décroissant et genre. 
SELECT first_name, gender, count(*) AS nombre
FROM employees 
WHERE first_name in ('Richard', 'Leandro', 'Lena') 
GROUP BY first_name, gender 
ORDER BY first_name DESC, gender;
--Alternative
SELECT first_name, gender, count(*) AS nombre
FROM employees 
WHERE first_name = 'Richard' OR first_name = 'Leandro' OR first_name = 'Lena'
GROUP BY first_name, gender 
ORDER BY first_name DESC, gender;


-- Rqt 20
-- Quels sont les noms de familles qui apparaissent plus de 200 fois (last_name, nombre)
-- Les résultats seront triés par leur nombre croissant et le nom de famille.
SELECT last_name,  COUNT(*) AS nombre
FROM employees
GROUP BY last_name
HAVING COUNT(*) > 200
ORDER BY count(*), last_name ; 


-- Rqt 21
-- Qui sont les employés dont le prénom est Richard qui ont gagné en somme cumulée plus de 1 000 000 (emp_no, first_name, last_name, hire_date, sommeSalaire)
SELECT e.emp_no, first_name, last_name, hire_date, SUM(salary) AS sommeSalaire
FROM employees e JOIN salaries s ON e.emp_no = s.emp_no 
WHERE first_name = 'Richard' 
GROUP BY s.emp_no  
HAVING SUM(salary) > 1000000; 

-- Rqt 22
-- Quel est le numéro, nom, prénom  de l'employé qui a eu le salaire maximum de tous les temps, et quel est le montant de ce salaire ? (emp_no, first_name, last_name, title, maxSalary)
SELECT e.emp_no, first_name, last_name,  salary AS maxSalary
FROM salaries s JOIN employees e ON e.emp_no = s.emp_no 
WHERE salary = (SELECT max(salary) FROM salaries ) ; 
-- Alternative 1 :
SELECT e.emp_no, first_name, last_name,  salary AS maxSalary
FROM salaries s JOIN employees e ON e.emp_no = s.emp_no 
WHERE salary >= ALL (SELECT salary FROM salaries ) ; 
-- Alternative 2
SELECT e.emp_no, first_name, last_name,  salary AS maxSalary 
FROM salaries s JOIN employees e ON e.emp_no = s.emp_no  
ORDER BY salary  DESC LIMIT 1 




-- Rqt Bonus ? 
-- Qui est le manager de Martine Hambrick actuellement et quel est son titre (emp_no, first_name, last_name, title) 
SELECT m.emp_no, m.first_name, m.last_name, t.title
FROM employees e 
	NATURAL JOIN dept_emp de 
	NATURAL JOIN departments d 
	JOIN dept_manager dm ON d.dept_no = dm.dept_no 
	JOIN employees m ON dm.emp_no = m.emp_no   
	JOIN titles t ON m.emp_no = t.emp_no  
WHERE e.first_name = 'Martine' and e.last_name = 'Hambrick' and dm.to_date < NOW() and t.to_date < NOW();