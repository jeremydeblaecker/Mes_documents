/*Exercice 1*/
console.log("Devinette");
 
var solution = Math.floor(Math.random() * 10) + 0;
 
//console.log("(La solution est " + solution + ")");
 
var nombre = Number(prompt("Entrez un nombre :"));

while(nombre !== solution)
{
  if (nombre > solution)
  {
    console.log(nombre + " est trop grand");
    nombre = Number(prompt("Choisissez un nombre plus petit que " + nombre));

  }
 
  else if (nombre < solution)
  {
    console.log(nombre + " est trop petit");
    nombre = Number(prompt("Choisissez un nombre plus grand que " + nombre));

  }
}

if(nombre == solution)
  console.log("Bravo !! La solution est : " + solution);