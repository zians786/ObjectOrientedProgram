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
				"/root/workspace/ObjectOrientedProgram/src/com/bridgeit/oop/Utility/stock_report.json");
		fileWriter.write(jsonArray.toJSONString());
		fileWriter.flush();
		fileWriter.close();
	}

	public void readStock() throws Exception {
		FileReader fileReader = new FileReader(
				"/root/workspace/ObjectOrientedProgram/src/com/bridgeit/oop/Utility/stock_report.json");
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
				new FileReader("/root/workspace/ObjectOrientedProgram/src/com/bridgeit/oop/Utility/Inventory.json"));

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
				"/root/workspace/ObjectOrientedProgram/src/com/bridgeit/oop/Utility/Inventory.json");
		fileWriter.write(inventory.toJSONString());
		fileWriter.flush();
		fileWriter.close();

	}

	public void totalInventory() throws Exception {
		Object data = new JSONParser().parse(
				new FileReader("/root/workspace/ObjectOrientedProgram/src/com/bridgeit/oop/Utility/Inventory.json"));

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
				new FileReader("/root/workspace/ObjectOrientedProgram/src/com/bridgeit/oop/Utility/Inventory.json"));

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

	public static void addDoctors() {
		Scanner scanner = new Scanner(System.in);

		JSONArray array = new JSONArray();
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

		array.add(json);

		try {
			FileWriter jsonFileWriter = new FileWriter(
					"/root/workspace/ObjectOrientedProgram/src/com/bridgeit/oop/Utility/doctor.json");
			jsonFileWriter.write(array.toJSONString());
			jsonFileWriter.flush();
			jsonFileWriter.close();
			System.out.println("Doctor Added:" + array);
			System.out.println("Doctor has been added successfully.. :");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void addPatient() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter no. of Patient");

		JSONArray array = new JSONArray();
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

		array.add(json1);

		try {
			FileWriter jsonFileWriter = new FileWriter(
					"/root/workspace/ObjectOrientedProgram/src/com/bridgeit/oop/Utility/patient.json");
			jsonFileWriter.write(array.toJSONString());
			jsonFileWriter.flush();
			jsonFileWriter.close();
			System.out.println(" Patient has been added successfully...");

			System.out.println("Pateint Added: " + array);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void searchDoctor() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter Name or ID or Specialization or Availiablity Of the Doctor to find..");
		String value = scanner.next();
		try {
			JSONParser parser = new JSONParser();
			JSONArray array = (JSONArray) parser.parse(
					new FileReader("/root/workspace/ObjectOrientedProgram/src/com/bridgeit/oop/Utility/doctor.json"));
			for (Object object : array) {
				JSONObject jsonobject = (JSONObject) object;
				if (value.equals((String) jsonobject.get("Patient_Name"))
						|| value.equals((String) jsonobject.get("Doctor_Availiablity"))
						|| value.equals((String) jsonobject.get("Doctor_Specialization"))
						|| value.equals(String.valueOf((long) jsonobject.get("Doctor_ID")))) {
					System.out.println("Doctor_found " + jsonobject);
				} else {
					System.out.println("Doctor Not found..");
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void searchPatient() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter Name or ID or Mobile Number or Age Of Patient to find..");
		String value = scanner.next();
		try {
			JSONParser parser = new JSONParser();
			JSONArray array = (JSONArray) parser.parse(
					new FileReader("/root/workspace/ObjectOrientedProgram/src/com/bridgeit/oop/Utility/patient.json"));
			for (Object object : array) {
				JSONObject jsonobject = (JSONObject) object;
				if (value.equals((String) jsonobject.get("Patient_Name"))
						|| value.equals(String.valueOf((long) jsonobject.get("Patient_ID")))
						|| value.equals(String.valueOf((long) jsonobject.get("Patient_MobileNumber")))
						|| value.equals(String.valueOf((long) jsonobject.get("Patient_Age")))) {
					System.out.println("Patient_found " + jsonobject);
				} else {
					System.out.println("Patient Not found..");
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void takeAnAppointment() throws Exception {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter Patient_Name");
		String patient_name = scanner.next();
		System.out.println("Enter Doctor_Name for to take an Appointment");
		String doctername = scanner.next();
		System.out.println("Enter the date : e.g dd-MM-yyyy");
		String stringDate = scanner.next();

		String doctorInfo = null;

		JSONParser parser = new JSONParser();
		JSONArray array = (JSONArray) parser.parse(
				new FileReader("/root/workspace/ObjectOrientedProgram/src/com/bridgeit/oop/Utility/doctor.json"));
		JSONArray appointArray = (JSONArray) parser.parse(
				new FileReader("/root/workspace/ObjectOrientedProgram/src/com/bridgeit/oop/Utility/appointment.json"));
		
		boolean status = true;
		for (int i = 0; i < array.size(); i++) {
			JSONObject obj = (JSONObject) array.get(i);
			String doctorName = (String) obj.get("Doctor_Name");

			if (doctorName.equals(doctername)) {
				for(int j=0;j<appointArray.size();j++){
					JSONObject appObject = (JSONObject) array.get(i);
					String  = (String) appObj.get("Doctor_Name");

				}
				doctorInfo = doctorName;
				JSONArray appointmentFileObj = new JSONArray();

				JSONObject obj1 = new JSONObject();

				obj1.put("Doctor_Name", doctorInfo);

				obj1.put("Patient_Name", patient_name);
				obj1.put("Booking Date ", (stringDate));
				appointmentFileObj.add(obj1);
				FileWriter filewriter = new FileWriter(
						"/home/bridgeit/eclipse-workspace/OppsPrograms/src/com/bridgelabz/utility/appointment.json");
				filewriter.write(appointmentFileObj.toJSONString());
				filewriter.flush();
				filewriter.close();
				status = false;
				System.out.println("hello " + patient_name + " Your Appointment is fixed  With Doctor " + doctorInfo
						+ " For: " + (stringDate));

			}
		}
		if (status) {
			System.out.println("doctors not found in this name");
		}
	}

	public void stringToDate(String dat) throws ParseException {
		Date date1 = new Date(System.currentTimeMillis());
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = dateFormat.parse(dat);
		System.out.println(dateFormat.format(date));
	}

}
