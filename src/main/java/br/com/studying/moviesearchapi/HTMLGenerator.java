package br.com.studying.moviesearchapi;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import br.com.studying.moviesearchapi.domain.movie.Movie;

public class HTMLGenerator {
	
	public void generate(List<Movie> movieList) {
		
		
		
		PrintWriter fileOut; //HTML file connection
		Scanner fileIn; //input file connection
		String fileNameIn; //Original file's name
		String fileNameOut; // new HTML file1s name
		int dotIndex; //position of . in filename
		String line = null; // a line from the input file
		String filePath = "D:\\workdpsces\\eclipse-workspace\\moviesearchapi\\MoviePage";	
		
		this.fileEdit(movieList, filePath);
		
		fileNameIn = "MoviePage";
		
		//2. check if file exists
				try {
					//3. rename .txt as .html
					fileIn = new Scanner(new FileReader(fileNameIn));
					dotIndex = fileNameIn.lastIndexOf(".");
					if (dotIndex == -1) {
						fileNameOut = fileNameIn + ".html";
					}else {
						fileNameOut = fileNameIn.substring(0, dotIndex) + ".html";
					}
					fileOut = new PrintWriter(fileNameOut);
					
					//4. determine if file is empty
					
					try {
						line = fileIn.next();
					} catch (NoSuchElementException e) {
						System.out.println("Error: " + e.getMessage());
					}
					if (line==null) {
						System.out.println("This file is empty :(");
					}else {
						//5. read each line and insert necessary <tags>
						fileOut.println("<html>");
						fileOut.println("<head>");
						fileOut.println("</head>");
						fileOut.println("<body>");
						fileOut.println(line);
						
						while (fileIn.hasNextLine()) {
							
							fileOut.println("<br>");
							line = fileIn.nextLine();
							
							if (line.isEmpty()) {
								fileOut.println("<br>");
							}else {
								fileOut.println(line);
							}
							
						}
						fileOut.println("</body>");
						fileOut.println("</html>");
						System.out.println("HTML file is processed: :)");
					}
					fileIn.close();
					fileOut.close();
				} catch (FileNotFoundException e) {
					System.out.println("File not found");
				}
		
	}
	
	private void fileEdit(List<Movie> movieList, String fileNameIn) {
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(fileNameIn))){
			
			for(Movie movie: movieList) {
				bw.append(" ");
				bw.newLine();
				bw.append("Title: " + movie.getTitle() + " - ");
				bw.append("Release date: " + new SimpleDateFormat("dd-MM-yyyy").format(movie.getReleaseDate()) + " - ");
				bw.append("Rating: " + movie.getRating());
				bw.newLine();
			}
			
		} catch (IOException e) {
			System.out.println("Erro: " + e.getMessage());
		}
		
	}

}
