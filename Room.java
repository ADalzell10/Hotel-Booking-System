package Coursework02;

public class Room {
	private int roomNumber;
	private String roomType;
	private double price;
	private boolean hasBalcony;
	private boolean hasLounge;
	private String eMail = "";

	public Room(int roomNumber, String roomType, double price, boolean hasBalcony, boolean hasLounge, String eMail) {
		this.roomNumber = roomNumber;
		this.roomType = roomType;
		this.price = price;
		this.hasBalcony = hasBalcony;
		this.hasLounge = hasLounge;
		this.eMail = eMail;
	}

	public String saveRooms(){
		return roomNumber + " " + roomType + " " + price + " " + hasBalcony + " " + hasLounge + " " + eMail;
	}

	public String readRooms() {
		String read = "Room: " + roomNumber + ", Type: " + roomType + ", Price: £" + price + 
				"0, Has Balcony: " + hasBalcony + ", Has Lounge: " + hasLounge + " " + eMail;
		return read;		
	}

	public int getRoomNo() {
		return roomNumber;
	}

	public String getRoomType(){
		return roomType;
	}

	public double getPrice() {
		return price;
	}

	public boolean getBalcony() {
		return hasBalcony;
	}

	public boolean getLounge() {
		return hasLounge;
	}

	public String getEmail() {
		return eMail;
	}

	public void reserveRoom(String eMail)  {
		this.eMail = eMail;	
	}

	public void cancelRoom() {
		eMail = "";
	}

	public boolean isReserved() {
		if (eMail.equals("")) {

			return false;
		}
		else {
			return true;
		}
	}
}