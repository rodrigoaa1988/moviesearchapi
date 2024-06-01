package br.com.studying.moviesearchapi.service;

public class ParseService {

	public String parseAllMovies(String json) {

		char ini = '[';
		char fin = ']';

		int positionIni = json.indexOf(ini);
		int positionFin = json.lastIndexOf(fin) + 1;

		String jsonMovies = json.substring(positionIni, positionFin);

		return jsonMovies;
	}

	public String[] parseJsonMovies(String jsonMovies) {

		jsonMovies = jsonMovies.replace("[", "");
		jsonMovies = jsonMovies.replace("]", "");
		jsonMovies = jsonMovies.replace("},", "};");
		String[] moviesArray = jsonMovies.split(";");

		return moviesArray;
	}

	public String[] parseAtributes(String[] moviesArray, String atribute) {

		String[] titles = new String[moviesArray.length];

		int caracteres = 0;

		for (int x = 0; x < atribute.length(); x++) {
			int teste = atribute.indexOf(x);
			caracteres++;
		}
		caracteres++;

		int cont = 0;
		for (String movie : moviesArray) {
			int indexOfTitle = movie.indexOf(atribute) + caracteres;
			String movieTitle = movie.substring(indexOfTitle);
			int fin = movieTitle.indexOf("\",");
			movieTitle = movieTitle.substring(0, fin);
			titles[cont] = movieTitle;
			cont++;
		}
		return titles;
	}

	public double[] parseRating(String[] moviesArray, String atribute) {

		double[] titles = new double[moviesArray.length];

		int caracteres = 0;

		for (int x = 0; x < atribute.length(); x++) {
			int teste = atribute.indexOf(x);
			caracteres++;
		}

		int cont = 0;
		for (String movie : moviesArray) {
			int indexOfTitle = movie.indexOf(atribute) + caracteres;
			String movieTitle = movie.substring(indexOfTitle);
			int fin = movieTitle.indexOf(",");
			movieTitle = movieTitle.substring(0, fin);
			titles[cont] = Double.parseDouble(movieTitle);
			cont++;
		}

		return titles;
	}

}
