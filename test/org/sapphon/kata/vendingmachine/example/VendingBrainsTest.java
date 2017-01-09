package org.sapphon.kata.vendingmachine.example;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

public class VendingBrainsTest {

	@Test
	public void testVendingMachineDisplaysExactChangeOnlyAfterConstruction_IfBankIsEmpty() {
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
	
	@Test
	public void testVendingMachineProperlyReportsContentsOfItsBank_SimpleCase() {
		VendingBrains underTest = new VendingBrains();
		underTest.addChangeToBank(AcceptableCoins.DIME, 1);
		underTest.addChangeToBank(AcceptableCoins.NICKEL, 1);
		assertEquals(1, underTest.getNumberOfCoinsInBank(AcceptableCoins.DIME));
		assertEquals(1, underTest.getNumberOfCoinsInBank(AcceptableCoins.NICKEL));
		assertEquals(0, underTest.getNumberOfCoinsInBank(AcceptableCoins.QUARTER));
	}
	
	@Test
	public void testVendingMachineProperlyReportsContentsOfItsBank_WithRandomness() {
		VendingBrains underTest = new VendingBrains();
		int expectedDimes = new Random().nextInt(100);
		int expectedNickels = new Random().nextInt(100);
		int expectedQuarters = new Random().nextInt(100);
		underTest.addChangeToBank(AcceptableCoins.DIME, expectedDimes);
		underTest.addChangeToBank(AcceptableCoins.NICKEL, expectedNickels);
		underTest.addChangeToBank(AcceptableCoins.QUARTER, expectedQuarters);
		assertEquals(expectedDimes, underTest.getNumberOfCoinsInBank(AcceptableCoins.DIME));
		assertEquals(expectedNickels, underTest.getNumberOfCoinsInBank(AcceptableCoins.NICKEL));
		assertEquals(expectedQuarters, underTest.getNumberOfCoinsInBank(AcceptableCoins.QUARTER));
	}
	
	@Test
	public void testVendingMachineDisplaysInsertCoinAfterConstruction_IfItStartsWithThreeNickels() {
		VendingBrains underTest = new VendingBrains();
		underTest.addChangeToBank(AcceptableCoins.NICKEL, 3);
		assertEquals("INSERT COIN", underTest.readDisplay());
	}

	@Test
	public void testVendingMachineDisplaysExactChangeOnlyAfterConstruction_IfBankContainsOnlyOneNickel() {
		VendingBrains underTest = new VendingBrains();
		underTest.addChangeToBank(AcceptableCoins.NICKEL,  1);
		assertEquals("EXACT CHANGE ONLY", underTest.readDisplay());
	}
	
	@Test
	public void testVendingMachineWillRejectANonAcceptableCoin_AndGiveItBack() {
		VendingBrains underTest = new VendingBrains();
		Coin expectedReturnedCoin = new Coin(1.0f, 1.0f);
		underTest.insertCoin(expectedReturnedCoin);
		assertEquals("EXACT CHANGE ONLY", underTest.readDisplay());
		assertEquals(1, underTest.getCoinReturnContents().size());
		assertSame(expectedReturnedCoin, underTest.getCoinReturnContents().get(0));
	}
	

	
	

}
