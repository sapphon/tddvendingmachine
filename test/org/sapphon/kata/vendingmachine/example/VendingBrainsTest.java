package org.sapphon.kata.vendingmachine.example;

import static org.junit.Assert.*;

import org.junit.Test;

public class VendingBrainsTest {

	@Test
	public void testVendingMachineDisplaysExactChangeOnlyAfterConstruction() {
		VendingBrains underTest = new VendingBrains();
		assertEquals("EXACT CHANGE ONLY", underTest.readDisplay());
	}

}
