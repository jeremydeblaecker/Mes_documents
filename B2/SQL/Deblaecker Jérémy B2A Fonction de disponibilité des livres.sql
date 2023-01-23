--Fonction de disponibilité des livres
--Créer une fonction
 --livre_dispo
--qui renvoi un booléen indiquant si un livre dont l'id est donné en paramètre est disponible ou non.

delimiter $$
create function livre_dispo(id INT) returns BOOLEAN 
begin
declare dispo boolean;
case when true then set dispo= True ;
    when false then set dispo= False;
end case;
return dispo;

end $$
delimiter ;

select livre_dispo(1), date("2017-07-20");