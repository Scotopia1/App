package com.example.app;

import java.util.ArrayList;
import java.util.Date;

public class Takeaway extends Order{

	public Takeaway(String CustomerId, String EmployeeId, Date DateOrdered, Boolean OrderStatus,
	                ArrayList<String> OrderItems, double Total, String PaymentMethodId, Date DateCompleted,
	                double Discount, double Tax, double LoyaltyPoints, String SpecialRequest,
	                DollarRate UsedCurrency) {
		super(CustomerId, EmployeeId, DateOrdered, OrderStatus, OrderItems, Total, PaymentMethodId,
				DateCompleted, Discount, Tax, "Takeaway", LoyaltyPoints, SpecialRequest, UsedCurrency);
	}
}
