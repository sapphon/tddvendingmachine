package org.sapphon.kata.vendingmachine.example;

import static org.junit.Assert.*;

import org.junit.Test;

public class VendableProductsTest {

	@Test
	public void testThereAreThreeVendableProducts() {
		assertEquals(3, VendableProducts.values().length);
	}
	
	@Test
	public void testTheFirstProductIsCola() {
		assertEquals("COLA", VendableProducts.values()[0].name());
	}

	@Test
	public void testTheSecondProductIsChips() {
		assertEquals("CHIPS", VendableProducts.values()[1].name());
	}
	
	@Test
	public void testTheThirdProductIsCandy() {
		assertEquals("CANDY", VendableProducts.values()[2].name());
	}
	
	@Test
	public void testColaCostsABuck() {
		assertEquals(1.0f, VendableProducts.COLA.getCostInDollars(), Float.MIN_VALUE);
	}
	
}
