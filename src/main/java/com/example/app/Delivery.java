package com.example.app;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;

public class Delivery extends Order{
	private Adress adress;

	public Delivery(String CustomerId, String EmployeeId, Date DateOrdered, Boolean OrderStatus,
	                ArrayList<String> OrderItems, double Total, String PaymentMethodId, Date DateCompleted,
	                double Discount, double Tax, double LoyaltyPoints, String SpecialRequest,
	                DollarRate UsedCurrency, Adress adress) {
		super(CustomerId, EmployeeId, DateOrdered, OrderStatus, OrderItems, Total, PaymentMethodId,
				DateCompleted, Discount, Tax, "Delivery", LoyaltyPoints, SpecialRequest, UsedCurrency);
		setAdress(adress);
	}

	private void setAdress(Adress adress) {
		this.adress = adress;
	}

	public Adress getAdress() {
		return adress;
	}

	public void AddDeliveryToDb() {
		OrderDatabase orderDatabase = new OrderDatabase();
		orderDatabase.AddOrder();
	}

	private class OrderDatabase{
		private static MongoClient mongoClient = DatabaseConnection.getmongoClient();
		private static MongoDatabase database = DatabaseConnection.getdatabaseName();

		public OrderDatabase() {
			// Add to database
			database = mongoClient.getDatabase("TSFPos");
		}

		public void AddOrder() {
			String orderId = getOrderId();

			// Add to database
			Document document = new Document("OrderId", orderId)
					.append("CustomerId", getCustomerId())
					.append("EmployeeId", getEmployeeId())
					.append("DateOrdered", getDateOrdered())
					.append("OrderStatus", getOrderStatus())
					.append("OrderItems", getOrderItems())
					.append("Total", getTotal())
					.append("PaymentMethodId", getPaymentMethodId())
					.append("DateCompleted", getDateCompleted())
					.append("Discount", getDiscount())
					.append("Tax", getTax())
					.append("OrderType", getOrderType())
					.append("LoyaltyPoints", getLoyaltyPoints())
					.append("Adress", getAdress());

			database.getCollection("Orders").insertOne(document);
		}
	}
}