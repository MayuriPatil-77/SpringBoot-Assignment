package com.app.reward.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.app.reward.model.Reward;

public class RewardServiceTest {

	private final RewardService rewardService = new RewardService();

	 @Test
	    void testCalculateRewardsReturnsSummary() {
	        List<Reward> rewards = rewardService.calculateRewards();
	        assertFalse(rewards.isEmpty(), "Rewards list should not be empty");
	        assertTrue(rewards.stream().anyMatch(r -> r.getCustomerId().equals("Cust1")),
	                "Cust1 should be in the rewards summary");
	    }
}

