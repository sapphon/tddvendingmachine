package org.sapphon.kata.vendingmachine.example;

public enum AcceptableCoins {
	QUARTER(1f,1f,1f), 
	DIME(1f,1f,1f), 
	NICKEL(1f,1f,1f);
	
    AcceptableCoins(float weight, float size, float value){
		
	}

	public float getWeightInGrams() {
		return 5.67f;
	}
}
