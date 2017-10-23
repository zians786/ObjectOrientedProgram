package com.bridgeit.oop.Program;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.bridgeit.oop.Utility.LinkedListUtility;
import com.bridgeit.oop.Utility.StockAccount;

public class CompanyAdd {

	public static void main(String[] args) throws Exception {

		StockAccount account = new StockAccount();
		Scanner scanner = new Scanner(System.in);
		LinkedListUtility linkCompany = new LinkedListUtility();
		FileReader fileReader1 = new FileReader(
				"/root/workspace/ObjectOrientedProgram/src/com/bridgeit/oop/Utility/company_stock_details.json");
		JSONArray jsonArray1 = (JSONArray) new JSONParser().parse(fileReader1);

		for (Object object1 : jsonArray1) {

			JSONObject jsonObject = (JSONObject) object1;
			linkCompany.insertAtEnd(jsonObject);
		}

		while (true) {
			System.out.println(
					"\n1.Add Company\n2.Remove Company\n3.Display List of Shares\n4.Write data to JSON\n4.Exit");
			System.out.println("Enter Your Choice...");
			int choice = scanner.nextInt();
			switch (choice) {
			case 1:
				account.addCompany(linkCompany);

				break;
			case 2:
				account.removeCompany(linkCompany);
				break;

			case 3:
				linkCompany.displayList();

				break;
			case 4:
				account.writeToJSON(linkCompany,
						"/root/workspace/ObjectOrientedProgram/src/com/bridgeit/oop/Utility/company_stock_details.json");
				break;
			case 5:
				System.exit(0);

			}
		}

	}

}
