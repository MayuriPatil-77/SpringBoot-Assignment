package com.app.reward.Model;

import java.time.YearMonth;
import java.util.Map;

public class Reward {
	private String customerId;
	private Map<YearMonth, Integer> monthlyPoints;
	private int totalPoints;

	public Reward(String customerId, Map<YearMonth, Integer> monthlyPoints, int totalPoints) {
		super();
		this.customerId = customerId;
		this.monthlyPoints = monthlyPoints;
        this.totalPoints = monthlyPoints.values().stream().mapToInt(Integer::intValue).sum();
        }

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public Map<YearMonth, Integer> getMonthlyPoints() {
		return monthlyPoints;
	}

	public void setMonthlyPoints(Map<YearMonth, Integer> monthlyPoints) {
		this.monthlyPoints = monthlyPoints;
	}

	public int getTotalPoints() {
		return totalPoints;
	}

	public void setTotalPoints(int totalPoints) {
		this.totalPoints = totalPoints;
	}

}
