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
	
	@Test
	public void testCoinsRememberTheirSize_Random() {
		float sizeToExpect = new Random().nextFloat();
		Coin underTest = new Coin(NOT_BEING_TESTED, sizeToExpect);
		assertEquals(sizeToExpect, underTest.getSizeInMillimeters(), Float.MIN_VALUE);
	}
	
	@Test
	public void testCoinsAreEqualToCoinsWithTheSameWeightAndSize() {
		float sizeToExpect = new Random().nextFloat();
		float weightToExpect = new Random().nextFloat();
		Coin underTest = new Coin(weightToExpect, sizeToExpect);
		assertEquals(underTest, new Coin(weightToExpect, sizeToExpect));
	}
	
	@Test
	public void testCoinsAreNOTEqualToThingsThatAreNotCoins(){
		//I had to put the actual before the expectation here because of how "equals" works in Java.  I'd be testing the Integer's equals method otherwise!
		Coin underTest = new Coin(NOT_BEING_TESTED, NOT_BEING_TESTED);
		assertNotEquals(underTest, new Integer(1));
	}
	
	@Test
	public void testCoinsAreNOTEqualToCoinsWithOtherWeights(){
		//I had to put the actual before the expectation here because of how "equals" works in Java.  I'd be testing the Integer's equals method otherwise!
		Coin underTest = new Coin(0.01f, NOT_BEING_TESTED);
		assertNotEquals(underTest, new Coin(0.02f, NOT_BEING_TESTED));
	}
}
