package org.sapphon.kata.vendingmachine.example;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

public class CoinTest {
	
	private static float NOT_BEING_TESTED = -1.01f;

	@Test
	public void testCoinsRememberTheirWeight() {
		float weightToExpect = 0.07f;
		Coin underTest = new Coin(weightToExpect, NOT_BEING_TESTED);
		assertEquals(weightToExpect, underTest.getWeightInGrams(), Float.MIN_VALUE);
	}
	
	@Test
	public void testCoinsRememberTheirWeight_Random() {
		float weightToExpect = new Random().nextFloat();
		Coin underTest = new Coin(weightToExpect, NOT_BEING_TESTED);
		assertEquals(weightToExpect, underTest.getWeightInGrams(), Float.MIN_VALUE);
	}
	
	@Test
	public void testCoinsRememberTheirSize() {
		float sizeToExpect = 110.110110f;
		Coin underTest = new Coin(NOT_BEING_TESTED, sizeToExpect);
		assertEquals(sizeToExpect, underTest.getSizeInMillimeters(), Float.MIN_VALUE);
	}
}
