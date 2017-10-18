package com.bridgeit.oop.Utility;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class StockAccount {

	public StockAccount() {

	}

	public StockAccount(String userName) throws Exception {
		Scanner scanner = new Scanner(System.in);
		FileReader fileReader = new FileReader(
				"/root/workspace/ObjectOrientedProgram/src/com/bridgeit/oop/Utility/user_stock_details.json");
		JSONArray jsonArray = (JSONArray) new JSONParser().parse(fileReader);
		boolean status = true;
		for (Object object : jsonArray) {
			JSONObject jsonObject = (JSONObject) object;
			String name = (String) jsonObject.get("name");
			if (name.equals(userName)) {
				status = false;
			}
		}
		if (status) {
			System.out.println("Enter User Amount");
			String amount = scanner.nextLine();
			JSONObject user = new JSONObject();
			user.put("name", userName);
			user.put("amount", amount);
			JSONObject number = new JSONObject();
			number.put("@", "1");
			number.put("#", "1");
			number.put("$", "1");
			user.put("number", number);
			jsonArray.add(user);
			save(jsonArray);
			System.out.println("User With Name '" + userName + "', Added Successfully..");

		} else {
			System.out.println("User Already Exist...");
		}
	}

	public void buy(int amount, String symbol) throws IOException, ParseException {
		Scanner scanner=new Scanner(System.in);
		FileReader userFile = new FileReader(
				"/root/workspace/ObjectOrientedProgram/src/com/bridgeit/oop/Utility/user_stock_details.json");
		FileReader companyFile = new FileReader(
				"/root/workspace/ObjectOrientedProgram/src/com/bridgeit/oop/Utility/company_stock_details.json");
		JSONArray userArray = (JSONArray) new JSONParser().parse(userFile);
		JSONArray companyArray = (JSONArray) new JSONParser().parse(companyFile);
		boolean status=false;
		System.out.println("Enter your name to verify existing user..");
		String userName=scanner.next();
		for (Object object : userArray) {
			JSONObject jsonObject = (JSONObject) object;
			String name = (String) jsonObject.get("name");
			if (name.equals(userName)) {
				status = true;
			}
		}
		if(status){
			for (Object object : userArray) {
				JSONObject userObject = (JSONObject) object;
				String name=(String) userObject.get("name");
				if (name.equals(userName)) {
				
					for (Object object1 : companyArray) {
						JSONObject companyObject = (JSONObject) object1;
						String matchSymbol=(String) companyObject.get("symbol");
						if(matchSymbol.equals(symbol)){
							int price=Integer.parseInt((String)companyObject.get("price"));
							int number=Integer.parseInt((String)companyObject.get("number"));
							int shareCount=amount/price;
							
						}
				}
			}	
			}	
		}else{
			System.out.println("You are name is not exist..please make a new registration...");
		}
		

	}

	public void sell(int amount, String symbol) {

	}

	public void save(Object jsonArray) throws Exception {
		JSONArray array = (JSONArray) jsonArray;
		FileWriter fileWriter = new FileWriter(
				"/root/workspace/ObjectOrientedProgram/src/com/bridgeit/oop/Utility/user_stock_details.json");
		fileWriter.write(array.toJSONString());
		fileWriter.flush();
		fileWriter.close();

	}

	public void printReport() throws Exception {
		FileReader fileReader = new FileReader(
				"/root/workspace/ObjectOrientedProgram/src/com/bridgeit/oop/Utility/user_stock_details.json");
		JSONArray jsonArray = (JSONArray) new JSONParser().parse(fileReader);
		System.out.println("****** USER DETAILS ******");
		for (Object object : jsonArray) {
			System.out.println("---------------------------");
			JSONObject jsonObject = (JSONObject) object;
			String name = (String) jsonObject.get("name");
			System.out.println("User Name : " + name);
			int amount = Integer.parseInt((String) jsonObject.get("amount"));
			System.out.println("Amount : " + amount);
			JSONObject numbers = (JSONObject) jsonObject.get("number");
			int one = Integer.parseInt((String) numbers.get("@"));
			System.out.println("For @, number of shares are : " + one);
			int two = Integer.parseInt((String) numbers.get("#"));
			System.out.println("For #, number of shares are : " + two);
			int three = Integer.parseInt((String) numbers.get("$"));
			System.out.println("For $, number of shares are : " + three);

		}
		System.out.println();

		FileReader fileReader1 = new FileReader(
				"/root/workspace/ObjectOrientedProgram/src/com/bridgeit/oop/Utility/company_stock_details.json");
		JSONArray jsonArray1 = (JSONArray) new JSONParser().parse(fileReader1);
		System.out.println("****** COMPANY STOCK REPORT ******");
		System.out.println("------------------------------------------------------------------");
		for (Object object1 : jsonArray1) {

			JSONObject jsonObject = (JSONObject) object1;
			int numberShare = Integer.parseInt((String) jsonObject.get("number"));
			int price = Integer.parseInt((String) jsonObject.get("price"));
			String symbol = (String) jsonObject.get("symbol");
			System.out.println("Symbol of the Share '" + symbol + "', Price of per Share '" + price
					+ "' And Number of Shares are : " + numberShare);

		}
		System.out.println();

	}
}
