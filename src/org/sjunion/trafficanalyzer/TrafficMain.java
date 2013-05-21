package org.sjunion.trafficanalyzer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * The main class for the traffic analyzer.
 * It merely serves to process user input
 * and do the initial analysis of the data
 * from WireShark.
 * @author cstech
 *
 */
public class TrafficMain {

	private static TrafficTree tree;
	private static Scanner in;
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		tree = new TrafficTree();
		
		if (args.length != 1) {
			System.out.println("No file specified. Please supply a CSV" 
					+ "file from a run of WireShark: " + args.length);
			return;
		}
		
		BufferedReader br = new BufferedReader(new FileReader(args[0]));
		
		String line = br.readLine();
		
		int i = 0;
		System.out.print("\nAnalyzing");
		while ((line = br.readLine()) != null) {
			if (i % 5000 == 0) {
				System.out.print(".");
			}
			tree.addNode(line);
			i++;
		}
		br.close();
		
		System.out.println("\nIPs: " + tree.size());
		in = new Scanner(System.in);
		String userInput = "";
		while (!userInput.equals("3")) {
			System.out.print("What would you like to do? \n"
					+ "1. View Logged IP Addresses\n"
					+ "2. View Traffic From A Specific IP Address\n"
					+ "3. Quit\n"
					+ "> ");
			userInput = in.next();
			processInput(userInput);
		}
		System.out.println("Exiting...");
		in.close();
	}

	/**
	 * Processes user Input from main method and requests more as
	 * needed.
	 * @param userInput
	 */
	private static void processInput(String userInput) {
		switch (userInput) {
			case "1" : tree.printIPs();
						break;
			case "2" : 	System.out.print("Input IP > ");
						String ip = in.next();
						if (!TrafficTree.isValidIP(ip)) {
							System.out.println("Invalid IP Format (Use IPv4)");
						}
						else {
							TrafficTree.TreeNode n = tree.searchTree(ip);
							if (n == null) {
								System.out.println("IP Not Found");
							}
							else {
								System.out.println("IP Found");
								System.out.println(n.toString());
							}
						}
						break;
			default : break;
		}
	}
}
