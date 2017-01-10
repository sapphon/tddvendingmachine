package org.sapphon.kata.vendingmachine.example;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
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
	
	@Test
	public void testVendingMachineWillRejectNonAcceptableCoins_AndGiveThemBack() {
		VendingBrains underTest = new VendingBrains();
		Coin expectedReturnedCoin = new Coin(1.0f, 1.0f);
		Coin expectedReturnedCoin2 = new Coin(2.0f, 2.0f);
		Coin expectedReturnedCoin3 = new Coin(3.0f, 3.0f);
		underTest.insertCoin(expectedReturnedCoin);
		underTest.insertCoin(expectedReturnedCoin2);
		underTest.insertCoin(expectedReturnedCoin3);
		assertEquals("EXACT CHANGE ONLY", underTest.readDisplay());
		assertEquals(3, underTest.getCoinReturnContents().size());
		assertSame(expectedReturnedCoin, underTest.getCoinReturnContents().get(0));
		assertSame(expectedReturnedCoin2, underTest.getCoinReturnContents().get(1));
		assertSame(expectedReturnedCoin3, underTest.getCoinReturnContents().get(2));
	}
	
	@Test
	public void testVendingMachineWillAcceptANickel_AndDisplayItsValue() {
		VendingBrains underTest = new VendingBrains();
		underTest.insertCoin(new Coin(AcceptableCoins.NICKEL.getWeightInGrams(), AcceptableCoins.NICKEL.getSizeInMillimeters()));
		assertEquals("$0.05", underTest.readDisplay());
		assertEquals(0, underTest.getCoinReturnContents().size());
	}
	
	@Test
	public void testVendingMachineWillAcceptADime_AndDisplayItsValue() {
		VendingBrains underTest = new VendingBrains();
		underTest.insertCoin(new Coin(AcceptableCoins.DIME.getWeightInGrams(), AcceptableCoins.DIME.getSizeInMillimeters()));
		assertEquals("$0.10", underTest.readDisplay());
		assertEquals(0, underTest.getCoinReturnContents().size());
	}
	
	@Test
	public void testVendingMachineWillAcceptThreeDifferentCoins_AndDisplayTheirValue_ButRejectASlug() {
		VendingBrains underTest = new VendingBrains();
		underTest.insertCoin(new Coin(AcceptableCoins.DIME.getWeightInGrams(), AcceptableCoins.DIME.getSizeInMillimeters()));
		assertEquals("$0.10", underTest.readDisplay());
		assertEquals(0, underTest.getCoinReturnContents().size());
		underTest.insertCoin(new Coin(AcceptableCoins.NICKEL.getWeightInGrams(), AcceptableCoins.NICKEL.getSizeInMillimeters()));
		assertEquals("$0.15", underTest.readDisplay());
		assertEquals(0, underTest.getCoinReturnContents().size());
		Coin slugCoin = new Coin(100f, 0.001f);
		underTest.insertCoin(slugCoin);
		assertEquals("$0.15", underTest.readDisplay());
		assertEquals(1, underTest.getCoinReturnContents().size());
		assertSame(slugCoin, underTest.getCoinReturnContents().get(0));
		underTest.insertCoin(new Coin(AcceptableCoins.QUARTER.getWeightInGrams(), AcceptableCoins.QUARTER.getSizeInMillimeters()));
		assertEquals("$0.40", underTest.readDisplay());
		assertEquals(1, underTest.getCoinReturnContents().size());
	}
	
	@Test
	public void testUserCanRemoveCoinReturnContents() {
		VendingBrains underTest = new VendingBrains();
		assertEquals(0, underTest.getCoinReturnContents().size());
		Coin slugCoin = new Coin(100f, 0.001f);
		underTest.insertCoin(slugCoin);
		assertEquals("EXACT CHANGE ONLY", underTest.readDisplay());
		assertEquals(1, underTest.getCoinReturnContents().size());
		assertSame(slugCoin, underTest.getCoinReturnContents().get(0));
		underTest.takeCoinReturnContents();
		assertEquals("EXACT CHANGE ONLY", underTest.readDisplay());
		assertEquals(0, underTest.getCoinReturnContents().size());
	}
	
	@Test
	public void testProductInventoryStartsWithThreeTypesOfItem() {
		VendingBrains underTest = new VendingBrains();
		assertEquals(3, underTest.getProductInventory().keySet().size());
	}
	
	@Test
	public void testProductInventoryStartsWithZeroEachOfCandyChipsAndSoda() {
		VendingBrains underTest = new VendingBrains();
		assertArrayEquals(VendableProducts.values(), underTest.getProductInventory().keySet().toArray());
		Integer zero = 0;
		assertEquals(zero, underTest.getProductInventory().get(VendableProducts.COLA));
		assertEquals(zero, underTest.getProductInventory().get(VendableProducts.CANDY));
		assertEquals(zero, underTest.getProductInventory().get(VendableProducts.CHIPS));
	}
	
	@Test
	public void testProductInventoryCanBeAddedToAndRemembered() {
		VendingBrains underTest = new VendingBrains();
		assertArrayEquals(VendableProducts.values(), underTest.getProductInventory().keySet().toArray());
		Integer zero = 0;
		Integer five = 5;
		assertEquals(zero, underTest.getProductInventory().get(VendableProducts.COLA));
		assertEquals(zero, underTest.getProductInventory().get(VendableProducts.CANDY));
		assertEquals(zero, underTest.getProductInventory().get(VendableProducts.CHIPS));
		underTest.addProductToInventory(VendableProducts.CHIPS, 5);
		assertEquals(five, underTest.getProductInventory().get(VendableProducts.CHIPS));
		assertEquals(zero, underTest.getProductInventory().get(VendableProducts.CANDY));
		assertEquals(zero, underTest.getProductInventory().get(VendableProducts.COLA));
	}
	
	@Test
	public void testProductInventoryCanBeAddedToMultipleTimes() {
		VendingBrains underTest = new VendingBrains();
		assertArrayEquals(VendableProducts.values(), underTest.getProductInventory().keySet().toArray());
		Integer zero = 0;
		Integer five = 5;
		Integer two = 2;
		Integer seven = 7;
		assertEquals(zero, underTest.getProductInventory().get(VendableProducts.COLA));
		assertEquals(zero, underTest.getProductInventory().get(VendableProducts.CANDY));
		assertEquals(zero, underTest.getProductInventory().get(VendableProducts.CHIPS));
		underTest.addProductToInventory(VendableProducts.CHIPS, 5);
		assertEquals(five, underTest.getProductInventory().get(VendableProducts.CHIPS));
		assertEquals(zero, underTest.getProductInventory().get(VendableProducts.CANDY));
		assertEquals(zero, underTest.getProductInventory().get(VendableProducts.COLA));
		underTest.addProductToInventory(VendableProducts.CANDY, 2);
		assertEquals(five, underTest.getProductInventory().get(VendableProducts.CHIPS));
		assertEquals(two, underTest.getProductInventory().get(VendableProducts.CANDY));
		assertEquals(zero, underTest.getProductInventory().get(VendableProducts.COLA));
		underTest.addProductToInventory(VendableProducts.CHIPS, 2);
		assertEquals(seven, underTest.getProductInventory().get(VendableProducts.CHIPS));
		assertEquals(two, underTest.getProductInventory().get(VendableProducts.CANDY));
		assertEquals(zero, underTest.getProductInventory().get(VendableProducts.COLA));
	}
	
	@Test
	public void testCoinReturn_WithNoCoinsInserted() {
		VendingBrains underTest = new VendingBrains();
		List<Coin> expectedCoinReturnContents = new ArrayList<Coin>();
		underTest.pushCoinReturn();
		assertEquals(expectedCoinReturnContents, underTest.getCoinReturnContents());
	}
	
	@Test
	public void testCoinReturnContainsAllInvalidCoinsInserted_AndSomeValidOnesOnceYouPushCoinReturn() {
		VendingBrains underTest = new VendingBrains();
		List<Coin> expectedCoinReturnContents = new ArrayList<Coin>();
		Coin bigSlug = new Coin(100f, 100f);
		Coin littleSlug = new Coin(.001f, .001f);
		Coin validQuarter = new Coin(AcceptableCoins.QUARTER.getWeightInGrams(), AcceptableCoins.QUARTER.getSizeInMillimeters());
		Coin validDime = new Coin(AcceptableCoins.DIME.getWeightInGrams(), AcceptableCoins.DIME.getSizeInMillimeters());
		
		underTest.insertCoin(bigSlug);
		underTest.insertCoin(littleSlug);
		underTest.insertCoin(validQuarter);
		underTest.insertCoin(validDime);
		
		expectedCoinReturnContents.add(bigSlug);
		expectedCoinReturnContents.add(littleSlug);
		assertEquals(expectedCoinReturnContents, underTest.getCoinReturnContents());
		underTest.pushCoinReturn();
		expectedCoinReturnContents.add(validQuarter);
		expectedCoinReturnContents.add(validDime);
		assertEquals(expectedCoinReturnContents, underTest.getCoinReturnContents());
	}
	
	@Test
	public void testPushingCoinReturnResetsTheAmountInserted_InsteadOfMakingYouInfinitelyRich() {
		VendingBrains underTest = new VendingBrains();
		Coin bigSlug = new Coin(100f, 100f);
		Coin littleSlug = new Coin(.001f, .001f);
		Coin validQuarter = new Coin(AcceptableCoins.QUARTER.getWeightInGrams(), AcceptableCoins.QUARTER.getSizeInMillimeters());
		Coin validDime = new Coin(AcceptableCoins.DIME.getWeightInGrams(), AcceptableCoins.DIME.getSizeInMillimeters());
		
		underTest.insertCoin(bigSlug);
		underTest.insertCoin(littleSlug);
		underTest.insertCoin(validQuarter);
		underTest.insertCoin(validDime);
		assertEquals("$0.35", underTest.readDisplay());
		underTest.pushCoinReturn();
		assertEquals("EXACT CHANGE ONLY", underTest.readDisplay());
	}
	
	@Test
	public void testChoosingAProductWithoutPuttingInMoneyGetsYouAPriceOnTheDisplay() {
		VendingBrains underTest = new VendingBrains();
		underTest.productInventory.put(VendableProducts.CANDY, 1);
		underTest.selectProduct(VendableProducts.CANDY);
		assertEquals("PRICE $0.65", underTest.readDisplay());
	}
	
	@Test
	public void testChoosingAProductWithoutPuttingInMoneyGetsYouTheRightPriceOnTheDisplay_AndThenInsertCoinOrExactChangeOnly() {
		VendingBrains underTest = new VendingBrains();
		underTest.productInventory.put(VendableProducts.CHIPS, 1);
		underTest.productInventory.put(VendableProducts.COLA, 1);
		underTest.selectProduct(VendableProducts.CHIPS);
		assertEquals("PRICE $0.50", underTest.readDisplay());
		assertEquals("EXACT CHANGE ONLY", underTest.readDisplay());
		underTest.addChangeToBank(AcceptableCoins.NICKEL, 10);
		assertEquals("INSERT COIN", underTest.readDisplay());
		underTest.selectProduct(VendableProducts.COLA);
		assertEquals("PRICE $1.00", underTest.readDisplay());
		assertEquals("INSERT COIN", underTest.readDisplay());
	}
	
	@Test
	public void testChoosingAProductWithoutSufficientMoneyGetsYouTheRightPriceOnTheDisplay_AndThenYourTotal() {
		VendingBrains underTest = new VendingBrains();
		underTest.productInventory.put(VendableProducts.CHIPS, 1);
		underTest.productInventory.put(VendableProducts.COLA, 1);
		underTest.insertCoin(new Coin(AcceptableCoins.QUARTER.getWeightInGrams(), AcceptableCoins.QUARTER.getSizeInMillimeters()));
		//note: it is around here, very late I realize, that I muchly feel the need for a copy constructor between AcceptableCoin and Coin.  A good refactor for next time.
		underTest.selectProduct(VendableProducts.CHIPS);
		assertEquals("PRICE $0.50", underTest.readDisplay());
		assertEquals("$0.25", underTest.readDisplay());
		underTest.selectProduct(VendableProducts.COLA);
		assertEquals("PRICE $1.00", underTest.readDisplay());
		assertEquals("$0.25", underTest.readDisplay());
	}
	
	@Test
	public void testChoosingAProductThatIsSoldOutGetsYouASoldOutMessageNoMatterWhat() {
		VendingBrains underTest = new VendingBrains();
		underTest.selectProduct(VendableProducts.CHIPS);
		assertEquals("SOLD OUT", underTest.readDisplay());
		assertEquals("EXACT CHANGE ONLY", underTest.readDisplay());
		underTest.insertCoin(new Coin(AcceptableCoins.QUARTER.getWeightInGrams(), AcceptableCoins.QUARTER.getSizeInMillimeters()));
		assertEquals("$0.25", underTest.readDisplay());
		underTest.selectProduct(VendableProducts.COLA);
		assertEquals("SOLD OUT", underTest.readDisplay());
		assertEquals("$0.25", underTest.readDisplay());
	}
	
	@Test
	public void testChoosingAProductThatIsInStockAndYouCanAffordSaysThankYou() {
		VendingBrains underTest = new VendingBrains();
		underTest.productInventory.put(VendableProducts.CHIPS, 1);
		underTest.insertCoin(new Coin(AcceptableCoins.QUARTER.getWeightInGrams(), AcceptableCoins.QUARTER.getSizeInMillimeters()));
		underTest.insertCoin(new Coin(AcceptableCoins.QUARTER.getWeightInGrams(), AcceptableCoins.QUARTER.getSizeInMillimeters()));
		underTest.selectProduct(VendableProducts.CHIPS);
		assertEquals("THANK YOU", underTest.readDisplay());
	}
	
	@Test
	public void testChoosingAProductThatIsInStockAndYouCanAffordResetsYourTotal_NoChangeDue() {
		VendingBrains underTest = new VendingBrains();
		underTest.productInventory.put(VendableProducts.CHIPS, 1);
		underTest.insertCoin(new Coin(AcceptableCoins.QUARTER.getWeightInGrams(), AcceptableCoins.QUARTER.getSizeInMillimeters()));
		underTest.insertCoin(new Coin(AcceptableCoins.QUARTER.getWeightInGrams(), AcceptableCoins.QUARTER.getSizeInMillimeters()));
		assertEquals(0, underTest.getCoinReturnContents().size());
		underTest.selectProduct(VendableProducts.CHIPS);
		assertEquals(0, underTest.getCoinReturnContents().size());
		assertEquals("THANK YOU", underTest.readDisplay());
		assertEquals("EXACT CHANGE ONLY", underTest.readDisplay());
	}
	
	@Test
	public void testChoosingAProductThatIsInStockAndYouCanAffordReallyGivesYouTheItem() {
		VendingBrains underTest = new VendingBrains();
		underTest.productInventory.put(VendableProducts.CHIPS, 1);
		underTest.insertCoin(new Coin(AcceptableCoins.QUARTER.getWeightInGrams(), AcceptableCoins.QUARTER.getSizeInMillimeters()));
		underTest.insertCoin(new Coin(AcceptableCoins.QUARTER.getWeightInGrams(), AcceptableCoins.QUARTER.getSizeInMillimeters()));
		underTest.selectProduct(VendableProducts.CHIPS);
		assertEquals(1, underTest.getItemHopperContents().size());
		assertEquals(VendableProducts.CHIPS, underTest.getItemHopperContents().get(0));
	}
	
	@Test
	public void testSeveralItemsCanBeVendedIfAllOtherRequirementsAreMet() {
		VendingBrains underTest = new VendingBrains();
		underTest.productInventory.put(VendableProducts.CHIPS, 1);
		underTest.productInventory.put(VendableProducts.COLA, 3);
		underTest.insertCoin(new Coin(AcceptableCoins.QUARTER.getWeightInGrams(), AcceptableCoins.QUARTER.getSizeInMillimeters()));
		underTest.insertCoin(new Coin(AcceptableCoins.QUARTER.getWeightInGrams(), AcceptableCoins.QUARTER.getSizeInMillimeters()));
		underTest.selectProduct(VendableProducts.CHIPS);
		assertEquals(1, underTest.getItemHopperContents().size());
		assertEquals(VendableProducts.CHIPS, underTest.getItemHopperContents().get(0));
		underTest.insertCoin(new Coin(AcceptableCoins.QUARTER.getWeightInGrams(), AcceptableCoins.QUARTER.getSizeInMillimeters()));
		underTest.insertCoin(new Coin(AcceptableCoins.QUARTER.getWeightInGrams(), AcceptableCoins.QUARTER.getSizeInMillimeters()));
		underTest.insertCoin(new Coin(AcceptableCoins.QUARTER.getWeightInGrams(), AcceptableCoins.QUARTER.getSizeInMillimeters()));
		underTest.insertCoin(new Coin(AcceptableCoins.QUARTER.getWeightInGrams(), AcceptableCoins.QUARTER.getSizeInMillimeters()));
		underTest.selectProduct(VendableProducts.COLA);
		assertEquals(2, underTest.getItemHopperContents().size());
		assertEquals(VendableProducts.COLA, underTest.getItemHopperContents().get(1));
	}
	
	@Test
	public void testMakingChangeDuringAValidPurchase_TenCentsIsOwed() {
		VendingBrains underTest = new VendingBrains();
		int plenty = 10;
		underTest.addChangeToBank(AcceptableCoins.DIME, plenty);
		underTest.addChangeToBank(AcceptableCoins.NICKEL, plenty);
		underTest.productInventory.put(VendableProducts.CANDY, 1);
		underTest.insertCoin(new Coin(AcceptableCoins.QUARTER.getWeightInGrams(), AcceptableCoins.QUARTER.getSizeInMillimeters()));
		underTest.insertCoin(new Coin(AcceptableCoins.QUARTER.getWeightInGrams(), AcceptableCoins.QUARTER.getSizeInMillimeters()));
		underTest.insertCoin(new Coin(AcceptableCoins.QUARTER.getWeightInGrams(), AcceptableCoins.QUARTER.getSizeInMillimeters()));
		assertEquals(0, underTest.getCoinReturnContents().size());
		
		underTest.selectProduct(VendableProducts.CANDY);
		
		assertEquals(1, underTest.getItemHopperContents().size());
		assertEquals(VendableProducts.CANDY, underTest.getItemHopperContents().get(0));
		assertEquals(1, underTest.getCoinReturnContents().size());
		assertEquals(new Coin(AcceptableCoins.DIME.getWeightInGrams(), AcceptableCoins.DIME.getSizeInMillimeters()), underTest.getCoinReturnContents().get(0));
		
	}
	
	@Test
	public void testMakingChangeDuringAValidPurchase_FiveCentsIsOwed() {
		VendingBrains underTest = new VendingBrains();
		int plenty = 10;
		underTest.addChangeToBank(AcceptableCoins.DIME, plenty);
		underTest.addChangeToBank(AcceptableCoins.NICKEL, plenty);
		underTest.productInventory.put(VendableProducts.CANDY, 1);
		underTest.insertCoin(new Coin(AcceptableCoins.QUARTER.getWeightInGrams(), AcceptableCoins.QUARTER.getSizeInMillimeters()));
		underTest.insertCoin(new Coin(AcceptableCoins.QUARTER.getWeightInGrams(), AcceptableCoins.QUARTER.getSizeInMillimeters()));
		underTest.insertCoin(new Coin(AcceptableCoins.DIME.getWeightInGrams(), AcceptableCoins.DIME.getSizeInMillimeters()));
		underTest.insertCoin(new Coin(AcceptableCoins.DIME.getWeightInGrams(), AcceptableCoins.DIME.getSizeInMillimeters()));
		assertEquals(0, underTest.getCoinReturnContents().size());
		
		underTest.selectProduct(VendableProducts.CANDY);
		
		assertEquals(1, underTest.getItemHopperContents().size());
		assertEquals(VendableProducts.CANDY, underTest.getItemHopperContents().get(0));
		assertEquals(1, underTest.getCoinReturnContents().size());
		assertEquals(new Coin(AcceptableCoins.NICKEL.getWeightInGrams(), AcceptableCoins.NICKEL.getSizeInMillimeters()), underTest.getCoinReturnContents().get(0));
	}
	
	@Test
	public void testMachineWillNotProvideChangeItDoesNotHave_ButWillMakeItUpWithSmallerCoins() {
		VendingBrains underTest = new VendingBrains();
		underTest.addChangeToBank(AcceptableCoins.NICKEL, 2);
		underTest.productInventory.put(VendableProducts.CANDY, 1);
		underTest.insertCoin(new Coin(AcceptableCoins.QUARTER.getWeightInGrams(), AcceptableCoins.QUARTER.getSizeInMillimeters()));
		underTest.insertCoin(new Coin(AcceptableCoins.QUARTER.getWeightInGrams(), AcceptableCoins.QUARTER.getSizeInMillimeters()));
		underTest.insertCoin(new Coin(AcceptableCoins.QUARTER.getWeightInGrams(), AcceptableCoins.QUARTER.getSizeInMillimeters()));
		assertEquals(0, underTest.getCoinReturnContents().size());
		
		underTest.selectProduct(VendableProducts.CANDY);
		
		assertEquals(1, underTest.getItemHopperContents().size());
		assertEquals(VendableProducts.CANDY, underTest.getItemHopperContents().get(0));
		assertEquals(2, underTest.getCoinReturnContents().size());
		assertEquals(new Coin(AcceptableCoins.NICKEL.getWeightInGrams(), AcceptableCoins.NICKEL.getSizeInMillimeters()), underTest.getCoinReturnContents().get(0));
		assertEquals(new Coin(AcceptableCoins.NICKEL.getWeightInGrams(), AcceptableCoins.NICKEL.getSizeInMillimeters()), underTest.getCoinReturnContents().get(1));
	}
	
	@Test
	public void testMachineWillGetAsCloseAsItCanIfTrulyOutOfChange() {
		VendingBrains underTest = new VendingBrains();
		underTest.addChangeToBank(AcceptableCoins.NICKEL, 1);
		underTest.productInventory.put(VendableProducts.CANDY, 1);
		underTest.insertCoin(new Coin(AcceptableCoins.QUARTER.getWeightInGrams(), AcceptableCoins.QUARTER.getSizeInMillimeters()));
		underTest.insertCoin(new Coin(AcceptableCoins.QUARTER.getWeightInGrams(), AcceptableCoins.QUARTER.getSizeInMillimeters()));
		underTest.insertCoin(new Coin(AcceptableCoins.QUARTER.getWeightInGrams(), AcceptableCoins.QUARTER.getSizeInMillimeters()));
		assertEquals(0, underTest.getCoinReturnContents().size());
		
		underTest.selectProduct(VendableProducts.CANDY);
		
		assertEquals(1, underTest.getItemHopperContents().size());
		assertEquals(VendableProducts.CANDY, underTest.getItemHopperContents().get(0));
		assertEquals(1, underTest.getCoinReturnContents().size());
		assertEquals(new Coin(AcceptableCoins.NICKEL.getWeightInGrams(), AcceptableCoins.NICKEL.getSizeInMillimeters()), underTest.getCoinReturnContents().get(0));
		assertEquals(0, underTest.getNumberOfCoinsInBank(AcceptableCoins.NICKEL));
	}
	
}
