package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CMD {

	/**
	 * Executes and reads the output of a Windows CMD command.
	 * @param cmds : The command(s) to be executed. E.g. "echo", "test".
	 * @return The output of the command as an ArrayList of lines.
	 */
	public static ArrayList<String> execute(String... cmds) {
		ProcessBuilder builder = buildProcess(true, cmds);

		return exec(builder);
	}

	/**
	 * Executes and reads the output of a Windows CMD command.
	 * @param cmds : The command(s) to be executed. E.g. "echo", "test".
	 * @return The output of the command as a string.
	 */
	public static String executeString(String... cmds) {
		ArrayList<String> output = execute(cmds);
		StringBuilder result = new StringBuilder();

		for (String s : output) {
			result.append(s);
			result.append("\n");
		}

		return result.toString();
	}

	/**
	 * Executes and reads the output of a terminal command.
	 * @param cmds : The command(s) to be executed. E.g. "application.exe", "argument1".
	 * @return The output of the command as an ArrayList of lines.
	 */
	public static ArrayList<String> executeProcess(String... cmds) {
		ProcessBuilder builder = buildProcess(false, cmds);

		return exec(builder);
	}

	private static ProcessBuilder buildProcess(boolean cmd, String... cmds) {
		int offset = 0;

		if (cmd)
			offset = 2;

		String[] args = new String[cmds.length + offset];
		if (cmd) {
			args[0] = "cmd.exe";
			args[1] = "/c";
		}

		for (int i = 0; i < cmds.length; i++) {
			args[i + offset] = cmds[i];
		}

		return new ProcessBuilder(args);
	}

	private static ArrayList<String> exec(ProcessBuilder builder) {
		ArrayList<String> result = new ArrayList<>();

		// Makes it so cmd errors are outputted by the InputStream as well as regular output;
		builder.redirectErrorStream(true);

		Process p;
		try {
			p = builder.start();
		} catch (IOException e) {
			e.printStackTrace();
			result.add("Java_Exception: " + e.getMessage());
			return result;
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line;
		while (true) {

			try {
				line = br.readLine();
			} catch (IOException e) {
				result.add("Java_Exception: " + e.getMessage());
				return result;
			}

			// Stop reading once there's no more to read;
			if (line == null) {
				break;
			}

			result.add(line);
		}

		return result;
	}

}
