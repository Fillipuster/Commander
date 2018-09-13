package application;

import java.io.File;
import java.util.ArrayList;

public class EverythingSearcher {

	private ArrayList<String> result = new ArrayList<>();

	public EverythingSearcher() {
		boolean esExists = new File("es.exe").exists();
		if (!esExists)
			throw new RuntimeException("Missing EverythingSearch (es) executable.\nPlease add the command-line interface version of EverythingSearch to the working directory.\nEverythingSearch can be found here: https://www.voidtools.com/downloads/");
	}
	
	/**
	 * Performs an Everything search for keyword and overwrites the current result with the result of this search.
	 * @param keyword : The string to search for.
	 */
	public void search(String keyword) {
		result = CMD.executeProcess("es.exe", keyword);
	}
	
	/**
	 * Performs an Everything search for the keyword and adds the result(s) to the current result.
	 * @param keyword : The string to search for.
	 */
	public void addSearch(String keyword) {
		result.addAll(CMD.executeProcess("es.exe", keyword));
	}

	/**
	 * Clears the result, resulting in an empty ArrayList.
	 */
	public void clearResult() {
		result = new ArrayList<String>();
	}
	
	/**
	 * Used to extract the result of whatever search(es) performed.
	 * @return The current list of results.
	 */
	public ArrayList<String> getResult() {
		return result;
	}

	/**
	 * Used to extract the result of whatever search(es) performed as a single string.
	 * @return The current results as a newline segmented string.
	 */
	public String getResultString() {
		StringBuilder sb = new StringBuilder();

		for (String s : getResult()) {
			sb.append(s);
			sb.append("\n");
		}
		
		return sb.toString();
	}

}