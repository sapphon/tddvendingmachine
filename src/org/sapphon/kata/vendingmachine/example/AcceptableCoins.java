package org.sapphon.kata.vendingmachine.example;

public enum AcceptableCoins {
	QUARTER(5.67f,24.26f,1f), 
	DIME(2.268f,17.91f,1f), 
	NICKEL(5f,21.21f,1f);
	
	private float weightInGrams;
	private float sizeInMillis;
	
    AcceptableCoins(float weight, float size, float value){
		this.weightInGrams = weight;
		this.sizeInMillis = size;
	}

	public float getWeightInGrams() {
		return this.weightInGrams;
	}

	public float getSizeInMillimeters() {
		return this.sizeInMillis;
	}

	public float getMonetaryValueInDollars() {
		return .25f;
	}
}
