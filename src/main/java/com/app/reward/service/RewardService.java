package com.app.reward.service;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.app.reward.Model.Customer;
import com.app.reward.Model.Reward;
import com.app.reward.exception.InvalidTransactionException;

/**
 * Provides reward calculation logic for customer transactions.
 *
 * <p>
 * Rules:
 * <ul>
 * <li>Amount > 100 → 2 points per dollar over 100 + 50 points for $50–$100
 * range.</li>
 * <li>Amount between 51–100 → 1 point per dollar over 50.</li>
 * <li>Amount ≤ 50 → 0 points.</li>
 * </ul>
 * </p>
 */

@Service
public class RewardService {

	/**
	 * Hardcoded list of sample customer transactions. Each transaction contains
	 * customer ID, transaction date, and amount.
	 */

	private final List<Customer> customers = Arrays.asList(
			new Customer("Cust1", java.time.LocalDate.of(2026, 3, 15), 120),
			new Customer("Cust1", java.time.LocalDate.of(2026, 3, 20), 75),
			new Customer("Cust2", java.time.LocalDate.of(2026, 4, 10), 200),
			new Customer("Cust2", java.time.LocalDate.of(2026, 5, 5), 45), // invalid case
			new Customer("Cust3", java.time.LocalDate.of(2026, 5, 18), 95));

	/**
	 * Calculates monthly and total reward points for all customers.
	 *
	 * @return list of {@link Reward} objects containing monthly and total points
	 * @throws InvalidTransactionException if any transaction amount is zero or
	 *                                     negative
	 */

	public List<Reward> calculateRewards() {
		Map<String, Map<YearMonth, Integer>> customerRewards = new HashMap<>();

		for (Customer ct : customers) {
			if (ct.getAmount() <= 0) {
				throw new InvalidTransactionException("Transaction amount must be positive");
			}

			int points = calculatePoints(ct.getAmount());
			YearMonth ym = YearMonth.from(ct.getDate());

			Map<YearMonth, Integer> monthlyPoints = customerRewards.computeIfAbsent(ct.getCustomerId(),
					k -> new HashMap<>());

			// Add points for that month
			monthlyPoints.merge(ym, points, Integer::sum);
		}

		List<Reward> summaries = new ArrayList<>();
		for (Map.Entry<String, Map<YearMonth, Integer>> entry : customerRewards.entrySet()) {
			int totalPoints = entry.getValue().values().stream().mapToInt(Integer::intValue).sum();
			summaries.add(new Reward(entry.getKey(), entry.getValue(), totalPoints));
		}
		return summaries;
	}

	/**
	 * Calculates reward points for a single transaction.
	 *
	 * @param amount transaction amount
	 * @return reward points earned
	 */

	private int calculatePoints(double amount) {
		int points = 0;
		if (amount > 100) {
			points += (int) ((amount - 100) * 2);
			points += 50; // for $50–$100
		} else if (amount > 50) {
			points += (int) (amount - 50);
		}
		return points;

	}
}
