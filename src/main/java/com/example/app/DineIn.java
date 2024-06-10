package com.example.app;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;

public class DineIn extends Order{

	private int TableNumber;

	public DineIn(String CustomerId, String EmployeeId, Date DateOrdered, Boolean OrderStatus,
	                ArrayList<String> OrderItems, double Total, String PaymentMethodId, Date DateCompleted,
	                double Discount, double Tax, double LoyaltyPoints, String SpecialRequest,
	                DollarRate UsedCurrency, int TableNumber) {
		super(CustomerId, EmployeeId, DateOrdered, OrderStatus, OrderItems, Total, PaymentMethodId,
				DateCompleted, Discount, Tax, "DineIn", LoyaltyPoints, SpecialRequest, UsedCurrency);
		setTableNumber(TableNumber);
	}

	private void setTableNumber(int TableNumber) {
		this.TableNumber = TableNumber;
	}

	public int getTableNumber() {
		return TableNumber;
	}

	public void AddDineInToDb() {
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
					.append("TableNumber", getTableNumber());

			database.getCollection("Orders").insertOne(document);
		}
	}
}