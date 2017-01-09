package org.sapphon.kata.vendingmachine.example;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class VendingBrains {

	LinkedHashMap<AcceptableCoins, Integer> bank;
	List<Coin> coinReturnContents;

	public VendingBrains() {
		initializeEmptyBank();
		initializeEmptyCoinReturn();
	}

	public String readDisplay() {
		if (canMakeChangeWithCurrentBank()) {
			return "INSERT COIN";
		} else
			return "EXACT CHANGE ONLY";
	}

	public void insertCoin(Coin coin) {
		for (AcceptableCoins coinType : AcceptableCoins.values()) {
			if (coin == new Coin(coinType.getWeightInGrams(),
					coinType.getSizeInMillimeters())) {
				// TODO
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

}
