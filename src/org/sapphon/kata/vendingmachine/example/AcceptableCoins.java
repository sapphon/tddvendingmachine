package org.sapphon.kata.vendingmachine.example;

public enum AcceptableCoins {
	QUARTER(5.67f,1f,1f), 
	DIME(2.268f,1f,1f), 
	NICKEL(1f,1f,1f);
	
	private float weightInGrams;
	
    AcceptableCoins(float weight, float size, float value){
		this.weightInGrams = weight;
	}

	public float getWeightInGrams() {
		return this.weightInGrams;
	}
}
