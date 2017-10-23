package com.bridgeit.oop.Program;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.bridgeit.oop.Utility.Utility;

public class JsonInventory {

	public static void main(String[] args) throws Exception {
		Utility utility=new Utility();
		utility.inventoryReport();
		}
		}


