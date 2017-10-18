package com.bridgeit.oop.Program;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.bridgeit.oop.Utility.Utility;

public class StockReport {

	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		Utility utility=new Utility();
		System.out.println("Enter the number of Stocks...");
		int stock = scanner.nextInt();
		utility.writeStock(stock);
		utility.readStock();
	}
}