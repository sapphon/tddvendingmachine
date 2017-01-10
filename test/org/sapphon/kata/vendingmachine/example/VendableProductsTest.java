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

}
