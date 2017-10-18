package com.bridgeit.oop.Utility;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Utility {

	public void regularExpression(String fullName,String number,String message){
		String regex_Name="<<name>>";
		String regex_Full_Name="<<full name>>";
		String regex_Number="91-xxxxxxxxxx";
		String regex_date="01/01/2016";
		
		long time=System.currentTimeMillis();
		Date date=new Date(time);
		String currentDate=date.toString();
		
		String[] first_name=fullName.split(" ");
		
		Pattern p1=Pattern.compile(regex_Name);
		Matcher m1=p1.matcher(message);
		message=m1.replaceAll(first_name[0]);
		
		Pattern p2=Pattern.compile(regex_Full_Name);
		Matcher m2=p2.matcher(message);
		message=m2.replaceAll(fullName);

		Pattern p3=Pattern.compile(regex_Number);
		Matcher m3=p3.matcher(message);
		message=m3.replaceAll(number);
		
		Pattern p4=Pattern.compile(regex_date);
		Matcher m4=p4.matcher(message);
		message=m4.replaceAll(currentDate);
		
		System.out.println(message);
	}

	
	public void writeStock(int stock) throws Exception{
		Scanner scanner = new Scanner(System.in);
		JSONArray jsonArray = new JSONArray();
		String name, number, price;
		while (stock != 0) {
		
			JSONObject jsonObject = new JSONObject();

			System.out.println("Enter Share name :");
			name = scanner.nextLine();
			jsonObject.put("name", name);

			System.out.println("Enter number of shares");
			number = scanner.nextLine();
			jsonObject.put("number", number);

			System.out.println("Enter value of each share");
			price = scanner.nextLine();
			jsonObject.put("price", price);

			jsonArray.add(jsonObject);
			stock--;
		}
		
		FileWriter fileWriter=new FileWriter("/root/workspace/ObjectOrientedProgram/src/com/bridgeit/oop/Utility/stock_report.json");
		fileWriter.write(jsonArray.toJSONString());
		fileWriter.flush();
		fileWriter.close();
	}
	
	public void readStock() throws Exception{
		FileReader fileReader=new FileReader("/root/workspace/ObjectOrientedProgram/src/com/bridgeit/oop/Utility/stock_report.json");
		JSONArray jsonArray=(JSONArray) new JSONParser().parse(fileReader);
		int total=0;
		for(Object o:jsonArray){
			JSONObject jsonObject=(JSONObject) o;

	        String item_name = (String) jsonObject.get("name");
	        System.out.println("Share Name --> " + item_name);

	        String nunber_Share =(String)  jsonObject.get("number");
	        System.out.println("Number Of Shares are --> " + nunber_Share);
	        int share_Num=Integer.parseInt(nunber_Share);

	        String price_Of_Item = (String) jsonObject.get("price");
	        System.out.println("Share Amount is --> " + price_Of_Item);
	        int item_Price=Integer.parseInt(price_Of_Item);
	 
	        System.out.println("Total Inventary : "+(share_Num*item_Price));
	        System.out.println();
	        total=total+share_Num*item_Price;
	    }

	 System.out.println("The Total Stocks Report  ------> "+total);
		
		}
	
	public <E extends Comparable<E>> E[] bubbleSort(E[] array) {
		for (int i = 0; i < array.length - 1; i++) {
			for (int j = 0; j < array.length - 1; j++) {
				if (array[j].compareTo(array[j + 1]) > 0) {
					E temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
				}
			}
		}

		return array;
	}







}
	
	
	

	
	
	
	
	

