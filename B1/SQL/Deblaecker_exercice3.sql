-- Rqt 1
-- Que est le client "inactif" qui a loué le plus de film (customer_id, first_name, last_name, nbFilms)
SELECT rental.customer_id, customer.first_name, customer.last_name, COUNT(*) AS nbFilms FROM rental INNER JOIN customer ON rental.customer_id=customer.customer_id WHERE rental.customer_id IN (SELECT customer_id FROM customer WHERE active=0) GROUP BY rental.customer_id ORDER BY nbFilms DESC LIMIT 1;-- Rqt 2
-- Qui sont les clients qui ont des films en retards, quels sont ces films et combien de jours de retard y a t'il (customer_id, first_name, last_name, phone, film_id, title, nbJoursRetard) 
-- Classé par numéro de client, numéro de film
SELECT c.customer_id, first_name, last_name, phone, f.film_id, title, TO_DAYS(NOW()) - TO_DAYS(rental_date) + rental_duration AS nbJoursRetardFROM film f INNER JOIN inventory i  ON i.film_id = f.film_id  INNER JOIN rental r  ON r.inventory_id = i.inventory_idINNER JOIN customer c  ON c.customer_id = r.customer_id  INNER JOIN address a  ON a.address_id = c.address_idWHERE TO_DAYS(NOW()) > TO_DAYS(rental_date) + rental_durationORDER BY customer_id ASC, film_id ASC;
-- Rqt 3
-- Quels sont les clients ayant les même noms/prénoms que des acteurs (customer_id, first_name, last_name, actor_id)
-- Classé par numéro de client
SELECT customer_id, a.first_name, a.last_name, actor_id FROM actor a, customer c WHERE a.first_name=c.first_name AND a.last_name=c.last_name ORDER BY customer_id;
-- Rqt 4
-- Quel est le montant des locations de chaque client (customer_id, first_name, last_name, montantTotal)
-- Classé par numéro de client
SELECT c.customer_id, first_name, last_name, SUM(p.amount) as montantTotal FROM customer c, payment p WHERE p.customer_id=c.customer_id GROUP BY customer_id;
-- Rqt 5
-- Quels sont les films qui ne sont pas inventoriés (film_id, title)
-- classé par film_id 
SELECT f.film_id, title FROM film f WHERE f.film_id NOT IN (SELECT film_id FROM inventory) GROUP BY film_id;
-- Rqt 6
-- Quel est le magasin qui a le plus de ventes (en nombre) (store_id, city, nbVentes)
SELECT store_id, city,  COUNT(amount) AS nbVentes FROM payment INNER JOIN store ON store.store_id=payment.staff_id INNER JOIN address ON address.address_id=store.address_id INNER JOIN city ON city.city_id=address.city_id  GROUP BY staff_id ORDER BY nbVentes DESC LIMIT  1;
-- Rqt 7
-- Quel est le magasin qui a le plus grand chiffre d'affaire (store_id, city, country, ca)
SELECT store_id, city, country, SUM(amount) AS ca FROM payment INNER JOIN store ON store.store_id=payment.staff_id INNER JOIN address ON address.address_id=store.address_id INNER JOIN city ON city.city_id=address.city_id INNER JOIN country ON country.country_id=city.country_id GROUP BY staff_id DESC LIMIT  1;
-- Rqt 8
-- Quel est l'acteur le plus loué (actor_id, name, nbFilmsLoues)
SELECT a.actor_id, last_name, COUNT(*) AS nbFilmsLoues FROM film f, film_actor fa, actor a, rental r, inventory i WHERE f.film_id = fa.film_id AND fa.actor_id = a.actor_id AND f.film_id = i.film_id AND i.inventory_id = r.inventory_id;