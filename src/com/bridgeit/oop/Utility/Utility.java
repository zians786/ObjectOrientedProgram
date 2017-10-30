package com.bridgeit.oop.Utility;

import java.io.FileNotFoundException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Utility {

	public void regularExpression(String fullName, String number, String message) {
		String regex_Name = "<<name>>";
		String regex_Full_Name = "<<full name>>";
		String regex_Number = "91-xxxxxxxxxx";
		String regex_date = "01/01/2016";

		long time = System.currentTimeMillis();
		Date date = new Date(time);
		String currentDate = date.toString();

		String[] first_name = fullName.split(" ");

		Pattern p1 = Pattern.compile(regex_Name);
		Matcher m1 = p1.matcher(message);
		message = m1.replaceAll(first_name[0]);

		Pattern p2 = Pattern.compile(regex_Full_Name);
		Matcher m2 = p2.matcher(message);
		message = m2.replaceAll(fullName);

		Pattern p3 = Pattern.compile(regex_Number);
		Matcher m3 = p3.matcher(message);
		message = m3.replaceAll(number);

		Pattern p4 = Pattern.compile(regex_date);
		Matcher m4 = p4.matcher(message);
		message = m4.replaceAll(currentDate);

		System.out.println(message);
	}

	public void writeStock(int stock) throws Exception {
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

		FileWriter fileWriter = new FileWriter(
				"/home/bridgeit/Desktop/ziauddin/ObjectOrientedProgram/src/com/bridgeit/oop/Utility/stock_report.json");
		fileWriter.write(jsonArray.toJSONString());
		fileWriter.flush();
		fileWriter.close();
	}

	public void readStock() throws Exception {
		FileReader fileReader = new FileReader(
				"/home/bridgeit/Desktop/ziauddin/ObjectOrientedProgram/src/com/bridgeit/oop/Utility/stock_report.json");
		JSONArray jsonArray = (JSONArray) new JSONParser().parse(fileReader);
		int total = 0;
		for (Object o : jsonArray) {
			JSONObject jsonObject = (JSONObject) o;

			String item_name = (String) jsonObject.get("name");
			System.out.println("Share Name --> " + item_name);

			String nunber_Share = (String) jsonObject.get("number");
			System.out.println("Number Of Shares are --> " + nunber_Share);
			int share_Num = Integer.parseInt(nunber_Share);

			String price_Of_Item = (String) jsonObject.get("price");
			System.out.println("Share Amount is --> " + price_Of_Item);
			int item_Price = Integer.parseInt(price_Of_Item);

			System.out.println("Total Inventary : " + (share_Num * item_Price));
			System.out.println();
			total = total + share_Num * item_Price;
		}

		System.out.println("The Total Stocks Report  ------> " + total);

	}

	public void addInventory() throws Exception {
		Scanner scanner = new Scanner(System.in);
		Object data = new JSONParser().parse(
				new FileReader("/home/bridgeit/Desktop/ziauddin/ObjectOrientedProgram/src/com/bridgeit/oop/Utility/Inventory.json"));

		JSONArray inventory = (JSONArray) data;
		JSONObject inventObject = new JSONObject();
		System.out.println("Enter Inventory Name :");
		inventObject.put("name", scanner.next());
		System.out.println("Enter Price Per kg :");
		inventObject.put("price_per_kg", scanner.nextLong());

		System.out.println("Enter Weight of the Inventory :");
		inventObject.put("weight", scanner.nextLong());
		inventory.add(inventObject);

		FileWriter fileWriter = new FileWriter(
				"/home/bridgeit/Desktop/ziauddin/ObjectOrientedProgram/src/com/bridgeit/oop/Utility/Inventory.json");
		fileWriter.write(inventory.toJSONString());
		fileWriter.flush();
		fileWriter.close();

	}

	public void totalInventory() throws Exception {
		Object data = new JSONParser().parse(
				new FileReader("/home/bridgeit/Desktop/ziauddin/ObjectOrientedProgram/src/com/bridgeit/oop/Utility/Inventory.json"));

		JSONArray inventory = (JSONArray) data;
		System.out.println("Enter Inventory name..");
		String searchName = new Scanner(System.in).next();
		boolean status = true;
		for (Object o : inventory) {

			JSONObject details = (JSONObject) o;
			String name = (String) details.get("name");
			if (searchName.equals(name)) {
				long weight = (long) details.get("weight");
				long price = (long) details.get("price_per_kg");
				System.out.println("Inventory Name --> " + name + "\nIt has " + weight + "kg of " + name
						+ " and Value of per kg is " + price);
				System.out.println("Total Inventory is " + (price * weight));
				System.out.println();
				status = false;
			}

		}
		if (status) {
			System.out.println("Inventory not Found..");
		}

	}

	public void inventoryReport() throws Exception {
		Object data = new JSONParser().parse(
				new FileReader("/home/bridgeit/Desktop/ziauddin/ObjectOrientedProgram/src/com/bridgeit/oop/Utility/Inventory.json"));

		JSONArray inventory = (JSONArray) data;

		for (Object o : inventory) {

			JSONObject details = (JSONObject) o;
			String name = (String) details.get("name");
			long weight = (long) details.get("weight");
			long price = (long) details.get("price_per_kg");
			System.out.println("Inventory Name --> " + name + "\nIt has " + weight + "kg of " + name
					+ " and Value of per kg is " + price);
			System.out.println("Total Inventory is " + (price * weight));
			System.out.println();
		}

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

	public static void addDoctors() throws Exception {
		JSONParser parser = new JSONParser();
		JSONArray doctorArray = (JSONArray) parser.parse(
				new FileReader("/home/bridgeit/Desktop/ziauddin/ObjectOrientedProgram/src/com/bridgeit/oop/Utility/doctor.json"));
		
		
		Scanner scanner = new Scanner(System.in);

		
		JSONObject json = new JSONObject();

		System.out.println("Enter I.D doctor");
		int id = scanner.nextInt();
		json.put("Doctor_ID", id);

		System.out.println("Enter name of doctor");
		String name = scanner.next();
		json.put("Doctor_Name", name);

		System.out.println("Enter Specialization of doctor");
		String specilization = scanner.next();
		json.put("Doctor_Specialization", specilization);

		System.out.println("Enter Availablity of doctor");
		String available = scanner.next();
		json.put("Doctor_Availiablity", available);

		doctorArray.add(json);

		try {
			FileWriter jsonFileWriter = new FileWriter(
					"/home/bridgeit/Desktop/ziauddin/ObjectOrientedProgram/src/com/bridgeit/oop/Utility/doctor.json");
			jsonFileWriter.write(doctorArray.toJSONString());
			jsonFileWriter.flush();
			jsonFileWriter.close();
			System.out.println("Doctor has been added successfully.. :");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void addPatient() throws Exception {
		JSONParser parser = new JSONParser();
		JSONArray patientArray = (JSONArray) parser.parse(
				new FileReader("/home/bridgeit/Desktop/ziauddin/ObjectOrientedProgram/src/com/bridgeit/oop/Utility/patient.json"));
		
		
		Scanner scanner = new Scanner(System.in);
		
		
		JSONObject json1 = new JSONObject();

		System.out.println("Enter patient ID. :");
		int id = scanner.nextInt();
		json1.put("Patient_ID", id);

		System.out.println("Enter patient name :");
		String patientname = scanner.next();
		json1.put("Patient_Name", patientname);

		System.out.println("Enter patient mobile number :");
		long number = scanner.nextLong();
		json1.put("Patient_MobileNumber", number);

		System.out.println("Enter patient age");
		int age = scanner.nextInt();
		json1.put("Patient_Age", age);

		patientArray.add(json1);

		try {
			FileWriter jsonFileWriter = new FileWriter(
					"/home/bridgeit/Desktop/ziauddin/ObjectOrientedProgram/src/com/bridgeit/oop/Utility/patient.json");
			jsonFileWriter.write(patientArray.toJSONString());
			jsonFileWriter.flush();
			jsonFileWriter.close();
			System.out.println(" Patient has been added successfully...");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void searchDoctor() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter Name or ID or Specialization or Availiablity Of the Doctor to find..");
		String value = scanner.next();
		boolean status=true;
		try {
			JSONParser parser = new JSONParser();
			JSONArray array = (JSONArray) parser.parse(
					new FileReader("/home/bridgeit/Desktop/ziauddin/ObjectOrientedProgram/src/com/bridgeit/oop/Utility/doctor.json"));
			for (Object object : array) {
				JSONObject jsonobject = (JSONObject) object;
				if (value.equals((String) jsonobject.get("Doctor_Name"))
						|| value.equals((String) jsonobject.get("Doctor_Availiablity"))
						|| value.equals((String) jsonobject.get("Doctor_Specialization"))
						|| value.equals(String.valueOf((long) jsonobject.get("Doctor_ID")))) {
					System.out.println("Doctor_found " + jsonobject);
					status=false;
				} 
				
			}
			if(status) {
				System.out.println("Doctor Not found..");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void searchPatient() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter Name or ID or Mobile Number or Age Of Patient to find..");
		String value = scanner.next();
		boolean status=true;
		try {
			JSONParser parser = new JSONParser();
			JSONArray array = (JSONArray) parser.parse(
					new FileReader("/home/bridgeit/Desktop/ziauddin/ObjectOrientedProgram/src/com/bridgeit/oop/Utility/patient.json"));
			for (Object object : array) {
				JSONObject jsonobject = (JSONObject) object;
				if (value.equals((String) jsonobject.get("Patient_Name"))
						|| value.equals(String.valueOf((long) jsonobject.get("Patient_ID")))
						|| value.equals(String.valueOf((long) jsonobject.get("Patient_MobileNumber")))
						|| value.equals(String.valueOf((long) jsonobject.get("Patient_Age")))) {
					System.out.println("Patient_found " + jsonobject);
					status=false;
				} 
			}
			if(status) {
				System.out.println("Patient Not found..");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public  void takeAnAppointment() throws Exception {
		StockAccount utility=new StockAccount();
		boolean found=false;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter Patient_Name");
		String patient_name = scanner.next();
		System.out.println("Enter Doctor_Name for to take an Appointment");
		String doctername = scanner.next();
		System.out.println("Enter the date : e.g dd-MM-yyyy");
		String stringDate = scanner.next();

		JSONParser parser = new JSONParser();
		JSONArray doctorArray = (JSONArray) parser.parse(
				new FileReader("/home/bridgeit/Desktop/ziauddin/ObjectOrientedProgram/src/com/bridgeit/oop/Utility/doctor.json"));
		JSONArray appointArray = (JSONArray) parser.parse(
				new FileReader("/home/bridgeit/Desktop/ziauddin/ObjectOrientedProgram/src/com/bridgeit/oop/Utility/appointment.json"));

		boolean doctorExistStatus = true, doctorInAppoint = true;

		for (Object object1:doctorArray) {
			JSONObject obj = (JSONObject) object1;
			String doctorName = (String) obj.get("Doctor_Name");
			String appointDate = (String) obj.get("date");

			if (doctorName.equals(doctername)) {
				doctorExistStatus = false;

				for(Object object:appointArray) {
					JSONObject appObject = (JSONObject) object;
					String dName = (String) appObject.get("Doctor_Name");
					Object appDate =  appObject.get("date");
					if (dName.equals(doctername)) {
						doctorInAppoint = false;
						found=true;
						if (appDate.equals(stringDate)) {
							if (6 > (long) appObject.get("appointNumbers")) {
								JSONObject patientObject = new JSONObject();
								patientObject.put("name", patient_name);
								appObject.put("Patient_Detail", patientObject);
								long appointmentNumber = (long) appObject.get("appointNumbers");
								appObject.replace("appointNumbers", appointmentNumber + 1);
							} else {
								System.out.println("Sorry Appointment List is Full Please Select Different date");
							}
						} else {
							JSONArray patientArray = new JSONArray();
							JSONObject appointObject = new JSONObject();
							JSONObject patientObject = new JSONObject();
							appointObject.put("Doctor_Name", doctername);
							patientObject.put("name", patient_name);
							patientObject.put("number", 1);
							patientArray.add(patientObject);
							appointObject.put("Patient_Detail", patientArray);
							appointObject.put("date", appointDate);
							appointObject.put("appointNumbers", 1);
							appointArray.add(patientObject);
						}
					}

				}
				if (doctorInAppoint) {
					JSONArray patientArray = new JSONArray();
					JSONObject appointObject = new JSONObject();
					JSONObject patientObject = new JSONObject();
					appointObject.put("Doctor_Name", doctername);
					patientObject.put("name", patient_name);
					patientObject.put("number", 1);
					patientArray.add(patientObject);
					appointObject.put("Patient_Detail", patientArray);
					appointObject.put("date", appointDate);
					appointObject.put("appointNumbers", 1);
					appointArray.add(patientObject);
				}

			}
		}
		if (doctorExistStatus) {
			System.out.println("doctors not found in this name");

		}
if(found==true)		
utility.save(appointArray,"/home/bridgeit/Desktop/ziauddin/ObjectOrientedProgram/src/com/bridgeit/oop/Utility/appointment.json");
	}

	public void stringToDate(String dat) throws ParseException {
		Date date1 = new Date(System.currentTimeMillis());
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = dateFormat.parse(dat);
		System.out.println(dateFormat.format(date));
	}

}
