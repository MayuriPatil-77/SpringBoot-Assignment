package com.app.reward.model;

import java.time.LocalDate;

public class Customer {
	private String customerId;
	private LocalDate date;
	private double amount;

	public Customer(String customerId, LocalDate date, double amount) {
		super();
		this.customerId = customerId;
		this.date = date;
		this.amount = amount;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}
