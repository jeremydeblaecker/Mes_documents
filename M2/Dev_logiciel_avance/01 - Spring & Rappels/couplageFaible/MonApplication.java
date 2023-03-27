package com.demo;

import org.springframework.beans.factory.annotation.Autowired;

public class MonApplication {

	public static void main(String [] args ) {
	Musicien violoniste = new Violoniste();
	//use the objet
	System.out.println( violoniste.joueTaPartition());
	
	}
	
}
