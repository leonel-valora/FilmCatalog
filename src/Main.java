import java.util.Scanner;

import com.leonel.films.business.FilmsCatalog;
import com.leonel.films.business.FilmsCatalogInterface;
public class Main {
	//Contiene el menu que permite al usuario seleccionar la accion a ejecutar sobre el catalogo de peliculas
	public static void main(String[] args) {
		final Scanner scanner = new Scanner(System.in);
		int option;
		String fileName = "peliculas.txt";
		FilmsCatalogInterface filmsCatalog = new FilmsCatalog();
		String messageMenu ="1.- Iniciar catalogo de peliculas\n"
				+ "2.- Agregar peliculas\n"
				+ "3.- Listar peliculas\n"
				+ "4.- Buscar Pelicula\n"
				+ "0.- Salir";
		System.out.println(messageMenu);
		System.out.print("~ ");
		option = Integer.parseInt(scanner.nextLine());
		while(option != 0) {
			switch(option) {
				case 1:
					filmsCatalog.startFile(fileName);
					break;
				case 2:
					System.out.println("Ingresa el titulo de la pelicula");
					System.out.print("~ ");
					String filmName = scanner.nextLine();
					filmsCatalog.addFilm(filmName, fileName);
					break;
				case 3:
					filmsCatalog.listFilms(fileName);
					break;
				case 4:
					System.out.println("¿Que pelicula buscas?");
					System.out.print("~ ");
					String search = scanner.nextLine();
					filmsCatalog.searchFilm(fileName, search);
					break;
				default:
					System.out.println("No se introducio una opcion correcta, intenta de nuevo :D");
			}
			System.out.println();
			System.out.println(messageMenu);
			System.out.print("~ ");
			option = Integer.parseInt(scanner.nextLine());
		}
		System.out.println("Has salido de la aplicacion!");
	}
}
