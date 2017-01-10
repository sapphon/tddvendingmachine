package org.sapphon.kata.vendingmachine.example;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class VendingBrains {

	LinkedHashMap<AcceptableCoins, Integer> bank;
	LinkedHashMap<AcceptableCoins, Integer> inserted;
	List<Coin> coinReturnContents;

	public VendingBrains() {
		initializeEmptyBank();
		initializeEmptyCoinReturn();
		initializeEmptyInsertedAmount();
	}

	public String readDisplay() {
		if (calculateInsertedTotal() > 0f) {
			return "$" + String.format("%.02f", this.calculateInsertedTotal());
		}
		else if (canMakeChangeWithCurrentBank()) {
				return "INSERT COIN";
			} else
				return "EXACT CHANGE ONLY";
		}
		

	private float calculateInsertedTotal() {
		float total = 0f;
		for (AcceptableCoins coin : AcceptableCoins.values()) {
			total += this.inserted.get(coin) * coin.getMonetaryValueInDollars();
		}
		return total;
	}

	public void insertCoin(Coin coin) {
		for (AcceptableCoins coinType : AcceptableCoins.values()) {
			if (coin.equals(new Coin(coinType.getWeightInGrams(), coinType
					.getSizeInMillimeters()))) {
				this.inserted.put(coinType, this.inserted.get(coinType) + 1);
				return;
			}
		}
		this.coinReturnContents.add(coin);
	}

	public void addChangeToBank(AcceptableCoins coinType, int howMany) {
		this.bank.put(coinType, this.bank.get(coinType) + howMany);
	}

	public int getNumberOfCoinsInBank(AcceptableCoins coinType) {
		return this.bank.get(coinType);
	}
	
	public void takeCoinReturnContents(){
		this.initializeEmptyCoinReturn();
	}

	private void initializeEmptyInsertedAmount() {
		this.inserted = new LinkedHashMap<AcceptableCoins, Integer>();
		for (AcceptableCoins coin : AcceptableCoins.values()) {
			this.inserted.put(coin, 0);
		}

	}

	private void initializeEmptyBank() {
		this.bank = new LinkedHashMap<AcceptableCoins, Integer>();
		for (AcceptableCoins coin : AcceptableCoins.values()) {
			this.bank.put(coin, 0);
		}
	}

	private void initializeEmptyCoinReturn() {
		this.coinReturnContents = new ArrayList<Coin>();
	}

	private boolean canMakeChangeWithCurrentBank() {
		return (this.bank.get(AcceptableCoins.NICKEL) > 2)
				|| (this.bank.get(AcceptableCoins.NICKEL) > 0 && this.bank
						.get(AcceptableCoins.DIME) > 0);
	}

	public List<Coin> getCoinReturnContents() {
		return this.coinReturnContents;
	}

	public LinkedHashMap<VendableProducts, Integer> getProductInventory() {
		LinkedHashMap<VendableProducts, Integer> localTrickyMap = new LinkedHashMap<VendableProducts, Integer>();
		for (VendableProducts product : VendableProducts.values()) {
			localTrickyMap.put(product, 0);
		}
		return localTrickyMap;
	}

}
