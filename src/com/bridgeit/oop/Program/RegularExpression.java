package com.bridgeit.oop.Program;

import java.util.Scanner;

import com.bridgeit.oop.Utility.Utility;

public class RegularExpression {

	public static void main(String[] args) {
		Scanner scanner=new Scanner(System.in);
		String fullUserName,mobile_Number;
	
		System.out.println("Enter Your Full Name...");
		fullUserName=scanner.nextLine();
		
		System.out.println("Enter Your Mobile Number........");
		mobile_Number=scanner.nextLine();
		
		
	String message=" Hello <<name>> , We have your full name as <<full name>> in our system.your contact number is 91-xxxxxxxxxx.Please,let us know in case of any clarification Thank you BridgeLabz 01/01/2016.";

	Utility utility=new Utility();
	utility.regularExpression(fullUserName, mobile_Number, message);

	}

}
