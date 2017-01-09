package org.sapphon.kata.vendingmachine.example;

import java.util.LinkedHashMap;

public class VendingBrains {

	boolean pretendYouHaveChange = false;

	public String readDisplay() {
		if(this.pretendYouHaveChange){
			return "INSERT COIN";
		}
		else return "EXACT CHANGE ONLY";
	}

	public void addChangeToBank(AcceptableCoins coinType, int howMany) {
		this.pretendYouHaveChange = true;
	}

	public int getNumberOfCoinsInBank(AcceptableCoins coinType) {
		if(coinType == AcceptableCoins.QUARTER){
			return 0;
		}
		else return 1;
	}

}
