package org.sapphon.kata.vendingmachine.example;
import static org.junit.Assert.*;
import org.junit.Test;

public class AcceptableCoinsTest {

	@Test
	public void testThereAreThreeAcceptableCoins() {
		assertEquals(3, AcceptableCoins.values().length);
	}
	
	@Test
	public void testThatOneAcceptableCoinIsNamedQuarter() {
		assertEquals("QUARTER", AcceptableCoins.values()[0].name());
	}
	
	@Test
	public void testThatOneAcceptableCoinIsNamedDime() {
		assertEquals("DIME", AcceptableCoins.values()[1].name());
	}
	
	@Test
	public void testThatOneAcceptableCoinIsNamedNickel() {
		assertEquals("NICKEL", AcceptableCoins.values()[2].name());
	}
	
	@Test
	public void testQuarterHasCanonicalWeightAndSizeAndIsWorthTwentyFiveCents() {
		assertEquals(5.67f, AcceptableCoins.valueOf("QUARTER").getWeightInGrams(), Float.MIN_VALUE);
		assertEquals(24.26f, AcceptableCoins.valueOf("QUARTER").getSizeInMillimeters(), Float.MIN_VALUE);
	}
	
	@Test
	public void testDimeHasCanonicalWeightAndSizeAndIsWorthTenCents() {
		assertEquals(2.268f, AcceptableCoins.valueOf("DIME").getWeightInGrams(), Float.MIN_VALUE);
		assertEquals(17.91f, AcceptableCoins.valueOf("DIME").getSizeInMillimeters(), Float.MIN_VALUE);
	}
	
	@Test
	public void testNickelHasCanonicalWeightAndSizeAndIsWorthFiveCents() {
		assertEquals(5f, AcceptableCoins.valueOf("NICKEL").getWeightInGrams(), Float.MIN_VALUE);
		assertEquals(21.21f, AcceptableCoins.valueOf("NICKEL").getSizeInMillimeters(), Float.MIN_VALUE);
	}
}
