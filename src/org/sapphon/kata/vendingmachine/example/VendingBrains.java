package org.sapphon.kata.vendingmachine.example;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class VendingBrains implements IVendingBrains{

	private LinkedHashMap<AcceptableCoins, Integer> bank;
	private LinkedHashMap<AcceptableCoins, Integer> inserted;
	private LinkedHashMap<VendableProducts, Integer> productInventory;
	private List<Coin> coinReturnContents;
	private List<VendableProducts> hopperContents;
	
	String interruptMessage = "";

	public VendingBrains() {
		initializeEmptyBank();
		initializeEmptyCoinReturn();
		initializeEmptyInsertedAmount();
		initializeEmptyProductInventory();
		initializeEmptyHopperContents();
	}



	public String readDisplay() {
		if(interruptMessage != ""){
			String toReturn = interruptMessage;
			interruptMessage = "";
			return toReturn;
		}
		else if (calculateInsertedTotal() > 0f) {
			return "$" + String.format("%.02f", this.calculateInsertedTotal());
		}
		else if (canMakeChangeWithCurrentBank()) {
				return "INSERT COIN";
			} else
				return "EXACT CHANGE ONLY";
		}
		
	public void selectProduct(VendableProducts productToVend) {
		if(this.productInventory.get(productToVend) > 0){
			if(productToVend.getCostInDollars() > this.calculateInsertedTotal()){
				this.interruptMessage = "PRICE $" + String.format("%.02f", productToVend.getCostInDollars());
			} else{
				this.interruptMessage = "THANK YOU";
				this.hopperContents.add(productToVend);
				this.addInsertedAmountToBank();
				if(this.calculateInsertedTotal() > productToVend.getCostInDollars()){
					this.makeChangeFor(productToVend);
				}
				this.initializeEmptyInsertedAmount();
			}
		}
		else{
			this.interruptMessage = "SOLD OUT";
		}
		
	}

	public void insertCoin(Coin coin) {
		for (AcceptableCoins coinType : AcceptableCoins.values()) {
			if (coin.equals(new Coin(coinType.getWeightInGrams(), coinType
					.getSizeInMillimeters()))) {
				this.inserted.put(coinType, this.inserted.get(coinType) + 1);
				return;
			}
		}
		this.coinReturnContents.add(coin);
	}
	
	public List<VendableProducts> getItemHopperContents(){
		return this.hopperContents;
	}

	public void addChangeToBank(AcceptableCoins coinType, int howMany) {
		this.bank.put(coinType, this.bank.get(coinType) + howMany);
	}

	public int getNumberOfCoinsInBank(AcceptableCoins coinType) {
		return this.bank.get(coinType);
	}
	
	public void takeCoinReturnContents(){
		this.initializeEmptyCoinReturn();
	}
	

	public void addProductToInventory(VendableProducts product, int numberToAdd) {
		this.productInventory.put(product, this.productInventory.get(product) + numberToAdd);
	}

	public void pushCoinReturn() {
		for (AcceptableCoins denomination : this.inserted.keySet()) {
			for(int n = 0; n < this.inserted.get(denomination); n++){
				this.coinReturnContents.add(new Coin(denomination.getWeightInGrams(), denomination.getSizeInMillimeters()));
			}
		}
		this.initializeEmptyInsertedAmount();
	}
	
	public List<Coin> getCoinReturnContents() {
		return this.coinReturnContents;
	}

	public LinkedHashMap<VendableProducts, Integer> getProductInventory() {
		return this.productInventory;
	}
	


	private void initializeEmptyInsertedAmount() {
		this.inserted = new LinkedHashMap<AcceptableCoins, Integer>();
		for (AcceptableCoins coin : AcceptableCoins.values()) {
			this.inserted.put(coin, 0);
		}

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


	private void initializeEmptyProductInventory(){
		this.productInventory = new LinkedHashMap<VendableProducts, Integer>();
		for (VendableProducts product : VendableProducts.values()) {
			this.productInventory.put(product, 0);
		}
	}
	
	private void initializeEmptyHopperContents() {
		this.hopperContents = new ArrayList<VendableProducts>();
		
	}



	private void addInsertedAmountToBank() {
		for (AcceptableCoins coinType : AcceptableCoins.values()) {
			this.addChangeToBank(coinType, this.inserted.get(coinType));
		}
	}

	private float calculateInsertedTotal() {
		float total = 0f;
		for (AcceptableCoins coin : AcceptableCoins.values()) {
			total += this.inserted.get(coin) * coin.getMonetaryValueInDollars();
		}
		return total;
	}


	private void makeChangeFor(VendableProducts productToVend) {
		float localTotalInserted = this.calculateInsertedTotal();
		localTotalInserted -= productToVend.getCostInDollars();

		for (AcceptableCoins coin : AcceptableCoins.values()) {
			while(localTotalInserted > coin.getMonetaryValueInDollars() && this.bank.get(coin) > 0){
				this.coinReturnContents.add(new Coin(coin.getWeightInGrams(), coin.getSizeInMillimeters()));
				this.addChangeToBank(coin, -1);
				localTotalInserted -= coin.getMonetaryValueInDollars();
			}
		}
	}

}
