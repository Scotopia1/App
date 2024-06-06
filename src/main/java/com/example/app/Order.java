package com.example.app;

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
	private Date DateCompleted;
	private double Discount;
	private double Tax;
	private String OrderType;
	private double LoyaltyPoints;
	private String SpecialRequest;
	private DollarRate UsedCurrency;
	
	public Order(String CustomerId, String EmployeeId, Date DateOrdered, Boolean OrderStatus,
	             ArrayList<String> OrderItems, double Total, String PaymentMethodId, Date DateCompleted,
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
}