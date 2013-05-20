import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class TrafficMain {

	private static TrafficTree tree;
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		tree = new TrafficTree();
		
		if (args.length != 1) {
			System.out.println("No file specified. Please supply a CSV" 
					+ "file from a run of WireShark: " + args.length);
			return;
		}
		
		BufferedReader br = new BufferedReader(new FileReader(args[0]));
		
		String line = br.readLine();
		
		int i = 0;
		System.out.print("Analyzing");
		while ((line = br.readLine()) != null) {
			if (i % 1000 == 0) {
				System.out.print(".");
			}
			tree.addNode(line);
			i++;
		}
		br.close();
		
		System.out.println("\nIPs: " + tree.size());
		Scanner in = new Scanner(System.in);
		String userInput = "";
		while (!userInput.equals("q")) {
			System.out.print("What would you like to do? \n"
					+ "1. View Logged IP Addresses\n"
					+ "2. View Traffic From IP Address\n"
					+ "[q]uit\n"
					+ "> ");
			userInput = in.next();
			processInput(userInput);
		}
		System.out.println("Exiting...");
		in.close();
	}

	private static void processInput(String userInput) {
		switch (userInput) {
			case "1" : tree.printIPs();
						break; 
			case "2" : break;
			default : break;
		}
		
	}
}
