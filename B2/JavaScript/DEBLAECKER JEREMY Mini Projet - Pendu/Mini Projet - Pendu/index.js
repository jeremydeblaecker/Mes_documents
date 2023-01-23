window.onload = function() {

    let alphabet = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
        'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
        't', 'u', 'v', 'w', 'x', 'y', 'z', '1', '2', '3', '4', 
        '5', '6', '7', '8', '9', '0'
    ]; //Clavier

    let mot; // Ensembles des mots
    let motselec; // Mot séléctionné
    let word; // Selectionne le mot
    let devine; 
    let trouvee = []; //Lettres enregistrées
    let vies=10; // Nbr de vies
    let couprates=0; //Compte le nbr de coups ratés en commancant à 0
    let counter=0; // Compte les reponses correctes en commancant à 0

    // Affiche les vies restantes
    let affichevies = document.getElementById("mesVies");

    // Créer le clavier
    bouttons = function() {
        mybouttons = document.getElementById('bouttons'); //On affiche les boutons
        lettres = document.createElement('ul'); //On créer une liste de lettres

        for (let i = 0; i < alphabet.length; i++) {
            lettres.id = 'alphabet'; 
            list = document.createElement('li');
            list.id = 'letter';
            list.innerHTML = alphabet[i];
            check();
            mybouttons.appendChild(lettres);
            lettres.appendChild(list);
        }
    }

    result = function() {
        souvenirmot = document.getElementById('memoire'); 
        vrai = document.createElement('ul');

        for (let i = 0; i < word.length; i++) {
            vrai.setAttribute('id', 'my-word');
            devine = document.createElement('li'); //On créer la ligne du mot à trouvé
            devine.setAttribute('class', 'devine');            
            devine.innerHTML = "_";//Remplace les lettres du mot par des _
            trouvee.push(devine);
            souvenirmot.appendChild(vrai);
            vrai.appendChild(devine);
        }
    }

    // Compteur de vie
    resultat = function() { 
        affichevies.innerHTML = "Vous avez " + vies + " vies"; //On affiche le nbr de vie qu'il nous reste
        if (vies < 1) { //Si on a plus de vie
            affichevies.innerHTML = "Perdu! La bonne reponse etait: " + word; //Alors on affiche qu'on a perdu
        }
        else if (counter === trouvee.length){ //Sinon si le nbr de bonne réponse est égal à la longeur du mot alors
             {
                affichevies.innerHTML = "Victoire!"; //On a gagné
            }
        }
    }


    //Fonction Onclick
    check = function() {
        list.onclick = function() {
            let devine = this.innerHTML;
            this.setAttribute("class", "active"); //Quand on a déjà cliqué sur une lettre on ne perd pas de point supplémentaire en cliquant dessus
            this.onclick = null;
            for (let i = 0; i < word.length; i++) {
                if (word[i] === devine) {
                    trouvee[i].innerHTML = devine;
                    counter += 1;
                }
            }
            let c = (word.indexOf(devine));
            if (c === -1) {
                vies -= 1; //On supprime une vie à chaque erreur
                couprates++; 
                document.images['pendu'].src="images/pendu_"+couprates+".jpg"; //On change l'image du pendu à chaque coup ratés
                resultat();
            } else {
                resultat();
            }
        }
    }

    play = function() {
        mot = [
            ["css", "xml", "wei", "wed", "bde", "nas", "mur", "php","fiat","html","gift","code",
            "cuirs","redis","gnome","belin","drone","match","forge","orage","esnext","avions",
            "cactus","cuisez","cahier","banane","tamaris","mirage","prisme","royaux","titube","eviter",
            "basics","nifles","gigues","peuple","citroen","riviere","sacques","paroles","orleans","absout",
            "evapore","devouez","snifeur","domaine","memoire","endroit","gnocchi","peugeot","mercedes","quietude",
            "serveuse","jacquart","audition","mouffant","obstruez","kawasaki","tricoteur","calibrant","accession","exigeants",
            "moustache","implosion","voisinage", "encostumee","trolleuses", "briocherie", "javascript", "charbonnez", "es1"]
        ]; //Listes de mots
        motselec = mot[Math.floor(Math.random() * mot.length)]; //On choisi un mot random parmi la liste
        word = motselec[Math.floor(Math.random() * motselec.length)];
        console.log(word);
        bouttons(); //On affiche le clavier
        result(); //Affiche les lettres trouvé 
        resultat(); //Affiche le nbr de vies restantes et le résultat à la fin de la partie
    }

    play();
    
}