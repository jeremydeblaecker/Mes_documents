-- Exercice 2
-- Rqt 1 
-- Quel est actuellement le salaire moyen de chaque titre  (title, salaireMoyen) 
-- Classé par salaireMoyen croissant
SELECT title, AVG(salary) AS salaireMoyen FROM salaries s, titles t WHERE t.emp_no=s.emp_no AND t.from_date<=NOW() AND t.to_date>NOW() GROUP BY title ORDER BY salaireMoyen ASC;
-- Rqt 2
-- Combien de manager différents ont eu les différents  départements (dept_no, dept_name, nbManagers)
-- Classé par nom de département 
SELECT d.dept_no, dept_name, COUNT(emp_no) AS nbManagers FROM dept_manager d, departments e WHERE d.dept_no=e.dept_no GROUP BY d.dept_no ORDER BY dept_name;
-- Rqt 3
-- Quel est le département de la société qui a le salaire moyen le plus élevé (dept_no, dept_name, salaireMoyen) 
SELECT d.dept_no, dept_name, AVG(salary) AS salaireMoyen FROM departments d, dept_emp de, employees e, salaries s WHERE d.dept_no=de.dept_no AND de.emp_no = e.emp_no AND e.emp_no=s.emp_no GROUP BY d.dept_no ORDER BY salaireMoyen DESC LIMIT 1;
-- Rqt 4
-- Quel est le prénom, nom, titre du manager actuel de Georgi Facello (first_name, last_name, title) 
SELECT first_name, last_name, title FROM employees e, dept_manager dm, titles t WHERE e.emp_no=t.emp_no AND e.emp_no=dm.emp_no AND dm.dept_no IN (SELECT dept_no FROM dept_emp WHERE emp_no IN (SELECT emp_no FROM employees WHERE first_name="Georgi" AND last_name="Facello")) AND dm.from_date<=NOW() AND dm.to_date>NOW() AND title='Manager';
-- Rqt 5
-- Quel était le titre de Margareta Markovitch lorsqu'elle était manager, et de quel département était elle manager ? 
-- emp_no, fisrt_name, last_name, dept_no, dept_name, dept_manager.from_date, dept_manager.to_date, title, titles.from_date, titles.to_date
-- Classé par titles.from_date croissant 
SELECT e.emp_no, first_name, last_name, d.dept_no, dept_name, dm.from_date, dm.to_date, title, t.from_date, t.to_date FROM employees e, dept_manager dm, departments d, titles t  WHERE e.emp_no=t.emp_no AND e.emp_no = dm.emp_no AND dm.dept_no=d.dept_no AND e.emp_no=(SELECT emp_no FROM employees WHERE first_name='Margareta' AND last_name='Markovitch') AND title ='Manager' ORDER BY t.from_date ASC;
-- Rqt 6
-- Quels sont les employés qui ont eu le titre de 'Senior Staff' sans avoir le titre de 'Staff' ( emp_no , birth_date , first_name , last_name , gender , hire_date )
-- Classé par emp_no
SELECT employees.emp_no, birth_date, first_name, last_name, gender, hire_date, titles.title FROM employees NATURAL JOIN titles WHERE titles.title = 'Senior Staff' AND employees.emp_no NOT IN (SELECT titles.emp_no FROM titles WHERE titles.title = 'Staff') ORDER BY employees.emp_no;
-- Rqt 7
-- Indiquer le titre et le salaire de chaque employé lors de leur embauche (emp_no, first_name, last_name, title, salary)
-- Classé par nom, prenom, date d'embauche, titre, salaire
SELECT e.emp_no, first_name, last_name, title, salary FROM employees e, titles t, salaries s  WHERE e.emp_no = t.emp_no AND hire_date=s.from_date AND e.emp_no = s.emp_no ORDER BY last_name, first_name, hire_date, title, salary;
-- Rqt 8
-- Quels sont les employés dont le salaire a baissé (emp_no, first_name, last_name)
-- Classé par numéro d'employé
SELECT e.emp_no, first_name, last_name, salary 
FROM employees e, salaries s  
WHERE e.emp_no =s.emp_no AND hire_date=s.from_date AND e.emp_no = s.emp_no AND salary=(SELECT salary FROM salaries WHERE salary WHEN from_date>to_date)
ORDER BY emp_no;

SELECT e.emp_no, first_name, last_name FROM employees e JOIN salaries s ON e.emp_no = s.emp_no WHERE (SELECT salary FROM salaries WHERE to_date >= NOW())< (SELECT salary FROM salaries WHERE to_date < NOW() BETWEEN()) ORDER BY e.emp_no;
