package BankAccounts;

import java.io.Serializable;
import java.util.Observable;

public abstract class Account extends Observable implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private double suma;
	
	public Account(int id, double suma) {
		this.id = id;
		this.suma = suma;
	}

	public int getId() {
		return id;
	}
	/*public void setId(int id) {
		this.id=id;
	}*/
	
	public double getSuma() {
		return suma;
	}

	public void setSuma(double suma) {
		this.suma = suma;
	}
	
	public abstract String addMoney(double suma) ;
	public abstract String withdrawMoney(double suma);
	//delete?
	
	
	
	
	
	

}
