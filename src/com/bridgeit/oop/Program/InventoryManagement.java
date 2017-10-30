package com.bridgeit.oop.Program;

import java.util.Scanner;

import com.bridgeit.oop.Utility.Utility;

public class InventoryManagement {

	static {
		System.out.println("hello java");
	}

	public static void main(String[] args) throws Exception {
		Utility utility = new Utility();

		while (true) {
			System.out.println(
					"Welcome To Inventory Management System..\n1.Add Inventory\n2.Calculate Each Inventory\n3.Inventory Report\n4.Exit");
			System.out.println("Enter Your Choice...");
			int choice = new Scanner(System.in).nextInt();
			switch (choice) {
			case 1:
				utility.addInventory();
				break;
			case 2:
				utility.totalInventory();
				break;
			case 3:
				utility.inventoryReport();
				break;
			case 4:
				System.exit(0);
				break;

			}
		}

	}

}
