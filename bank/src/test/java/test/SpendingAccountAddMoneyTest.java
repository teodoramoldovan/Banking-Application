package test;

import org.junit.Test;

import BankAccounts.Account;

import BankAccounts.SpendingAccount;

public class SpendingAccountAddMoneyTest {
	

	@Test
	public void testAddSaving() {
		
		Account a=new SpendingAccount(1,100);
		a.addMoney(400);
		double sumaDupa=a.getSuma();
		assert(sumaDupa==500);
	}

}
