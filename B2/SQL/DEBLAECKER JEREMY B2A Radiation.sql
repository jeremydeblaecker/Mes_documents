-- La bibliothèque Biblynov souhaite pouvoir radier les adhérents qui ont emprunté des livres depuis plus d'un ans.
-- Lors de la radiation, ces derniers seront redevables d'une somme forfaitaire de 20€ par livre non rendu depuis un an.
-- Cette somme est susceptible d'évoluer dans le temps.
-- Dès lors qu'un adhérent est radié, on doit pouvoir connaître sa date de radiation, mais aussi passer les livres qu'il n'a pas rendu en perte.
-- Vous créerez une procédure qui parcours tous les emprunts gérer les radiations, les pertes, les sommes dues.

-- Cette procédure sera appelée tous les soirs à minuit.
-- Vous créerez toutes les tables qui vous paraissent nécessaires à ces nouvelles règles de gestion.
-- Enfin, vous modifierez vos développements précédents, notamment celles concernant la disponibilité des livres, pour prendre en compte les pertes enregistrées.

DROP TABLE IF EXISTS radiation;
DROP TABLE IF EXISTS radiation_livre;
DROP PROCEDURE IF EXISTS radiation_adherent;

CREATE TABLE radiation(
	id_adherent INT PRIMARY KEY, 
	date_radiation DATE, 
	somme_forfaitaire INT
);

CREATE TABLE radiation_livre(
	id_adherent INT,
	id_livre INT
);

CREATE EVENT journalier
  ON SCHEDULE
    EVERY 1 DAY
    STARTS '2020-02-02 00:00:00' ON COMPLETION PRESERVE ENABLE
  DO
DELIMITER $$
CREATE PROCEDURE radiation_adherents()

BEGIN
	DECLARE DateActuel DATE;
    DECLARE LivreRadie INT DEFAULT 0;
	DECLARE adherentActuel INT;
    DECLARE countLivre INT;
	DECLARE RadiationDate DATE;
	DECLARE DateNOW DATE DEFAULT DATE(now());


    DECLARE x INT DEFAULT 0;
    DECLARE n INT DEFAULT 0; 	-- N = le nombre total de client a radier cad ceux dont la date d'emprunt dépace un an.
	DECLARE y INT DEFAULT 0;

	SELECT COUNT (*) FROM (SELECT id_adherent FROM emprunt WHERE date_retour IS NULL AND TIMESTAMPDIFF(year, date_emprunt, now()) >= 1 GROUP BY id_adherent) as rqt INTO n;

	SET x = 0;
	WHILE x < n DO
		SELECT id_adherent FROM emprunt WHERE date_retour IS NULL AND TIMESTAMPDIFF(year, date_emprunt, now()) >= 1 GROUP BY id_adherent LIMIT x, 1
		INTO adherentActuel; --On recupère l'id de l'adherent
		SELECT count(*) FROM emprunt WHERE date_retour IS NULL AND TIMESTAMPDIFF(year, date_emprunt, now()) >= 1 AND id_adherent = adherentActuel
		INTO countLivre; --On recupère le nombre de livre de l'adherent non rendu
		SET k = 0;
		WHILE k < countLivre DO
			SELECT date_emprunt FROM emprunt WHERE date_retour IS NULL AND TIMESTAMPDIFF(year, date_emprunt, now()) >= 1 AND id_adherent = adherentActuel LIMIT k, 1
			INTO DateActuel;
			IF DATEDIFF(DateActuel, DateNOW) < 0 THEN SET DateNOW = DateActuel; 		-- On définit la date actuel comme étant la date de radiation du livre
			END IF;
            SET k = k+1;
		END WHILE;
		SELECT DATE_ADD(DateNOW, INTERVAL 1 YEAR) INTO RadiationDate;		-- On définit la date de radiation de l'adherent
		SET y = 0;
		WHILE y < countLivre DO
			SELECT id_livre FROM emprunt WHERE date_retour IS NULL AND TIMESTAMPDIFF(year, date_emprunt, now()) >= 1 AND id_adherent = adherentActuel LIMIT y, 1
			INTO LivreRadie;
			-- On ajoute tous les livre que l'adherent n'a pas rendu qui n'ont pas encore été ajouter a la table
			IF (SELECT EXISTS(SELECT id_adherent FROM radiation_livre WHERE id_adherent = adherentActuel AND id_livre = LivreRadie)) = 0 
            THEN INSERT INTO radiation_livre VALUES (adherentActuel, LivreRadie);
			IF (SELECT EXISTS(SELECT id_adherent FROM radiation WHERE id_adherent = adherentActuel)) = 1 THEN
					UPDATE radiation SET date_radiation = RadiationDate, somme_forfaitaire = (somme_forfaitaire + 20) WHERE id_adherent = adherentActuel;
				ELSE INSERT INTO radiation VALUES (adherentActuel, RadiationDate, 20);
				END IF;
			END IF;
            SET k = k+1;
		END WHILE;
        SET i = i+1;
	END WHILE;
END $$

DELIMITER ;