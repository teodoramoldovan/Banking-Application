package test;

import org.junit.Test;

import BankAccounts.Account;
import BankAccounts.SpendingAccount;

public class SpendingAccountWithdrawMoneyTest {
	

	@Test
	public void testAddSaving() {
		
		Account a=new SpendingAccount(1,1000);
		a.withdrawMoney(400);
		double sumaDupa=a.getSuma();
		assert(sumaDupa==600);
	}

}
