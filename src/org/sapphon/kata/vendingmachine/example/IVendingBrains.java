package org.sapphon.kata.vendingmachine.example;

import java.util.List;
import java.util.Map;

public interface IVendingBrains {

	
	public String readDisplay();
	
	public void selectProduct(VendableProducts productToVend);

	public void insertCoin(Coin coin);
	
	public List<VendableProducts> getItemHopperContents();

	public void addChangeToBank(AcceptableCoins coinType, int howMany);

	public int getNumberOfCoinsInBank(AcceptableCoins coinType);
	
	public void takeCoinReturnContents();

	public void addProductToInventory(VendableProducts product, int numberToAdd);

	public void pushCoinReturn();
	
	public List<Coin> getCoinReturnContents();

	public Map<VendableProducts, Integer> getProductInventory();

}
