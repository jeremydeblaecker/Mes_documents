package com.springdemo;

public class Pianiste implements Musicien {

	private PrepareService service;

	@Override
	public String joueTaPartition() {
		return "je joue du piano debout";
	}

	@Override
	public String prepa() {
		return service.preparation();
	}

	public Pianiste(PrepareService service) {
		this.service = service;
	}

}
