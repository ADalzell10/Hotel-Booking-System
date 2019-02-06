package Coursework02;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class HotelBookingSystem {

	private static Scanner console = new Scanner(System.in);

	private static final Room[] rooms = new Room [17];

	public static void main(String[] args) throws FileNotFoundException {

		String response = "";

		Scanner file = null;

		try {
			file = new Scanner(new FileReader("rooms.txt"));

		} catch (FileNotFoundException ignore) {
			System.out.println("Please check that the file is in the correct area.");
		}

		int index = 0;

		while (file.hasNextLine()) {
			String[] roomDetails = file.nextLine().split(" ");
			rooms[index] = new Room(Integer.parseInt(roomDetails[0]), roomDetails[1], Double.parseDouble(roomDetails[2]), Boolean.parseBoolean(roomDetails[3]), 
					Boolean.parseBoolean(roomDetails[4]), roomDetails.length == 5 ? "" : roomDetails[5]);

			index ++;
		}

		file.close();
		
		// Saves the rooms back into rooms.txt file
		
		PrintWriter pw = new PrintWriter("rooms.txt");
		for(int i=0; i < rooms.length; i++) {
			pw.println(rooms[i].saveRooms());
		} 	
		pw.close();

		System.out.println("\n-- Room Booking System --");

		do {
			System.out.println("\n-- Main Menu --");
			System.out.println("1 - Reserve Room");
			System.out.println("2 - Cancel Room");
			System.out.println("3 - View Room Reservations");
			System.out.println("Q - Quit");
			System.out.print("Enter choice: ");

			response = console.next().toUpperCase();

			switch (response) {
			case "1": {
				reserveRoom();
				break;
			}
			case "2": {
				cancelRoom();
				break;
			}
			case "3": {
				viewReservations();
				break;
			}
			}
		} while (!response.equals("Q"));
		System.out.println("-- Booking System Ended --");
	}

	private static void reserveRoom()  {

		// Getting customer room requirements

		System.out.print("\nEnter room type (Single/Double/Suite): ");
		String roomType = console.next();

		double price = 0;
		while (true) {
			System.out.print("Maximum price(£): ");
			try {
				price = Double.parseDouble(console.next());
				break;
			} catch (NumberFormatException ignore) {
				System.out.println("Invalid input, please enter a price.");
			}
		}

		System.out.print("Want a balcony? (True/False): ");
		boolean balcony = console.nextBoolean();

		System.out.print("Want a lounge? (True/False): ");
		boolean lounge = console.nextBoolean();

		System.out.println("\nRooms that match your search: ");

		// Matching requirements to specific rooms

		boolean roommatch = false;
		for (int i=0; i<rooms.length; i++) {

			if (rooms[i].getRoomType().equalsIgnoreCase(roomType) && rooms[i].getPrice() <= price 
					&& rooms[i].getBalcony() == balcony && rooms[i].getLounge() == lounge) {
				roommatch= true;

				System.out.println(rooms[i].readRooms());
			}
		}
		if (!roommatch){
			System.out.println("Sorry, no rooms matched your search.");
		}
		
		if (roommatch) {
			/* calls a booking method that allows the user to continue with booking when match is found, 
			   otherwise it will loop through the main menu again*/
			booking();
		}
	}

	private static void cancelRoom() {

		System.out.println("\n-- Room Cancellation -- ");

		System.out.print("Enter room number: ");
		int roomNo = console.nextInt();

		System.out.print("Enter customer email: ");
		String email = console.next();

		// Removes customer email reservation from room if both room number and email match the reservation

		boolean roomFound = false;
		for (int i=0; i<rooms.length; i++) {
			if (rooms[i].getRoomNo() == roomNo) {
				if (rooms[i].getEmail().equals(email)) {
					roomFound = true;
					rooms[i].cancelRoom();
					System.out.println("Reservation successfully cancelled.");
				}
			}
		}
		if (!roomFound) {
			System.out.println("The email entered does not match the room number or there was no reservation found for the room entered.");
		}
	}

	private static void viewReservations()  {
		System.out.println("\n-- Reservations --");

		// Prints list of rooms that have been reserved, along with rooms that hold no reservation

		for (int i=0; i<rooms.length; i++) {
			if (rooms[i].isReserved() == true){
				System.out.println("Room Number: " + rooms[i].getRoomNo() + ", Customer email: " + rooms[i].getEmail());
			}

			else{
				System.out.println(rooms[i].getRoomNo() + " - Not Reserved");
			}
		}
	}

	private static void booking(){

		// Selecting room to reserve

		System.out.print("\nEnter number of room you want to Reserve: ");
		int roomNo = console.nextInt();

		System.out.print("Enter customer email: ");
		String email = console.next();

		// Reserves the room entered using customer email

		for (int i=0; i<rooms.length; i++){
			if (rooms[i].getRoomNo() == roomNo) {
				rooms[i].reserveRoom(email);
			}
		}
	}
}