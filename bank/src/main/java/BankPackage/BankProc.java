package BankPackage;

import java.time.LocalDate;

public interface BankProc {
	
	/**
	 * @invariant the sum of all accounts must be equal to the sum in the bank
	 **/
	
	/**
	 * @pre nume,prenume,birthday must not be NULL and cnp must not be 0
	 * @post number of clients(persons) in the bank must be equal with the previous number incremented by one
	 **/	
	public String addPerson(String nume,String prenume,LocalDate birthday,long cnp) ;
	
	/**
	 * @pre the person with personID must exist in the bank
	 * @post the number of persons must remain the same
	 **/	
	public String editPerson(long personID,String nume,String prenume) ;
	
	/**
	 * @pre the person with the specified personID must exist in the bank
	 * @post number of clients(persons) in the bank must be equal with the previous number decremented by one
	 **/
	public String removePerson(long personID);
	
	/**
	 * @pre the person with the id equal with personID must already exist in the bank
	 * @post one more account 
	 **/
	public String addAccount(long personID,double suma,String type,int period);
	
	
	
	/**
	 * @pre the account with accountID must exist
	 * @post number of accounts is decremented by one
	 **/
	public String removeAccount(int accountID);
	
	
	/**
	 * @pre the account with accountID must exist
	 * @post the sum in the account is greater than the previous one
	 **/
	public String addMoneyToAccount(int accountID,double suma);
	
	
	/**
	 * 
	 * @pre the account with accountID must exist
	 * @post number of accounts doesn't change
	 *
	 */
	public String editAccount(int accountID,long newHolderID);
	
	/**
	 * @pre the account with accountID must exist
	 * @post the sum in the account is smaller than the previous one
	 **/
	public String withdrawMoneyFromAccount(int accountID,double suma);
	
	public void readData();
	public void writeData();
	
}
