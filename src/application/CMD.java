package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CMD {
	
	public static String execEverything(String keyword) {
		StringBuilder sb = new StringBuilder();
		
		ProcessBuilder builder = new ProcessBuilder("es.exe", keyword);
		
		builder.redirectErrorStream(true);
		
		Process p;
		try {
			p = builder.start();
		} catch (IOException e) {
			e.printStackTrace();
			return "Java_Exception: " + e.getMessage();
		}
		
		BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line;
		while (true) {
			try {
				line = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
				return "Java_Exception: " + e.getMessage();
			}
			
			if (line == null) {break;}
			
			sb.append(line);
			sb.append("\n");
		}
		
		return sb.toString();
	}
	
	public static String execSingle(String cmd) {
		return exec(cmd);
	}
	
	public static String execArray(String[] cmds) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < cmds.length; i++) {
			sb.append(cmds[i]);
			if (i != cmds.length - 1) {
				sb.append(" && ");
			}
		}
		
		return exec(sb.toString());
	}
	
	private static String exec(String cmd) {
		StringBuilder sb = new StringBuilder();
		
		ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", cmd);
		
		// Makes it so cmd errors are outputted by the InputStream as well as regular output;
		builder.redirectErrorStream(true);
		
		Process p;
		try {
			p = builder.start();
		} catch (IOException e) {
			e.printStackTrace();
			return "Java_Exception: " + e.getMessage();
		}
		
		BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line;
		while (true) {
			
			try {
				line = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
				return "Java_Exception: " + e.getMessage();
			}
			
			// Stop reading once there's no more to read;
			if (line == null) {break;}
			
			sb.append(line);
			sb.append("\n");
		}
		
		return sb.toString();
	}
	
}
