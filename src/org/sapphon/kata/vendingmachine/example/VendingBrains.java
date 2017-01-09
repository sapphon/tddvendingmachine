package org.sapphon.kata.vendingmachine.example;

import java.util.LinkedHashMap;

public class VendingBrains {

	LinkedHashMap<AcceptableCoins, Integer> bank;
	
	public VendingBrains(){
		this.bank = new LinkedHashMap<AcceptableCoins, Integer>();
		for (AcceptableCoins coin : AcceptableCoins.values()) {
			this.bank.put(coin, 0);
		}
	}

	public String readDisplay() {
		if((this.bank.get(AcceptableCoins.NICKEL) > 2) || 
				(this.bank.get(AcceptableCoins.NICKEL) > 0 && this.bank.get(AcceptableCoins.DIME) > 0)){
			return "INSERT COIN";
		}
		else return "EXACT CHANGE ONLY";
	}

	public void addChangeToBank(AcceptableCoins coinType, int howMany) {
		this.bank.put(coinType, this.bank.get(coinType) + howMany);
	}

	public int getNumberOfCoinsInBank(AcceptableCoins coinType) {
		return this.bank.get(coinType);
	}

}
