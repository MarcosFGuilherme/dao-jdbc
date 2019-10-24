package application;

import java.util.Scanner;

import general.Utility;

public class UI {
	public static void clearScreen() {
		System.out.flush();
	}
	public static String menuScreen(Scanner sc) {
		String menu = ""
				+ "[S]how "
				+ "[F]ind "
				+ "[I]nsert "
				+ "[U]pdate "
				+ "[D]elete "
				+ "[E]xit "
				+ "==> ";
		System.out.println(Utility.stringFix("", 60, "_"));
		System.out.print(menu);
		String opc = sc.next();
		return  opc.substring(0,1).toUpperCase();
	}
}
