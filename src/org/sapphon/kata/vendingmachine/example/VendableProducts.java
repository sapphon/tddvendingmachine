package org.sapphon.kata.vendingmachine.example;

public enum VendableProducts {
	COLA, CHIPS, CANDY;

	public float getCostInDollars() {
		return 1f;
	}
}
