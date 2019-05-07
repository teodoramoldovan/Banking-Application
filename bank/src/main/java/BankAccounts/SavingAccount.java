package BankAccounts;

public class SavingAccount extends Account{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private double suma;
	private int period;
	private boolean oneAdd;
	private boolean oneWithdrawal;
	private double interest=0.25;
	private double minimumDepositValue=300;

	public SavingAccount(int id,int period) {
		super(id, 0);
		this.period=period;
		this.oneAdd=false;
		this.oneWithdrawal=false;	
		
	}

	@Override
	public String addMoney(double suma) {
		// TODO Auto-generated method stub
		StringBuilder sb=new StringBuilder();
		if(this.oneAdd==true) {
			this.setChanged();
			sb.append("This is a Savings Account and you can only make one deposit. There are already money in the account with id:"+this.getId());
			this.notifyObservers(sb.toString());
			return sb.toString();
		}
		if(suma<minimumDepositValue) {
			this.setChanged();
			sb.append("You can not deposit such a small sum in a Savings Account. The sum must be greater than "+this.minimumDepositValue);
			this.notifyObservers(sb.toString());
			return sb.toString();
		}
		else {
			this.setSuma(suma);
			this.setChanged();
			sb.append("Sum "+suma+"$ has been added to your SAVING ACCOUNT with id "+this.getId()+". You now have "+this.getSuma()+"$");
			this.notifyObservers(sb.toString());
			this.oneAdd=true;
			
			return sb.toString();
		}
		//sb.append("Something went wrong");
		//return sb.toString();
		
	}
	public double sumToWithdraw() {
		double suma=this.getSuma();
		for(int i=0;i<period;i++) {
			suma+=suma*this.interest;
		}
		return suma;
	}

	@Override
	public String withdrawMoney(double suma) {
		// TODO Auto-generated method stub
		StringBuilder sb=new StringBuilder();
		if(this.oneAdd==false) {
			this.setChanged();
			sb.append("You can't withdraw money from an empty account");
			this.notifyObservers(sb.toString());
			return sb.toString();
		}
		if(this.oneWithdrawal==true) {
			this.setChanged();
			sb.append("Withdrawal already done");
			this.notifyObservers(sb.toString());
			return sb.toString();
		}
		else {
			
			double sum=sumToWithdraw();
			this.oneWithdrawal=true;
			this.setChanged();
			this.setSuma(0);
			sb.append("You made a withdrawal from your SAVING ACCOUNT with id "+this.getId()+". Total amount withdrawn: "+sum+"$. You now have: "+this.getSuma()+"$");
			this.notifyObservers(sb.toString());
			
			return sb.toString();
		}	
		
	}
	
	
	
	

}
