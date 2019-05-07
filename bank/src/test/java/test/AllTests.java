package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ SavingAccountAddMoneyTest.class,SavingAccountWithdrawMoneyTest.class,SpendingAccountAddMoneyTest.class,
	SpendingAccountWithdrawMoneyTest.class})
public class AllTests {

}
