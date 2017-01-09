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
	
	
}
