package mainApp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LevelLoader {
	public char[][] loadLevel(String filePath) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		int rows = 0;
		int cols = 0;

		// Determine the dimensions of the level
		String line;
		while ((line = reader.readLine()) != null) {
			rows++;
			cols = Math.max(cols, line.length());
		}

		// Create a 2D array to store the level layout
		char[][] level = new char[rows][cols];
		reader.close();

		// Read the file again to fill in the array
		reader = new BufferedReader(new FileReader(filePath));
		for (int i = 0; i < rows; i++) {
			line = reader.readLine();
			for (int j = 0; j < line.length(); j++) {
				level[i][j] = line.charAt(j);
			}
		}
		reader.close();

		return level;
	}
}
