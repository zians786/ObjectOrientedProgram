package com.bridgeit.oop.Program;

import java.text.ParseException;

import java.util.Scanner;

import com.bridgeit.oop.Utility.Utility;

public class ClinicManagement {

public static void main(String[] args) throws Exception {
		Utility utility=new Utility();
		Scanner scanner = new Scanner(System.in);
		while (true) {
		System.out.println("Welcome To Clinic Management System..\n1.Add Doctors\n2.Add Patients\n3.Search Doctor\n4.Search Patient\n5.Take an appoitment\n6.Exit");
		System.out.println("Enter Your Choice...");
		int choice = scanner.nextInt();
		switch (choice) {
		case 1:
			utility.addDoctors();
		break;
		case 2:
			utility.addPatient();
		break;
		case 3:
			utility.searchDoctor();
		break;
		case 4:
			utility.searchPatient();
		break;
		case 5:
			utility.takeAnAppointment();
		break;
		case 6:
			System.exit(0);
		break;
		}
		}
		}

}
