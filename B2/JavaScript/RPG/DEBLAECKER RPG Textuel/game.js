const text = document.getElementById('text')
const options = document.getElementById('option-bouttons')

    let hero = class hero{
    	constructor (prenom, nom){
            this.premom = prenom;
    		this.nom= nom;
    		this.age = 6;
    		this.pointdevie = 100;
    		this.pointderesistance = 150;
    		this.force = 50; 
    		this.xp =0;
            }	

            get statusHero(){
                return this.satus();
            }
            status (){
                return pointdevie;
                console.log(rg);        
            }
    }

    let ennemi = class ennemi{
    	constructor (type){
    		this.type = type;
    		this.pointdevie;
    		this.pointderesistance;
    		this.force;
    	}
    }
let etat = {}

function startGame() {
    choixMultiple(1)
}

function choixMultiple(IndexChoix) {
    const Multiple = leschoix.find(Multiple => Multiple.id === IndexChoix)
    text.innerText = Multiple.text
    while (options.firstChild) {
        options.removeChild(options.firstChild)
    }

    Multiple.options.forEach(option => {
        if (montrerChoix(option)) {
            const button = document.createElement('button')
            button.innerText = option.text
            button.classList.add('bouton')
            button.addEventListener('click', () => selectionnerChoix(option))
            options.appendChild(button)
        }
    })
}

//La fonction qui nous permet d'afficher les choix pour chaque étapes
function montrerChoix(option) {
    return option.requiredState == null || option.requiredState(etat)
}

//Fonction de choix 
function selectionnerChoix(option) {
    const nextMultipleId = option.nextText
    if (nextMultipleId <= 0) {
        return startGame()
    }
    etat = Object.assign(etat, option.setState)
    choixMultiple(nextMultipleId)
}

const leschoix = [{
        id: 1,
        text: 'Etes vous un HOMME ou une FEMME?',
        options: [{
                text: 'Homme',
                nextText: 2
            },
            {
                text: 'Femme',
                nextText: 2
            }

        ]
    },
    {
        id: 2,
        text: 'Il était une fois un héros qui habité un royaume enchanté. Cependant notre héros était pauvre, il ne pouvait donc malheureusement pas payé les médicaments de sa mère malade. Un beau jour notre héros reçu une missive de l\'empereur : \n "Nous recherchons des volontaires pour éliminer le mal qui sévi dans notre forêt . Des terribles mages noirs terrorisent les villageois en détruisant leurs champs, kidnapper des enfants et ressusciter des morts. Une récompense de 50000000 couronnes sera offerte à celui qui me rapportera la tête du chef  : le perfide Olrik."',
        options: [{
                text: 'Hors de question vous n\'êtes pas un héros!',
                nextText: 3
            },
            {
                text: 'C\'est votre destin vous prenez votre courage à deux mains et partez à l\'aventure.',
                nextText: 4
            }
        ]
    },
    {
        id: 3,
        text: 'Vous retournez vous couchez dans votre couette bien douillette et vous endormez paisiblement. Deux jours plus tard les mages noirs pulvérise votre maison avec une boule de feu, vous entendez votre mère hurler de douleur mais il est trop tard pour faire quoi que ce soit, vous brulez également.Vous êtes mort.',
        options: [{
            text: 'Recommencer',
            nextText: -1
        }, ]
    },
    {
        id: 4,
        text: 'Vous remplissez tous de suite votre sacoche avec de la nourriture, votre manuel des sortilèges magiques élémentaires (+5 force +3 points de vie). Vous devez maintenant choisir une arme, un arc (+10 force +3points de vie) ou une épée (+10 force +3 points de résistance).',
        options: [{
                text: 'Vous prenez l\'arc',
                nextText: 5
            },
            {
                text: 'Vous prenez l\'épée',
                nextText: 5
            }
        ]
    },
    {
        id: 5,
        text: 'Vous vous équipez également de l\'armure de cuire que vous avez offert votre tante (+10 points de résistance).Vous prenez votre mère dans vos bras et la déposé chez votre tante pour qu\'elle s\'en occupe.Vous traversez le village en direction de l\'ouest, vous remarquez un chien apeuré dans une ruelle.',
        options: [{
                text: 'Vous lui donnez de la nourriture.',
                nextText: 6
            },
            {
                text: 'Vous continuez votre chemin.',
                nextText: 7
            },
            {
                text: 'Vous abréger les souffrances de ce chien errant.',
                nextText: 8
            }
        ]
    },
    {
        id: 6,
        text: 'Vous sortez un morceau de pain et vous approchez doucement. Le chien avale avec joie le morceau de pain de pain que vous lui avez offert. Vous repartez vers l\'ouest accompagnez de votre nouveau chien (+10 points de dégats). Votre gentillesse vous fait gagner 25xp. Vous arrivez finalement à la fin du village, vous avez deux solutions pour accéder à la foret des mages noirs. Soit vous passer par les mines de la Moria, courte mais dangeureuse ou vous pouvez passer par les montagnes enneigés, c\'est un chemin plus long mais moins risqué. ',
        options: [{
            text: 'Prendre la route des mines.',
            nextText: 14
        },
        {
            text: 'Prendre la route de la montagne.',
            nextText: 15
        }]
    },
    {
        id: 7,
        text: 'Vous repartez en direction de l\'ouest comme si vous n\'aviez pas remarqué ce pauvre petit chien abandonné. On va espérer qu\'une âme plus charitable viendra le sauver, sinon la SPA va euthanasier.Vous arrivez finalement à la fin du village, vous avez deux solutions pour accéder à la foret des mages noirs. Soit vous passer par les mines de la Moria, courte mais dangeureuse ou vous pouvez passer par les montagnes enneigés, c\'est un chemin plus long mais moins risqué. ',
        options: [{
            text: 'Prendre la route des mines.',
            nextText: 14
        },
        {
            text: 'Prendre la route de la montagne.',
            nextText: 15
        }]
    },
    {
        id: 8,
        text: 'Vous vous diriger vers le chien, déterminé vous lui brisez la nuque, il ne souffrira plus jamais. Un policier vous interpèle il vous a vu tuer le chien. \n-Monsieur, vous êtes en état arrestation pour le meurtre de ce chien !',
        options: [{
                text: 'Vous n\'avez pas le temps de gérer ce policier : vous vous enfuyez.',
                nextText: 10
            },
            {
                text: 'Vous vous rendez espérant la clémence du juge',
                nextText: 11
            },
            {
                text: 'Vous combattez le garde. Après tous c\'est le seul témoin',
                nextText: 12
            },
            {
                text: 'Je vous jure que c\'est pas moi Monsieur l\'agent :)',
                nextText: 13
            }
        ]
    },
    {
        id: 10,
        text: 'Pas encore écrit',
        options: [{
            text: 'Pas encore écrit',
            nextText: 10
        }]
    },
    {
        id: 11,
        text: 'Le garde vous ammène directement au tribunal. Malheureusement pour vous le chien était celui du juge.Il c\'était enfui une semaine plus tôt. Le juge fond en larme et vous condamne à mort pour le meurtre de \'Croquette\' son petit Shih-tzu.',
        options: [{
            text: 'Recommencé',
            nextText: -1
        }]
    },
    {
        id: 12,
        text: 'Pas encore écrit',
        options: [{
            text: 'Pas encore écrit',
            nextText: 12
        }]
    },
    {
        id: 13,
        text: 'Le garde vous croit et vous laisse partir. \n-Bonne journée Monsieur!',
        options: [{
            text: 'Prendre la route des mines.',
            nextText: 14
        },
        {
            text: 'Prendre la route de la montagne.',
            nextText: 15
        }]
    },
]

startGame()