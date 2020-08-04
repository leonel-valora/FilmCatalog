package com.leonel.films.business;

import java.util.List;

import com.leonel.films.data.DataAccess;
import com.leonel.films.data.DataAccessInterface;
import com.leonel.films.domain.Film;
import com.leonel.films.exceptions.DataAccessException;
import com.leonel.films.exceptions.DataReadException;
import com.leonel.films.exceptions.DataWriteException;

public class FilmsCatalog implements FilmsCatalogInterface{
	//Contiene las implementacion de las operaciones necesarias de la app
	DataAccessInterface data;
	
	public FilmsCatalog() {
		data = new DataAccess();
	}

	@Override
	public void addFilm(String filmName, String fileName) {
		Film film = new Film(filmName);
		boolean add = false;
		try {
			add = this.data.exist(fileName);
			data.write(film, fileName, add);
		}  catch (DataWriteException e) {
			System.out.print("Error al escribir los datos");
			e.printStackTrace(System.out);
		}catch (DataAccessException e) {
			e.printStackTrace(System.out);
			System.out.print("Error al acceder a los datos");
		}
	}

	@Override
	public void listFilms(String fileName) {
		try {
			List<Film> films = this.data.list(fileName);
			if(films != null) {
				System.out.println("(¯`·.¸¸.·Lista de peliculas·.¸¸.·´¯)");
				for(Film film : films) {
					System.out.println("- "+film.getName());
				}
				System.out.println("(_.·´¯`·.¸¸¸¸¸¸¸¸¸¸¸¸¸¸¸¸¸.·´¯`·.¸_)");
			}
			else {
				System.out.println("La lista de peliculas esta vacia");
			}
			
		} catch (DataReadException e) {
			System.out.print("Error al acceder a los datos");
			e.printStackTrace(System.out);
		}
		
	}

	@Override
	public void searchFilm(String fileName, String search) {
		String result = null;
		try {
			result = this.data.search(fileName, search);
		}catch(DataAccessException e) {
			System.out.print("Error al acceder a los datos");
			e.printStackTrace(System.out);
		}
		System.out.println(result);
	}

	@Override
	public void startFile(String fileName) {
		try {
			if(this.data.exist(fileName)) {
				this.data.delete(fileName);
				this.data.create(fileName);
			}
			else {
				this.data.create(fileName);
			}
			System.out.println("Archivo creado!");
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}
}
