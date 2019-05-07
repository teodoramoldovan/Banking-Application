package BankAccounts;

public class SpendingAccount extends Account {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SpendingAccount(int id,double suma) {
		super(id,suma);
	}

	@Override
	public String addMoney(double suma) {
		// TODO Auto-generated method stub
		StringBuilder sb=new StringBuilder();
		this.setSuma(this.getSuma()+suma);
		this.setChanged();
		sb.append("Sum "+suma+"$ has been added to your SPENDING ACCOUNT with id "+this.getId()+" .You now have "+this.getSuma()+"$");
		this.notifyObservers(sb.toString());
		//this.notifyObservers("In your account with id: "+ this.getId()+" you now have "+this.getSuma()+"$");
		
		return sb.toString();
		
	}

	@Override
	public String withdrawMoney(double suma) {
		// TODO Auto-generated method stub
		StringBuilder sb=new StringBuilder();
		if(suma>this.getSuma()) {
			this.setChanged();
			sb.append("Account balance is too low to make this withdrawal. The current balance is: "+this.getSuma()+"$");
			this.notifyObservers(sb.toString());
			return sb.toString();
		}
		this.setSuma(this.getSuma()-suma);
		this.setChanged();
		sb.append("You made a withdrawal from your SPENDING ACCOUNT with id "+this.getId()+". Total amount withdrawn: "+suma+"$. You now have: "+this.getSuma()+"$");
		this.notifyObservers(sb.toString());
		
		return sb.toString();
		
	}

}
