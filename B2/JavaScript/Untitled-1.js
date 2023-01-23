class Voiture{
	constructor(marque, modele, annee){
		this.marque = marque;
		this.modele= modele;
		this.annee= annee;
		this.couleur;
		this.motorisation;
		this.options;
		this.carburant;
		this.prix;
		this.boiteDeVitesse;
		}

		set color(couleur){
			this.couleur = couleur;
		}

		set motor(motorisation){
			this.motorisation= motorisation;
		}

		set fuel(carburant){
			this.carburant= carburant;
		}

		set price(prix){
			this.prix= prix;
		}

		description(){
			return "Marque du véhicule" $(this.marque)
			return "Modèle du véhicule" $(this.modele)
			return "Année du véhicule" $(this.annee)
			return "Couleur du véhicule" $(this.couleur)
			return "Motorisation du véhicule" $(this.motorisation)
			return "Options du véhicule" $(this.options)
			return "Carburant du véhicule" $(this.carburant)
			return "Prixdu véhicule" $(this.prix)
			return "Boite de vitesse du véhicule" $(this.boiteDeVitesse)
			};
				}
            }
    const TESLA_S = new Voiture('Tesla', 'S', 2018);
    const TESLA_X = new Voiture('Tesla', 'X', 1997);
    const MERCEDES= new Voiture('Mercedes', 'Class A', 2019);

    TESLA_S.color= 'Rouge';
    TESLA_X.color= 'Jaune';
    MERCEDES.color= 'Noir';

    TESLA_S.motor= 'electrique';
    TESLA_X.motor= 'hybride';
    MERCEDES.motor= 'essence';

    TESLA_S.fuel='E10';
    TESLA_X.fuel='diesel';
    MERCEDES.fuel='essence';

    TESLA_S.price='100000';
    TESLA_X.price='200000';
    MERCEDES.price='300000';


    console.log(TESLA_S.description());
    console.log(TESLA_X.description());
    console.log(MERCEDES.description());