package com.example.app;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;

public class Customer {
	private String CustomerId;
	private String Firstname, Lastname;
	private String email;
	private String phone;
	private ArrayList<String> address;
	private ArrayList<String> orders;
	private Double LoyaltyPoints;

	public Customer(String Firstname, String Lastname, String email, String phone, String addressId, String orderId) {
		setCustomerId();
		this.Firstname = Firstname;
		this.Lastname = Lastname;
		this.email = email;
		this.phone = phone;
		this.address.add(addressId);
		this.orders.add(orderId);
		this.LoyaltyPoints = 0.0;
	}

	private void setCustomerId() {
		Date date = new Date();
		this.CustomerId = "C" + date.getTime();
	}

	public static ArrayList<String> getCustomerId() {
		return CustomerDatabase.getCustomerId();
	}

	public static String getCustomerId(String firstname, String lastname) {
		return CustomerDatabase.getCustomerId(firstname, lastname);
	}

	public static String getCustomerId(String phonenumber) {
		return CustomerDatabase.getCustomerId(phonenumber);
	}

	public static String getFirstname(String CustomerId) {
		return CustomerDatabase.getFirstname(CustomerId);
	}

	public static String getLastname(String CustomerId) {
		return CustomerDatabase.getLastname(CustomerId);
	}

	public static String getFullName(String client) {
		return CustomerDatabase.getFullName(client);
	}

	public static String getEmail(String CustomerId) {
		return CustomerDatabase.getEmail(CustomerId);
	}

	public static String getPhone(String CustomerId) {
		return CustomerDatabase.getPhone(CustomerId);
	}

	public static ArrayList<String> getAddress(String CustomerId) {
		return CustomerDatabase.getAddress(CustomerId);
	}

	public void addCustomer() {
		CustomerDatabase customerDatabase = new CustomerDatabase();
		customerDatabase.addCustomer();
	}

	public static void updateEmail(String customerId, String email) {
		CustomerDatabase.updateEmail(customerId, email);
	}

	public static void updatePhone(String customerId, String phone) {
		CustomerDatabase.updatePhone(customerId, phone);
	}

	public static void AddAddress(String customerId, String address) {
		CustomerDatabase.AddAddress(customerId, address);
	}

	public static void AddOrder(String customerId, String order) {
		CustomerDatabase.AddOrder(customerId, order);
	}

	public static void IncreaseLoyaltyPoints(String customerId, Double points) {
		CustomerDatabase.IncreaseLoyaltyPoints(customerId, points);
	}

	public static void DecreaseLoyaltyPoints(String customerId, Double points) {
		CustomerDatabase.DecreaseLoyaltyPoints(customerId, points);
	}

	public static void DeleteCustomer(String customerId) {
		CustomerDatabase.DeleteCustomer(customerId);
	}

	public static void DeleteAddress(String customerId, String address) {
		CustomerDatabase.DeleteAddress(customerId, address);
	}

	public static void DeleteOrder(String customerId, String order) {
		CustomerDatabase.DeleteOrder(customerId, order);
	}

	public static ArrayList<String> getCustomerNames() {
		return CustomerDatabase.getCustomerNames();
	}

	public static ArrayList<String> getCustomerPhoneNumbers() {
		return CustomerDatabase.getCustomerPhoneNumbers();
	}

	private class CustomerDatabase {
		private static MongoClient mongoClient = DatabaseConnection.getmongoClient();
		private static MongoDatabase database = mongoClient.getDatabase("TSFPos");

		public CustomerDatabase() {
			database = mongoClient.getDatabase("TSFPos");
			System.out.println("Database connection successful");
		}

		public void addCustomer() {
			Document document = new Document("CustomerId", CustomerId)
					.append("Firstname", Firstname)
					.append("Lastname", Lastname)
					.append("email", email)
					.append("phone", phone)
					.append("address", address)
					.append("orders", orders)
					.append("LoyaltyPoints", LoyaltyPoints);
			database.getCollection("Customers").insertOne(document);
		}

		public static ArrayList<String> getCustomerId() {
			ArrayList<String> customerId = new ArrayList<>();
			for (Document document : database.getCollection("Customer").find()) {
				customerId.add(document.getString("CustomerId"));
			}
			return customerId;
		}

		public static String getCustomerId(String firstname, String lastname) {
			Document document = database.getCollection("Customer").find(new Document("Firstname", firstname).append("Lastname", lastname)).first();
			return document.getString("CustomerId");
		}

		public static String getCustomerId(String phonenumber) {
			Document document = database.getCollection("Customer").find(new Document("phone", phonenumber)).first();
			return document.getString("CustomerId");
		}

		public static String getFirstname(String customerId) {
			Document document = database.getCollection("Customer").find(new Document("CustomerId", customerId)).first();
			return document.getString("Firstname");
		}

		public static String getLastname(String customerId) {
			Document document = database.getCollection("Customer").find(new Document("CustomerId", customerId)).first();
			return document.getString("Lastname");
		}

		public static String getFullName(String client) {
			Document document = database.getCollection("Customer").find(new Document("CustomerId", client)).first();
			return document.getString("Firstname") + " " + document.getString("Lastname");
		}

		public static String getEmail(String customerId) {
			Document document = database.getCollection("Customer").find(new Document("CustomerId", customerId)).first();
			return document.getString("email");
		}

		public static String getPhone(String customerId) {
			Document document = database.getCollection("Customer").find(new Document("CustomerId", customerId)).first();
			return document.getString("phone");
		}

		public static ArrayList<String> getAddress(String customerId) {
			Document document = database.getCollection("Customer").find(new Document("CustomerId", customerId)).first();
			return (ArrayList<String>) document.get("address");
		}

		public static ArrayList<String> getCustomerNames() {
			ArrayList<String> customerNames = new ArrayList<>();
			for (Document document : database.getCollection("Customer").find()) {
				customerNames.add(document.getString("Firstname") + " " + document.getString("Lastname"));
			}
			return customerNames;
		}

		public static ArrayList<String> getCustomerPhoneNumbers() {
			ArrayList<String> customerPhoneNumbers = new ArrayList<>();
			for (Document document : database.getCollection("Customer").find()) {
				customerPhoneNumbers.add(document.getString("phone"));
			}
			return customerPhoneNumbers;
		}

		public static void updateEmail(String customerId, String email) {
			database.getCollection("Customer").updateOne(new Document("email", email), new Document("$set", new Document("email", email)));
		}

		public static void updatePhone(String customerId, String phone) {
			database.getCollection("Customer").updateOne(new Document("phone", phone), new Document("$set", new Document("phone", phone)));
		}

		public static void AddAddress(String customerId, String address) {
			database.getCollection("Customer").updateOne(new Document("address", address), new Document("$push", new Document("address", address)));
		}

		public static void AddOrder(String customerId, String order) {
			database.getCollection("Customer").updateOne(new Document("orders", order), new Document("$push", new Document("orders", order)));
		}

		public static void IncreaseLoyaltyPoints(String customerId, Double points) {
			database.getCollection("Customer").updateOne(new Document("LoyaltyPoints", points), new Document("$inc", new Document("LoyaltyPoints", points)));
		}

		public static void DecreaseLoyaltyPoints(String customerId, Double points) {
			database.getCollection("Customer").updateOne(new Document("LoyaltyPoints", points), new Document("$inc", new Document("LoyaltyPoints", -points)));
		}

		public static void DeleteCustomer(String customerId) {
			database.getCollection("Customer").deleteOne(new Document("CustomerId", customerId));
		}

		public static void DeleteAddress(String customerId, String address) {
			database.getCollection("Customer").updateOne(new Document("address", address), new Document("$pull", new Document("address", address)));
		}

		public static void DeleteOrder(String customerId, String order) {
			database.getCollection("Customer").updateOne(new Document("orders", order), new Document("$pull", new Document("orders", order)));
		}
	}
}
