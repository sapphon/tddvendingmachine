package org.sapphon.kata.vendingmachine.example;

public class Coin {
	private float weightInGrams;
	private float sizeInMillimeters;

	public Coin(float weight, float size) {
		this.weightInGrams = weight;
		this.sizeInMillimeters = size;
	}

	public float getWeightInGrams() {
		return this.weightInGrams;
	}

	public float getSizeInMillimeters() {
		return this.sizeInMillimeters;
	}
}
