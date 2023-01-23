-- DEBLAECKER JEREMY B2A
--Créer une fonction qui renvoi le type de cotisation pour un date de naissance donnée en paramètre.
--Le type de cotisation sera calculé dynamiquement en fonction de la date courante et de la date en paramètre. 
--Les types de cotisations sont les suivants :
-- - enfant : si l'adhérent a moins de 18 ans,
-- - etudiant : si l'adhérent a entre 18 ans et 25 ans,
-- - adulte : si l'adhérent a entre 25 ans et 65 ans,
-- - senior : si l'adhérent a plus de 65 ans.
-- Le nom de la fonction sera fct_type_cotisation.

DELIMITER $$
CREATE FUNCTION fct_type_cotisation (dateNaissance date) RETURNS VARCHAR(10)
BEGIN
DECLARE cotisation VARCHAR(10);

case when timestampdiff(year, dateNaissance , now()) < 18 then SET cotisation='enfant';
 when timestampdiff(year, dateNaissance , now()) between 18 and 25 then SET cotisation='etudiant';
 when timestampdiff(year, dateNaissance , now()) between 25 and 65 then SET cotisation='adulte';
 when timestampdiff(year, dateNaissance , now()) > 65 then SET cotisation='senior';
end case;

RETURN cotisation;
END $$
DELIMITER ;


select fct_type_cotisation("2000-01-01");