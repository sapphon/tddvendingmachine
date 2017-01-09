package org.sapphon.kata.vendingmachine.example;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

public class CoinTest {

	@Test
	public void testCoinsRememberTheirWeight() {
		float weightToExpect = 0.07f;
		Coin underTest = new Coin(weightToExpect);
		assertEquals(weightToExpect, underTest.getWeightInGrams(), Float.MIN_VALUE);
	}
}
