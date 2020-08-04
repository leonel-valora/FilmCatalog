package com.leonel.films.data;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.leonel.films.domain.Film;
import com.leonel.films.exceptions.DataAccessException;
import com.leonel.films.exceptions.DataReadException;
import com.leonel.films.exceptions.DataWriteException;

public class DataAccess implements DataAccessInterface {

	@Override
	public boolean exist(String fileName) {
		File file = new File(fileName);
		return file.exists();
	}

	@Override
	public List<Film> list(String fileName) throws DataReadException {
		File file = new File(fileName);
		List<Film> films = new ArrayList<>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();
			while(line != null) {
				Film film = new Film(line);
				films.add(film);
				line = reader.readLine();
			}
			reader.close();
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace(System.out);
			throw new DataReadException("No es posible listar las peliculas\n"+e.getMessage());
		} 
		catch (IOException e) {
			e.printStackTrace(System.out);
			throw new DataReadException("No es posible listar las peliculas\n"+e.getMessage());
		} 
		return films;
	}

	@Override
	public void write(Film film, String fileName, boolean add) throws DataWriteException {
		File file = new File(fileName);
		try {
			if (add) {
				PrintWriter writer = new PrintWriter(new FileWriter(file, true));
				writer.println(film.getName());
				writer.close();
				System.out.printf("Pelicula '%s' agregada!%n",film.getName());				
			}
			else {
				System.out.println("Creando archivo...");
				add = file.exists();
				System.out.println("Archivo creado!");
				this.write(film, fileName, add);
			}
		} catch (IOException e) {
			e.printStackTrace(System.out);
			throw new DataWriteException("No es posible agregar la pelicula\n"+e.getMessage());
		}
		
	}

	@Override
	public String search(String fileName, String search) throws DataReadException {
		File file = new File(fileName);
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();
			int index = 1;
			while(line != null) {
				if(line.equalsIgnoreCase(search)) {
					reader.close();
					return "Pelicula No."+index+": "+line;
				}
				line = reader.readLine();
				++index;
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace(System.out);
			throw new DataReadException("No es posible buscar la pelicula\n"+e.getMessage());
		} catch (IOException e) {
			e.printStackTrace(System.out);
			throw new DataReadException("No es posible buscar la pelicula\n"+e.getMessage());
		}
		return "No se encontro la pelicula";
	}

	@Override
	public void create(String fileName) throws DataAccessException {
		File file = new File(fileName);
		try {
			PrintWriter writer = new PrintWriter(new FileWriter(file));
			writer.close();
			System.out.println("Se ha creado el archivo de peliculas");
		} catch (IOException e) {
			e.printStackTrace(System.out);
			throw new DataWriteException("No es posible agregar la pelicula\n"+e.getMessage());
		}
	}

	@Override
	public void delete(String fileName) throws DataAccessException {
		File file = new File(fileName);
		if(file.exists()) {
			file.delete();
			System.out.println("Se ha eliminado el archivo de peliculas");
		}
		
	}

	

}
