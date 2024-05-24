package com.example.app;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Date;

public class Table {
	private int Number;
	private int Capacity;
	private int MergedTableNumber;
	private String OrderId;
	private String ReservationId;
	private String SpecialRequest;
	private boolean Occupied;
	private boolean isMerged;

	public Table(int Number, int Capacity) {
		setNumber(Number);
		setCapacity(Capacity);
		setMergedTableNumber(-1);
		setOrderId("");
		setReservationId("");
		setSpecialRequest("");
		setOccupied(false);
		setMerged(false);
		AddToDatabase();
	}

	private void setNumber(int Number) {
		this.Number = Number;
	}

	private void setCapacity(int Capacity) {
		this.Capacity = Capacity;
	}

	private void setMergedTableNumber(int MergedTableNumber) {
		this.MergedTableNumber = MergedTableNumber;
	}

	private void setOrderId(String OrderId) {
		this.OrderId = OrderId;
	}

	private void setReservationId(String ReservationId) {
		this.ReservationId = ReservationId;
	}

	private void setSpecialRequest(String SpecialRequest) {
		this.SpecialRequest = SpecialRequest;
	}

	private void setOccupied(boolean Occupied) {
		this.Occupied = Occupied;
	}

	private void setMerged(boolean isMerged) {
		this.isMerged = isMerged;
	}

	private void AddToDatabase() {
		// Add the table to the database
		TableDatabase tableDatabase = new TableDatabase();
		tableDatabase.AddTable();
	}

	private class TableDatabase {
		// Database connection
		private static MongoClient mongoClient = DatabaseConnection.getmongoClient();
		private static MongoDatabase database = DatabaseConnection.getdatabaseName();

		public TableDatabase() {
			database = mongoClient.getDatabase("TSFPos");
			System.out.println("Database connection successful");
		}

		public void AddTable() {
			Document document = new Document("number", Number)
					.append("capacity", Capacity)
					.append("mergedTableNumber", MergedTableNumber)
					.append("orderId", OrderId)
					.append("reservationId", ReservationId)
					.append("specialRequest", SpecialRequest)
					.append("occupied", Occupied)
					.append("isMerged", isMerged);
			database.getCollection("Tables").insertOne(document);
			System.out.println("Menu Item added to the Database " + Date.from(java.time.Instant.now()));
		}

		public static int getNumber() {
			Document document = database.getCollection("Tables").find().first();
			return document.getInteger("number");
		}
	}
}
