package com.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MonApplication {

	public static void main(String[] args) {
		// je vais charger context spring en utilisant le fichier xml de configuration
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		// récupérer un Musicien Violoniste depuis le contexte
		Musicien musicien = context.getBean("monMusicien", Musicien.class);

		// je veux exécuter une méthode de ce musicien
		System.out.println(musicien.joueTaPartition());

		// utiliser la dépendance
		System.out.println(musicien.prepa());

		// fermer le contexte
		context.close();

	}

}
