package com.app.reward.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.YearMonth;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.app.reward.Model.Reward;
import com.app.reward.service.RewardService;

/**
 * Unit test for RewardController using Mockito.
 */
@ExtendWith(MockitoExtension.class)
class RewardControllerTest {

    @Mock
    private RewardService rewardService;   // mock dependency

    @InjectMocks
    private RewardController rewardController; // controller under test

    @Test
    void testGetRewardsReturnsMockedData() {
        // Arrange: mock service response
        Reward reward = new Reward("Cust1",
                Map.of(YearMonth.of(2026, 3), 90),
                90);

        when(rewardService.calculateRewards()).thenReturn(Collections.singletonList(reward));

        // Act: call controller
        List<Reward> result = rewardController.getRewards();

        // Assert: verify controller returns mocked list
        assertEquals(1, result.size());
        assertEquals("Cust1", result.get(0).getCustomerId());
        assertEquals(90, result.get(0).getTotalPoints());
    }
}