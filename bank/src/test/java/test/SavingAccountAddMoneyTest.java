package test;



import org.junit.Test;

import BankAccounts.Account;
import BankAccounts.SavingAccount;

public class SavingAccountAddMoneyTest {

	
	@Test
	public void testAddSaving() {
		
		Account a=new SavingAccount(1,10);
		a.addMoney(400);
		double sumaDupa=a.getSuma();
		System.out.println(sumaDupa);
		assert(sumaDupa==400);
	}

}
