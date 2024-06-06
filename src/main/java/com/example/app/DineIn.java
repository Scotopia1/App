package com.example.app;

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
}
