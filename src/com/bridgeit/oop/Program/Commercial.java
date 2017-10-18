package com.bridgeit.oop.Program;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import com.bridgeit.oop.Utility.StockAccount;

public class Commercial {

	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		StockAccount stockAccount = new StockAccount();
		int choice;
		do {
			System.out.println("1.Add User\n2.Buy Share\n3.Sell Share\n4.Display Report\n5.Exit\nEnter Your Choice : ");
			choice = scanner.nextInt();
			switch (choice) {
			case 1:
				System.out.println("Enter User Name : ");
				String name = scanner.next();
				new StockAccount(name);
				break;
			case 2:
				System.out.println("Enter Amount and Share Symbol.  e.g '@' '#' '$'");
				int amount = scanner.nextInt();
				String symbol=scanner.next();
				stockAccount.buy(amount, symbol);
				break;
			case 3:
				break;

			case 4:
				stockAccount.printReport();
				break;
			case 5:
				System.exit(0);
			}
		} while (choice <= 5);
	}
}
