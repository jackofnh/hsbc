package com.gao.hsbc;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter input string:");
		String input = scanner.nextLine();

		System.out.println("Choose mode (1 for removal, 2 for replacement):");
		int mode = scanner.nextInt();

		IStringService service = new StringService();

		String rs = null;
		if (mode == 1) {
			rs = service.remove(input);

		} else if (mode == 2) {
			rs = service.replace(input);
		}

		System.out.println("Processing result:"+rs);
	}
}
