var memory_array = ['1','1','2','2','3','3','4','4','5','5','6','6','7','7','8','8']; //Défini les valeurs à trouver
var memory_values = [];
var memory_tile_ids = [];
var tiles_flipped = 0;
Array.prototype.melange_memoire = function(){ //Permet d'avoir un jeu aléatoire à chaque nouvel partie
    var i = this.length, j, temp;
    while(--i > 0){
        j = Math.floor(Math.random() * (i+1));
        temp = this[j];
        this[j] = this[i];
        this[i] = temp;
    }
}
function newBoard(){ //Permet de créer un nouveau plateau
	tiles_flipped = 0;
	var output = '';
    memory_array.melange_memoire(); 	//Appel la fonction qui permet de mélanger les cartes
	for(var i = 0; i < memory_array.length; i++){
		output += '<div id="tile_'+i+'" onclick="memoryFlipTile(this,\''+memory_array[i]+'\')"></div>';
	}
	document.getElementById('plateau').innerHTML = output;
}
function memoryFlipTile(tile,val){ //Retourne la carte quand on clique dessus
	if(tile.innerHTML == "" && memory_values.length < 2){
		tile.style.background = '#FFF'; //Quand on clique sur case son fond devient blanc
		tile.innerHTML = val;
		if(memory_values.length == 0){
			memory_values.push(val);
			memory_tile_ids.push(tile.id);
		} else if(memory_values.length == 1){
			memory_values.push(val);
			memory_tile_ids.push(tile.id);
			if(memory_values[0] == memory_values[1]){
				tiles_flipped += 2;
				memory_values = [];
            	memory_tile_ids = [];
				if(tiles_flipped == memory_array.length){
					alert("Le tableau est complet... Création du nouveau tableau"); //Quand la partie est fini 
					document.getElementById('plateau').innerHTML = "";
					newBoard(); //On créé un nouveau plateau
				}
			} else {
				function flip2Back(){
				    var tile_1 = document.getElementById(memory_tile_ids[0]);
				    var tile_2 = document.getElementById(memory_tile_ids[1]);
				    tile_1.style.background =  ''; //Permet au fond de redevenir gris
            	    tile_1.innerHTML = "";
				    tile_2.style.background = '';
            	    tile_2.innerHTML = "";
				    memory_values = [];
            	    memory_tile_ids = [];
				}
				setTimeout(flip2Back, 700);
			}
		}
	}
}
newBoard();