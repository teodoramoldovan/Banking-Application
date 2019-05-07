package BankAccounts;

import BankClients.Person;

public class ViewAccount {
	
	private String holderName;
	private long idCont;
	private double suma;
	private String accountType;
	
	public ViewAccount(Person p,Account a) {
		holderName=p.getNume()+" "+p.getPrenume();
		idCont=a.getId();
		suma=a.getSuma();
		//System.out.println(a.getClass().getName());
		if(a.getClass().getName().equals("BankAccounts.SavingAccount")) {
			accountType="Saving Account";		
		}
		else accountType="Spending Account";
	}
}
