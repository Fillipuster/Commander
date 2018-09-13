package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CMD {
	
	public static String execute(String...cmds) {
		String[] args = new String[cmds.length + 2];
		args[0] = "cmd.exe";
		args[1] = "/c";
		
		for (int i = 0; i < cmds.length; i++) {
			args[i+2] = cmds[i];
		}
		
		ProcessBuilder builder = new ProcessBuilder(args);
		
		return exec(builder);
	}
	
	private static String exec(ProcessBuilder builder) {
		StringBuilder sb = new StringBuilder();
		
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
