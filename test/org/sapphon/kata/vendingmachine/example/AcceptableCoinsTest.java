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
		AcceptableCoins quarter = AcceptableCoins.valueOf("QUARTER");
		assertEquals(5.67f, quarter.getWeightInGrams(), Float.MIN_VALUE);
		assertEquals(24.26f, quarter.getSizeInMillimeters(), Float.MIN_VALUE);
		assertEquals(.25f, quarter.getMonetaryValueInDollars(), Float.MIN_VALUE);
	}
	
	@Test
	public void testDimeHasCanonicalWeightAndSizeAndIsWorthTenCents() {
		AcceptableCoins dime = AcceptableCoins.valueOf("DIME");
		assertEquals(2.268f, dime.getWeightInGrams(), Float.MIN_VALUE);
		assertEquals(17.91f, dime.getSizeInMillimeters(), Float.MIN_VALUE);
		assertEquals(.1f, dime.getMonetaryValueInDollars(), Float.MIN_VALUE);
	}
	
	@Test
	public void testNickelHasCanonicalWeightAndSizeAndIsWorthFiveCents() {
		AcceptableCoins nickel = AcceptableCoins.valueOf("NICKEL");
		assertEquals(5f, nickel.getWeightInGrams(), Float.MIN_VALUE);
		assertEquals(21.21f, nickel.getSizeInMillimeters(), Float.MIN_VALUE);
		assertEquals(.05f, nickel.getMonetaryValueInDollars(), Float.MIN_VALUE);
	}
}
