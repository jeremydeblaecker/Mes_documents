package com.springdemo;

public class Violoniste implements Musicien {

	private PrepareService service;

	@Override
	public String joueTaPartition() {
		return "je joue la valse d'Amélie";
	}

	@Override
	public String prepa() {
		return service.preparation();
	}

	public Violoniste(PrepareService service) {
		this.service = service;
	}

}
