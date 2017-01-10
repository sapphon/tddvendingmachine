package org.sapphon.kata.vendingmachine.example;

public enum VendableProducts {
	COLA(1f), CHIPS(.5f), CANDY(.65f);

	private float costInDollars;
	
	VendableProducts(float cost){
		this.costInDollars = cost;
	}
	
	public float getCostInDollars() {
		return this.costInDollars;
	}
}
