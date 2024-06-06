package com.example.app;

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
}
