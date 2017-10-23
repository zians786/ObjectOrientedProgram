package com.bridgeit.oop.Utility;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
			save(jsonArray,
					"/root/workspace/ObjectOrientedProgram/src/com/bridgeit/oop/Utility/user_stock_details.json");
			System.out.println("User With Name '" + userName + "', Added Successfully..");

		} else {
			System.out.println("User Already Exist...");
		}
	}

	public void buy(int amount, String symbol) throws Exception {
		Scanner scanner = new Scanner(System.in);
		FileReader userFile = new FileReader(
				"/root/workspace/ObjectOrientedProgram/src/com/bridgeit/oop/Utility/user_stock_details.json");
		FileReader companyFile = new FileReader(
				"/root/workspace/ObjectOrientedProgram/src/com/bridgeit/oop/Utility/company_stock_details.json");
		JSONArray userArray = (JSONArray) new JSONParser().parse(userFile);
		JSONArray companyArray = (JSONArray) new JSONParser().parse(companyFile);
		boolean status = false;
		int shareCount = 0;
		System.out.println("Enter your name to verify existing user..");
		String userName = scanner.next();
		for (Object object : userArray) {
			JSONObject jsonObject = (JSONObject) object;
			String name = (String) jsonObject.get("name");
			int userAmount = Integer.parseInt((String) jsonObject.get("amount"));
			if (name.equals(userName) && amount <= userAmount) {
				status = true;
			}
		}
		if (status) {
			for (Object object : userArray) {
				
				JSONObject userObject = (JSONObject) object;
				String name = (String) userObject.get("name");
				int userAmount = Integer.parseInt((String) userObject.get("amount"));

				if (name.equals(userName)) {

					for (Object object1 : companyArray) {
						JSONObject companyObject = (JSONObject) object1;
						String matchSymbol = (String) companyObject.get("symbol");
						if (matchSymbol.equals(symbol)) {
							int price = Integer.parseInt((String) companyObject.get("price"));
							int number = Integer.parseInt((String) companyObject.get("number"));
							shareCount = amount / price;
							number = number - shareCount;
							String temp = Integer.toString(number);
							companyObject.replace("number", temp);
							companyObject.replace("date", new Date(System.currentTimeMillis()).toString());
							userAmount -= amount;
							String tempAmount = Integer.toString(userAmount);
							userObject.replace("amount", tempAmount);
							JSONObject numberObject = new JSONObject();
							numberObject = (JSONObject) userObject.get("number");
							int symbolNumber = Integer.parseInt((String) numberObject.get(symbol));
							symbolNumber += shareCount;
							String tempShare = Integer.toString(symbolNumber);
							numberObject.replace(symbol, tempShare);
							System.out.println("Transaction Successful..");
							System.out.println(new Date(System.currentTimeMillis()).toString());
						}
					}

				}
			}
		} else {
			System.out.println("You Dont have Enough amount to perform this transaction..");
		}

		save(userArray, "/root/workspace/ObjectOrientedProgram/src/com/bridgeit/oop/Utility/user_stock_details.json");
		save(companyArray,
				"/root/workspace/ObjectOrientedProgram/src/com/bridgeit/oop/Utility/company_stock_details.json");
	}

	public void sell(int amount, String symbol) throws Exception {
		Scanner scanner = new Scanner(System.in);
		FileReader userFile = new FileReader(
				"/root/workspace/ObjectOrientedProgram/src/com/bridgeit/oop/Utility/user_stock_details.json");
		FileReader companyFile = new FileReader(
				"/root/workspace/ObjectOrientedProgram/src/com/bridgeit/oop/Utility/company_stock_details.json");
		JSONArray userArray = (JSONArray) new JSONParser().parse(userFile);
		JSONArray companyArray = (JSONArray) new JSONParser().parse(companyFile);
		boolean status = false;
		int shareCount = 0;
		System.out.println("Enter your name to verify existing user..");
		String userName = scanner.next();
		for (Object object : userArray) {
			JSONObject jsonObject = (JSONObject) object;
			String name = (String) jsonObject.get("name");

			if (name.equals(userName)) {
				status = true;
			}
		}
		if (status) {
			for (Object object : userArray) {
				JSONObject userObject = (JSONObject) object;
				String name = (String) userObject.get("name");
				int userAmount = Integer.parseInt((String) userObject.get("amount"));

				JSONObject numberObject = new JSONObject();
				numberObject = (JSONObject) userObject.get("number");
				int symbolNumber = Integer.parseInt((String) numberObject.get(symbol));
				if (name.equals(userName)) {

					for (Object object1 : companyArray) {
						JSONObject companyObject = (JSONObject) object1;
						String matchSymbol = (String) companyObject.get("symbol");
						if (matchSymbol.equals(symbol)) {
							if (symbolNumber > amount / Integer.parseInt((String) companyObject.get("price"))) {
								int price = Integer.parseInt((String) companyObject.get("price"));
								int number = Integer.parseInt((String) companyObject.get("number"));
								shareCount = amount / price;
								number = number + shareCount;
								String temp = Integer.toString(number);
								companyObject.replace("number", temp);
								companyObject.replace("date", new Date(System.currentTimeMillis()).toString());
								userAmount += amount;
								String tempAmount = Integer.toString(userAmount);
								userObject.replace("amount", tempAmount);
								symbolNumber -= shareCount;
								String tempShare = Integer.toString(symbolNumber);
								numberObject.replace(symbol, tempShare);
								System.out.println("Transaction Successful..");
								System.out.println(new Date(System.currentTimeMillis()).toString());
							} else {
								System.out.println("You Dont Have Enough Shares to get That much money..");
							}
						}
					}

				}
			}
		} else {
			System.out.println("You Dont have Enough amount to perform this transaction..");
		}

		save(userArray, "/root/workspace/ObjectOrientedProgram/src/com/bridgeit/oop/Utility/user_stock_details.json");
		save(companyArray,
				"/root/workspace/ObjectOrientedProgram/src/com/bridgeit/oop/Utility/company_stock_details.json");
	}

	public void save(Object jsonArray, String file) throws Exception {
		JSONArray array = (JSONArray) jsonArray;
		FileWriter fileWriter = new FileWriter(file);
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
	
	
	public void addCompany(LinkedListUtility linkCompany) throws Exception{
		Scanner scanner=new Scanner(System.in);
			
		JSONObject companyObject=new JSONObject();
		
		System.out.println("Enter Symbol");
		companyObject.put("symbol", scanner.next());
		
		System.out.println("Enter Price of per Share");
		companyObject.put("price", scanner.next());
		
		System.out.println("Enter number of share");
		companyObject.put("number", scanner.next());
		
		Date date=new Date(System.currentTimeMillis());
		 String stringDate=date.toString();
		 companyObject.put("date", stringDate);
		linkCompany.insertAtEnd(companyObject);
		
	}

	public void removeCompany(LinkedListUtility linkCompany) {
		System.out.println("Enter The Symbol of share you want to delete");
		String symbol=new Scanner(System.in).next();
		int size=linkCompany.size();
		boolean status=true;
		for(int i=1;i<=size;i++){
			JSONObject dataObject=new JSONObject();
			dataObject=(JSONObject) linkCompany.dataAtPos(i);
		
			if(symbol.equals((String) dataObject.get("symbol"))){
				linkCompany.deleteAtPos(i);
				status=false;
			}
		}
		if(status){
			System.out.println("Symbol of share not Found");
		}
	}

	public void writeToJSON(LinkedListUtility linkCompany,String file) throws Exception {
	JSONArray array=new JSONArray();
	int size=linkCompany.size();
	
		while(!linkCompany.isEmpty()){
			JSONObject data=new JSONObject();
			data=(JSONObject) linkCompany.deleteAtPos(size);
			array.add(data);
			size--;
		}
		save(array,file);
	}
	
	
	
}
