package com.example.app;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;

public class Order {
	private String OrderId;
	private String CustomerId;
	private String EmployeeId;
	private Date DateOrdered;
	private Boolean OrderStatus;
	private ArrayList<String> OrderItems;
	private double Total;
	private String PaymentMethodId;
	private Boolean isPaid;
	private Date DateCompleted;
	private double Discount;
	private double Tax;
	private String OrderType;
	private double LoyaltyPoints;
	private String SpecialRequest;
	private DollarRate UsedCurrency;

	public Order(String CustomerId, String EmployeeId, Date DateOrdered, Boolean OrderStatus,
	             ArrayList<String> OrderItems, double Total, String PaymentMethodId, Boolean isPaid, Date DateCompleted,
	             double Discount, double Tax, String OrderType, double LoyaltyPoints, String SpecialRequest,
	             DollarRate UsedCurrency) {
		setOrderId();
		setCustomerId(CustomerId);
		setEmployeeId(EmployeeId);
		setDateOrdered(DateOrdered);
		setOrderStatus(OrderStatus);
		setOrderItems(OrderItems);
		setTotal(Total);
		setPaymentMethodId(PaymentMethodId);
		setDateCompleted(DateCompleted);
		setDiscount(Discount);
		setTax(Tax);
		setOrderType(OrderType);
		setLoyaltyPoints(LoyaltyPoints);
		setSpecialRequest(SpecialRequest);
		setUsedCurrency(UsedCurrency);
	}

	private void setOrderId() {
		Date date = new Date();
		this.OrderId = "O" + date.getTime();
	}

	private void setCustomerId(String CustomerId) {
		this.CustomerId = CustomerId;
	}

	private void setEmployeeId(String EmployeeId) {
		this.EmployeeId = EmployeeId;
	}

	private void setDateOrdered(Date DateOrdered) {
		this.DateOrdered = DateOrdered;
	}

	private void setOrderStatus(Boolean OrderStatus) {
		this.OrderStatus = OrderStatus;
	}

	private void setOrderItems(ArrayList<String> OrderItems) {
		this.OrderItems = OrderItems;
	}

	private void setTotal(double Total) {
		this.Total = Total;
	}

	private void setPaymentMethodId(String PaymentMethodId) {
		this.PaymentMethodId = PaymentMethodId;
	}

	private void setPaymentStatus(Boolean isPaid) {
		this.isPaid = isPaid;
	}

	private void setDateCompleted(Date DateCompleted) {
		this.DateCompleted = DateCompleted;
	}

	private void setDiscount(double Discount) {
		this.Discount = Discount;
	}

	private void setTax(double Tax) {
		this.Tax = Tax;
	}

	private void setOrderType(String OrderType) {
		this.OrderType = OrderType;
	}

	private void setLoyaltyPoints(double LoyaltyPoints) {
		this.LoyaltyPoints = LoyaltyPoints;
	}

	private void setSpecialRequest(String SpecialRequest) {
		this.SpecialRequest = SpecialRequest;
	}

	private void setUsedCurrency(DollarRate UsedCurrency) {
		this.UsedCurrency = UsedCurrency;
	}

	protected String getOrderId() {
		return OrderId;
	}

	protected String getCustomerId() {
		return CustomerId;
	}

	protected String getEmployeeId() {
		return EmployeeId;
	}

	protected Date getDateOrdered() {
		return DateOrdered;
	}

	protected Boolean getOrderStatus() {
		return OrderStatus;
	}

	protected ArrayList<String> getOrderItems() {
		return OrderItems;
	}

	protected double getTotal() {
		return Total;
	}

	protected String getPaymentMethodId() {
		return PaymentMethodId;
	}

	protected Date getDateCompleted() {
		return DateCompleted;
	}

	protected double getDiscount() {
		return Discount;
	}

	protected double getTax() {
		return Tax;
	}

	protected String getOrderType() {
		return OrderType;
	}

	protected double getLoyaltyPoints() {
		return LoyaltyPoints;
	}

	protected String getSpecialRequest() {
		return SpecialRequest;
	}

	protected DollarRate getUsedCurrency() {
		return UsedCurrency;
	}

	public static ArrayList<String> getOrderIdList() {
		return OrderDatabase.getOrderId();
	}
	public static String getCustomerId(String OrderId) {
		return OrderDatabase.getCustomerId(OrderId);
	}

	public static String getEmployeeId(String OrderId) {
		return OrderDatabase.getEmployeeId(OrderId);
	}

	public static Date getDateOrdered(String OrderId) {
		return OrderDatabase.getDateOrdered(OrderId);
	}

	public static Boolean getOrderStatus(String OrderId) {
		return OrderDatabase.getOrderStatus(OrderId);
	}

	public static ArrayList<String> getOrderItems(String OrderId) {
		return OrderDatabase.getOrderItems(OrderId);
	}

	public static double getTotal(String OrderId) {
		return OrderDatabase.getTotal(OrderId);
	}

	public static String getPaymentMethodId(String OrderId) {
		return OrderDatabase.getPaymentMethodId(OrderId);
	}

	public static Date getDateCompleted(String OrderId) {
		return OrderDatabase.getDateCompleted(OrderId);
	}

	public static double getDiscount(String OrderId) {
		return OrderDatabase.getDiscount(OrderId);
	}

	public static double getTax(String OrderId) {
		return OrderDatabase.getTax(OrderId);
	}

	public static String getOrderType(String OrderId) {
		return OrderDatabase.getOrderType(OrderId);
	}

	public static double getLoyaltyPoints(String OrderId) {
		return OrderDatabase.getLoyaltyPoints(OrderId);
	}

	public static String getSpecialRequest(String OrderId) {
		return OrderDatabase.getSpecialRequest(OrderId);
	}

	public static String getUsedCurrency(String OrderId) {
		return OrderDatabase.getUsedCurrency(OrderId);
	}

	private class OrderDatabase {
		private static MongoClient mongoClient = DatabaseConnection.getmongoClient();
		private static MongoDatabase database = DatabaseConnection.getdatabaseName();

		public OrderDatabase() {
			// Add to database
			database = mongoClient.getDatabase("TSFPos");
		}

		public static ArrayList<String> getOrderId() {
			ArrayList<String> orderId = new ArrayList<>();
			for (Document document : database.getCollection("Order").find()) {
				orderId.add(document.getString("OrderId"));
			}
			return orderId;
		}

		public static String getCustomerId(String orderId) {
			Document document = database.getCollection("Order").find(new Document("OrderId", orderId)).first();
			if (document != null) {
				return document.getString("CustomerId");
			} else {
				return null;
			}
		}

		public static String getEmployeeId(String orderId) {
			Document document = database.getCollection("Order").find(new Document("OrderId", orderId)).first();
			if (document != null) {
				return document.getString("EmployeeId");
			} else {
				return null;
			}
		}

		public static Date getDateOrdered(String orderId) {
			Document document = database.getCollection("Order").find(new Document("OrderId", orderId)).first();
			if (document != null) {
				return document.getDate("DateOrdered");
			} else {
				return null;
			}
		}

		public static Boolean getOrderStatus(String orderId) {
			Document document = database.getCollection("Order").find(new Document("OrderId", orderId)).first();
			if (document != null) {
				return document.getBoolean("OrderStatus");
			} else {
				return null;
			}
		}

		public static ArrayList<String> getOrderItems(String orderId) {
			Document document = database.getCollection("Order").find(new Document("OrderId", orderId)).first();
			if (document != null) {
				return (ArrayList<String>) document.get("OrderItems");
			} else {
				return null;
			}
		}

		public static double getTotal(String orderId) {
			Document document = database.getCollection("Order").find(new Document("OrderId", orderId)).first();
			if (document != null) {
				return document.getDouble("Total");
			} else {
				return 0.0;
			}
		}

		public static String getPaymentMethodId(String orderId) {
			Document document = database.getCollection("Order").find(new Document("OrderId", orderId)).first();
			if (document != null) {
				return document.getString("PaymentMethodId");
			} else {
				return null;
			}
		}

		public static Date getDateCompleted(String orderId) {
			Document document = database.getCollection("Order").find(new Document("OrderId", orderId)).first();
			if (document != null) {
				return document.getDate("DateCompleted");
			} else {
				return null;
			}
		}

		public static double getDiscount(String orderId) {
			Document document = database.getCollection("Order").find(new Document("OrderId", orderId)).first();
			if (document != null) {
				return document.getDouble("Discount");
			} else {
				return 0.0;
			}
		}

		public static double getTax(String orderId) {
			Document document = database.getCollection("Order").find(new Document("OrderId", orderId)).first();
			if (document != null) {
				return document.getDouble("Tax");
			} else {
				return 0.0;
			}
		}

		public static String getOrderType(String orderId) {
			Document document = database.getCollection("Order").find(new Document("OrderId", orderId)).first();
			if (document != null) {
				return document.getString("OrderType");
			} else {
				return null;
			}
		}

		public static double getLoyaltyPoints(String orderId) {
			Document document = database.getCollection("Order").find(new Document("OrderId", orderId)).first();
			if (document != null) {
				return document.getDouble("LoyaltyPoints");
			} else {
				return 0.0;
			}
		}

		public static String getSpecialRequest(String orderId) {
			Document document = database.getCollection("Order").find(new Document("OrderId", orderId)).first();
			if (document != null) {
				return document.getString("SpecialRequest");
			} else {
				return null;
			}
		}

		public static String getUsedCurrency(String orderId) {
			Document document = database.getCollection("Order").find(new Document("OrderId", orderId)).first();
			if (document != null) {
				return document.getString("UsedCurrency");
			} else {
				return null;
			}
		}

		public static void updateOrderStatus(String orderId, Boolean orderStatus) {
			database.getCollection("Order").updateOne(new Document("OrderId", orderId), new Document("$set", new Document("OrderStatus", orderStatus)));
		}

		public static void updateDateCompleted(String orderId, Date dateCompleted) {
			database.getCollection("Order").updateOne(new Document("OrderId", orderId), new Document("$set", new Document("DateCompleted", dateCompleted)));
		}


	}
}