package com.example.app;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class OrderItem {

	private String Name, orderItemId, MenuItemId, OrderId;

	private Double price, TotalPrice;

	private int Quantity;

	public OrderItem(String Name, String MenuItemId, String OrderId, Double price, int Quantity) {
		setName(Name);
		setMenuItemId(MenuItemId);
		setOrderId(OrderId);
		setPrice(price);
		setQuantity(Quantity);
		setTotalPrice();
		AddToDatabase();
	}

	private void setName(String Name) {
		this.Name = Name;
	}

	private void setMenuItemId(String MenuItemId) {
		this.MenuItemId = MenuItemId;
	}

	private void setOrderId(String OrderId) {
		this.OrderId = OrderId;
	}

	private void setPrice(Double price) {
		this.price = price;
	}

	private void setQuantity(int Quantity) {
		this.Quantity = Quantity;
	}

	private void setTotalPrice() {
		this.TotalPrice = this.price * this.Quantity;
	}

	private void AddToDatabase() {
		// Add to database
		OrderItemDatabase orderItemDatabase = new OrderItemDatabase();
		orderItemDatabase.AddOrderItem();
	}

	public static String getOrderId(String orderItemId) {
		return OrderItemDatabase.getOrderId(orderItemId);
	}

	public static String getMenuItemId(String orderItemId) {
		return OrderItemDatabase.getMenuItemId(orderItemId);
	}

	public static String getName(String orderItemId) {
		return OrderItemDatabase.getName(orderItemId);
	}

	public static Double getPrice(String orderItemId) {
		return OrderItemDatabase.getPrice(orderItemId);
	}

	public static int getQuantity(String orderItemId) {
		return OrderItemDatabase.getQuantity(orderItemId);
	}

	public static Double getTotalPrice(String orderItemId) {
		return OrderItemDatabase.getTotalPrice(orderItemId);
	}

	public static void setQuantityDb(String orderItemId, int Quantity) {
		OrderItemDatabase.setQuantity(orderItemId, Quantity);
	}

	public static void setTotalPriceDb(String orderItemId, Double TotalPrice) {
		OrderItemDatabase.setTotalPrice(orderItemId, TotalPrice);
	}

	public static void DeleteOrderItem(String orderItemId) {
		OrderItemDatabase.DeleteOrderItem(orderItemId);
	}


	private class OrderItemDatabase {
		// Database connection
		// Database connection
		private static MongoClient mongoClient = DatabaseConnection.getmongoClient();
		private static MongoDatabase database = DatabaseConnection.getdatabaseName();

		public OrderItemDatabase() {
			database = mongoClient.getDatabase("TSFPos");
			System.out.println("Database connection successful");
		}

		private void AddOrderItem() {
			Document document = new Document("name", Name)
					.append("menuItemId", MenuItemId)
					.append("orderId", OrderId)
					.append("price", price)
					.append("quantity", Quantity)
					.append("totalPrice", TotalPrice);
			database.getCollection("OrderItems").insertOne(document);
		}

		private static String getOrderId(String orderItemId) {
			Document document = database.getCollection("OrderItems").find(new Document("orderItemId", orderItemId)).first();
			return document.getString("orderId");
		}

		private static String getMenuItemId(String orderItemId) {
			Document document = database.getCollection("OrderItems").find(new Document("orderItemId", orderItemId)).first();
			return document.getString("menuItemId");
		}

		private static String getName(String orderItemId) {
			Document document = database.getCollection("OrderItems").find(new Document("orderItemId", orderItemId)).first();
			return document.getString("name");
		}

		private static Double getPrice(String orderItemId) {
			Document document = database.getCollection("OrderItems").find(new Document("orderItemId", orderItemId)).first();
			return document.getDouble("price");
		}

		private static int getQuantity(String orderItemId) {
			Document document = database.getCollection("OrderItems").find(new Document("orderItemId", orderItemId)).first();
			return document.getInteger("quantity");
		}

		private static Double getTotalPrice(String orderItemId) {
			Document document = database.getCollection("OrderItems").find(new Document("orderItemId", orderItemId)).first();
			return document.getDouble("totalPrice");
		}

		private static void setQuantity(String orderItemId, int Quantity) {
			Document document = database.getCollection("OrderItems").find(new Document("orderItemId", orderItemId)).first();
			database.getCollection("OrderItems").updateOne(document, new Document("$set", new Document("quantity", Quantity)));
		}

		private static void setTotalPrice(String orderItemId, Double TotalPrice) {
			Document document = database.getCollection("OrderItems").find(new Document("orderItemId", orderItemId)).first();
			database.getCollection("OrderItems").updateOne(document, new Document("$set", new Document("totalPrice", TotalPrice)));
		}

		private static void DeleteOrderItem(String orderItemId) {
			Document document = database.getCollection("OrderItems").find(new Document("orderItemId", orderItemId)).first();
			database.getCollection("OrderItems").deleteOne(document);
		}
	}
}
