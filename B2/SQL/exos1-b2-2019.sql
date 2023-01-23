-- Liste des adhérents qui n'ont pas de téléphone
SELECT * FROM adherent WHERE id_adherent not in (SELECT id_adherent FROM telephone); 
--variante 
 SELECT *  FROM adherent a LEFT JOIN telephone t on a.id_adherent = t.id_adherent  WHERE t.id_adherent is null;

-- variante 2
SELECT * FROM adherent a WHERE NOT EXISTS (SELECT * FROM telephone t WHERE a.id_adherent = t.id_adherent); 

--Liste des adhérents sans cotisation : 
SELECT * FROM adherent WHERE type_cotisation IS NUL;

-- Le titre des livres écrits pas l'auteure nommée Rowling : 
SELECT * FROM auteur NATURAL JOIN ecrit NATURAL JOIN livre WHERE nom = 'Rowlinng'; 

-- Liste des livres en retard = non rendus (id_livre, titre, nb_jours_retard)
-- Pour ajouter un nombre de jour à une date, en MySQL, on doit utiliser la syntaxe suivante : 
	-- my_date + INTERVAL nbjours DAY
-- Pour obtenir le nombre de jours entre deux date, il faut utiliser la fonction DATEDIFF
SELECT l.id_livre, l.titre,  DATEDIFF(NOW(), (date_emprunt + INTERVAL max_jours_emprunt DAY) ) AS nb_jours_retard
FROM livre l NATURAL JOIN emprunt NATURAL JOIN adherent NATURAL JOIN cotisation
WHERE date_retour IS NULL and DATEDIFF( NOW(), (date_emprunt + INTERVAL max_jours_emprunt DAY)) > 0; 


-- Liste des adhérents ayant eu des retards (id_adh, nom, nb_jours_retards)
-- Un livre en retard est soit : 
	-- Un livre qui n'a toujours pas été rendu, et emprunté depuis plus max_jours_emprunt => date_retour IS NULL
	-- Un livre qui a été rendu, mais après max_jours_emprunt 

SELECT a.id_adherent, a.nom, id_livre, 
	CASE WHEN date_retour IS NULL THEN DATEDIFF(NOW(), (date_emprunt + INTERVAL max_jours_emprunt DAY)) 
	ELSE DATEDIFF( date_retour, (date_emprunt + INTERVAL max_jours_emprunt DAY ))
	END
	AS 'nb_jours_retard'
FROM livre NATURAL JOIN emprunt NATURAL JOIN adherent a NATURAL JOIN cotisation
WHERE ( date_retour IS NULL and DATEDIFF( NOW(), (date_emprunt + INTERVAL max_jours_emprunt DAY)) > 0 ) 
OR DATEDIFF( date_retour, date_emprunt) > max_jours_emprunt; 


-- Variante 
SELECT a.id_adherent, a.nom, id_livre, DATEDIFF(NOW(), (date_emprunt + INTERVAL max_jours_emprunt DAY)) 
	AS 'nb_jours_retard'
FROM livre NATURAL JOIN emprunt NATURAL JOIN adherent a NATURAL JOIN cotisation
WHERE ( date_retour IS NULL and DATEDIFF( NOW(), (date_emprunt + INTERVAL max_jours_emprunt DAY)) > 0 ) 
UNION 
SELECT a.id_adherent, a.nom, id_livre, DATEDIFF( date_retour, (date_emprunt + INTERVAL max_jours_emprunt DAY ))
	AS 'nb_jours_retard'
FROM livre NATURAL JOIN emprunt NATURAL JOIN adherent a NATURAL JOIN cotisation
WHERE DATEDIFF( date_retour, date_emprunt) > max_jours_emprunt; 



-- Pour chaque livre, le nombre d'exemplaires dispo
-- Pour cela, on utilise une sous requête, qui pour chaque livre de la table emprunt, compte le nombre de date_retour qui est NULL (c'est à dire dont le livre n'a pas été rendu). 
-- On fait ensuite une jointure externe entre la table livre et cette sous-requête. 
-- Cela nous permet d'obtenir tous les livres : ceux sont en correspondance avec la sous-requête précédents, ainsi que ceux qui n'ont pas de correspondance (donc non empruntés)
SELECT l.id_livre, l.titre,
CASE WHEN e.id_livre IS NULL THEN nb_exemplaire
ELSE nb_exemplaire - nb_emprunt END
AS nb_dispo
FROM livre l LEFT JOIN ( SELECT id_livre, count(*) AS nb_emprunt FROM emprunt WHERE date_retour IS NULL GROUP BY id_livre) AS e ON l.id_livre = e.id_livre;