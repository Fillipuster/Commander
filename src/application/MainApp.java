package application;

public class MainApp {

	public static void main(String[] args) {
		System.out.println(CMD.execSingle("echo cmd_test1"));
		System.out.println("ARRAY TEST:");
		System.out.println(CMD.execArray(new String[]{"echo cmd_test2"}));
	}

}
