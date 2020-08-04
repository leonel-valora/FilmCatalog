package com.leonel.films.domain;

public class Film {
	//Representa los objetos pelicula utilizados en la aplicacion de catalogo peliculas.
	private String name;
	
	public Film() {
		
	}
	
	public Film(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
}
