package org.sapphon.kata.vendingmachine.example;

public class VendingBrains {

	private boolean pretendYouHaveChange = false;

	public String readDisplay() {
		if(pretendYouHaveChange){
			return "INSERT COIN";
		}
		else return "EXACT CHANGE ONLY";
	}

	public void addChangeToBank(AcceptableCoins coinType, int howMany) {
		this.pretendYouHaveChange  = true;
	}

}
