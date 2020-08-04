package com.leonel.films.business;

public interface FilmsCatalogInterface {
	//Contiene las opereaciones necesarias de la aplicacion 
	public void addFilm(String filmName, String fileName);
	
	public void listFilms(String fileName);
	
	public void searchFilm(String fileName, String search);
	
	public void startFile(String fileName);
}
