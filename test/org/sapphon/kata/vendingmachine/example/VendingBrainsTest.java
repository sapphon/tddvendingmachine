package org.sapphon.kata.vendingmachine.example;

import static org.junit.Assert.*;

import org.junit.Test;

public class VendingBrainsTest {

	@Test
	public void testVendingMachineDisplaysExactChangeOnlyAfterConstruction() {
		VendingBrains underTest = new VendingBrains();
		assertEquals("EXACT CHANGE ONLY", underTest.readDisplay());
	}
	
	@Test
	public void testVendingMachineDisplaysInsertCoinAfterConstruction_IfItStartsWithADimeAndANickel() {
		//Assumption: the machine will never need to make more than fifteen cents' change based on the items' pricing as it is now
		VendingBrains underTest = new VendingBrains();
		underTest.addChangeToBank(AcceptableCoins.DIME, 1);
		underTest.addChangeToBank(AcceptableCoins.NICKEL, 1);
		assertEquals("INSERT COIN", underTest.readDisplay());
	}

}
