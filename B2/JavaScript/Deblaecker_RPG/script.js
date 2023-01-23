let PieceActuel = "start";
let commands = ["aller", "inventaire", "chosir"];
let inventory = ["epee", "bouclier"];

//Fonction pour changer de salle
function changerPiece(dir) {
    if (pieces[PieceActuel].directions[dir] !== undefined) {
        PieceActuel = pieces[PieceActuel].directions[dir]
        $('#game-text').append("<p>" + pieces[PieceActuel].description + "</p>");
    } else {
        $('#game-text').append("<p>Vous ne pouvez pas allez par la!</p>");
    }
}

//Fonction pour afficher l'aider
function montrerAide() {
    $('#game-text').append("<p>Voici les commandes possibles : </p>");
    $('#game-text').append("<p><ul>");
    for (let i = 0; i < commands.length; i++) {
        $('#game-text').append("<li>" + commands[i] + "</li>");
    }
    $('#game-text').append("</ul></p>");

}

//Fonction qui permet d'afficher l'inventaire
function afficherInventaire() {
    if (inventory.length === 0) {
        $('#game-text').append("<p>Vous n'avez aucun matériel sur vous!</p>");
        return;
    }
    $('#game-text').append("<p>Voici votre inventaire: </p>");
    $('#game-text').append("<p><ul>");
    for (let i = 0; i < inventory.length; i++) {
        $('#game-text').append("<li>" + inventory[i] + "</li>");
    }
    $('#game-text').append("</ul></p>");

}

function playerInput(input) {
    let command = input.split(" ")[0];
    switch (command) {
        case "aller":
            let dir = input.split(" ")[1];
            changerPiece(dir);
            break;
        case "help":
            montrerAide();
            break;
        case "inventaire":
            afficherInventaire();
            break;
        default:
            $('#game-text').append("<p>Commande invalide!</p>");
    }
}

$(document).ready(function() {
    $('#game-text').append("<p>" + pieces.start.description + "</p>");

    $(document).keypress(function(key) {
        if (key.which === 13 && $('#user-input').is(':focus')) {
            var value = $('#user-input').val().toLowerCase();
            $('#user-input').val("");
            playerInput(value);
        }
    })


})
