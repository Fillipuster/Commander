package application;

public class MainApp {

	public static void main(String[] args) {
		EverythingSearcher e = new EverythingSearcher();
		
		e.search("eclipse.exe");
		
		System.out.println(e.getResult());
		
		System.out.println(e.getResultString());
	}

}