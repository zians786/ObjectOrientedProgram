package com.bridgeit.oop.Program;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonInventory {

	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		Object data=new JSONParser().parse(new FileReader("/root/workspace/ObjectOrientedProgram/src/com/bridgeit/oop/Utility/Inventory.json"));
		JSONObject invent=(JSONObject) data;
		JSONArray inventory= (JSONArray) invent.get("Inventory");
	
		for(Object o:inventory){
			
			JSONObject details=(JSONObject) o;
			String name= (String) details.get("name");
			long weight= (long)details.get("weight");
			long price=(long) details.get("price_per_kg");
			System.out.println("Inventory Name --> "+name+"\nIt has "+weight+"kg of "+name+" and Value of per kg is "+price);
			System.out.println("Total Inventory is "+(price*weight));
			System.out.println();
		}
		}
		}


