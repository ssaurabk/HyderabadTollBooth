package com.htb.cards;

public class SmartCard {
	boolean authentic;
	boolean valid;
	int balance;

	public SmartCard(boolean authentic, boolean valid, int balance) {
		super();
		this.authentic = authentic;
		this.valid = valid;
		this.balance = balance;
	}

	public boolean isAuthentic() {
		return authentic;
	}

	public boolean isValid() {
		return valid;
	}

	public int getBalance() {
		return balance;
	}

	public void setAuthentic(boolean authentic) {
		this.authentic = authentic;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public void rechargeSmartCard(int rechargeAmount) {
		this.balance = this.balance + rechargeAmount;
	}
}
