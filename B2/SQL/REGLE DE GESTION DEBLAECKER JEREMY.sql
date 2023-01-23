-- Vous donnerez, pour chaque règle de gestion ci-dessus, le code permettant de les gérer.
-- Vous pourrez utiliser des contraintes, procédures stockées, trigger, ...
-- 1) On ne peut pas emprunter un livre qui n'est pas disponible.
ALTER TABLE livre 
ADD CONSTRAINT impossible_emprunte CHECK (nb_exemplaire <=0);
-- 2 La date de rendu doit être ultérieure à la date d'emprunt.
ALTER TABLE emprunt
ADD CONSTRAINT verifiaction_date CHECK (date_retour < date_emprunt);
-- 3) Un adhérent ne peut pas emprunter plus de livres que ce que permet son type de cotisation
ALTER TABLE cotisation
ADD CONSTRAINT verifiaction_cotisation CHECK (max_emprunt);
-- 4) Un adhérent ne peut emprunter s'il n'a pas de type cotisation.
ALTER TABLE adherent 
ADD constraint pas_cotisation CHECK(type_cotisation IS NOT NULL);
-- 5) L'âge de l'adhérent doit être positif => la date de naissance doit être inférieure à la date du jour
ALTER TABLE adherent
ADD CONSTRAINT age CHECK (date_naissance >= NOW());
-- 6) Le nombre d'exemplaire d'un livre ne peut être < 0
ALTER TABLE livre
ADD CONSTRAINT nbr_obligatoire_de_livre CHECK (nb_exemplaire < 0);
-- => on peut modifier le type de donnée. Mais comme il existe déjà une valeur négative, il faudra faire une procédure qui permet la mise à jour de la table livre
-- 7) Lors de l'anniversaire de la date de cotisation, le type de cotisation est recalculé en fonction de l'âge de l'adhérent. La date de cotisation est remis à jour si le type de cotisation change.
ALTER TABLE adherent
ADD CONSTRAINT anniversaire_cotisation CHECK (DATEDIFF(date_naissance, DATE(NOW())) = 0);
-- 8) Un livre doit forcément avoir un ou plusieurs auteurs
ALTER TABLE ecrit
ADD CONSTRAINT nbr_auteurs CHECK (COUNT (id_auteur)>=1); 
-- 9) Le montant de cotisation doit être > 0
ALTER TABLE cotisation
ADD CONSTRAINT cotisation_valide CHECK (montant > 0);
-- 10) Un adhérent ne peut plus emprunter de livre tant qu'il a du retard

-- 11) La date de l'emprunt doit correspondre au jour de l'emprunt. 
ALTER TABLE emprunt
ADD CONSTRAINT check_emprunt CHECK (date_emprunt = date_emprunt);