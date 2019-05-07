package test;

import org.junit.Test;

import BankAccounts.Account;
import BankAccounts.SavingAccount;

public class SavingAccountWithdrawMoneyTest {
	@Test
	public void testWithdrawSaving() {
		
		Account a=new SavingAccount(1,10);
		a.addMoney(400);
	
		//System.out.println(sumaDupa);
		
		a.withdrawMoney(400);
		double sumaDupa=a.getSuma();
		assert(sumaDupa==0);
	}
}
