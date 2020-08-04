package com.leonel.films.data;
import java.util.List;

import com.leonel.films.domain.*;
import com.leonel.films.exceptions.*;

public interface DataAccessInterface {
	//Contiene las operaciones a ejecutar en el archivo peliculas.txt
	public boolean exist(String fileName) throws DataAccessException;
	
	public List<Film> list(String name) throws DataReadException;
	
	public void write(Film film, String fileName,boolean add) throws DataWriteException;
	
	public String search(String fileName, String search) throws DataReadException;
	
	public void create(String fileName) throws DataAccessException;
	
	public void delete(String fileName) throws DataAccessException;
}
