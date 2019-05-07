package BankPackage;

import java.util.List;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Set;

import BankAccounts.Account;
import BankAccounts.SavingAccount;
import BankAccounts.SpendingAccount;
import BankAccounts.ViewAccount;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import BankClients.Person;


public class Bank implements BankProc {
	
	private HashMap<Person,List<Account>> info=new HashMap<Person,List<Account>>();
	private double totalInBank;
	
	private String s;
	
	public Bank() {
		//writeData();
		readData();
		
		totalInBank=getTotalSum();
	}
	private boolean isWellFormed() {
		if (totalInBank == getTotalSum())return true;
		return false;
	}

	private int getTotalSum() {
		int sum = 0;
		for (Entry<Person, List<Account>> e : info.entrySet())
			for (Account a : e.getValue())
				sum += a.getSuma();
		return sum;
	}

	public String addPerson(String nume, String prenume, LocalDate birthday,long cnp) {
		// TODO Auto-generated method stub
		assert(isWellFormed());
		assert(nume!=null&&prenume!=null&&birthday!=null&&cnp!=0);
		int hashMapSize=info.size();
		Person p=new Person(nume,prenume,birthday,cnp);
		info.put(p, new ArrayList<Account>());//
		assert(hashMapSize+1==info.size());
		assert(isWellFormed());
		writeData();
		return "Person added";
		
	}


	public String editPerson(long personID, String nume, String prenume) {
		// TODO Auto-generated method stub
		assert(isWellFormed());
		Person p=findPerson(personID);
		System.out.println(p.getId());
		assert(p!=null);
		int hashMapSize=info.size();
		if(!nume.isEmpty()) {
			p.setNume(nume);
			
		}
		if(!prenume.isEmpty()) {
			p.setPrenume(prenume);
		}
		assert(hashMapSize==info.size());
		assert(isWellFormed());
		writeData();
		return "Person was edited";
	}

	public String removePerson(long personID) {
		// TODO Auto-generated method stub
		assert(isWellFormed());
		Person p=findPerson(personID);
		
		//System.out.println(p.getId());
		assert(p!=null);
		int hashMapSize=info.size();
		info.remove(p);
		assert(hashMapSize-1==info.size());
		
		assert(isWellFormed());
		writeData();
		return "Person deleted";
	}
	private Person findPerson(long id) {
		for(Person p:info.keySet()) {
			if(p.getId()==id)return p;
		}
		return null;
	}
	
	public void listPersons() {
		assert !info.isEmpty();
		
		for(Person p: info.keySet())
	    	System.out.println(p.getId()+" " +p.getNume());
	}
	public int lastAccountID() {
		int last=0;
		for(Entry<Person,List<Account>> e:info.entrySet()) {
			for(Account a:e.getValue()) {
				if(a.getId()>last)last=a.getId();
			}
		}
		return last;
	}
	@SuppressWarnings("unlikely-arg-type")
	public String addAccount(long personID, double suma, String type, int period) {
		// TODO Auto-generated method stub
		assert(isWellFormed());
		Person p=findPerson(personID);
		assert(p!=null);
		int accountCount;
		if(info.get(p)!=null) {
			 accountCount=info.get(p).size();
		}
		else accountCount=0;
		int accountID=lastAccountID()+1;
		if(type.equals("Spending Account")) {
			totalInBank+=suma;
			Account a=new SpendingAccount(accountID,suma);
			System.out.println(a.getId()+" "+a.getSuma()+" Account count: "+accountCount);
			a.addObserver(p);
			
			
			info.get(p).add(a);
			
			assert(isWellFormed());
			assert(accountCount+1==info.get(p).size());
			writeData();
			return "Spending Account added";
		}
		else {
			Account a=new SavingAccount(accountID,period);
			a.addObserver(p);
			info.get(p).add(a);
			assert(isWellFormed());
			assert(accountCount+1==info.get(p).size());
			writeData();
			return "Saving Account added";
		}
	
	}
	public String editAccount(int accountID,long newHolderID) {
		assert(isWellFormed());
		Account a=findAccount(accountID);
		assert(a!=null);
		Person p=null;
		for(Entry<Person,List<Account>> e:info.entrySet()) {
			for(Account a1:e.getValue()) {
				if(a.getId()==accountID)p=e.getKey();
			}
		}
		int count=info.get(p).size();
		removeAccount(accountID);
		
		addAccount(newHolderID,a.getSuma(),(a instanceof SpendingAccount)?"Spending Account":"Saving Account",3);
		assert(isWellFormed());
		writeData();
		return "Account edited";
		
		
	}


	public String removeAccount(int accountID) {
		// TODO Auto-generated method stub
		
		Person p = null;
		assert(isWellFormed());
		int count=0;
		/*for(Entry<Person,List<Account>> e:info.entrySet()) {
			for(@SuppressWarnings("unused") Account a:e.getValue()) {
				count++;
			}
		}*/
		Account a=findAccount(accountID);
		assert(a!=null);
		for(Entry<Person,List<Account>> e:info.entrySet()) {
			for(Account a1:e.getValue()) {
				if(a1.getId()==accountID) {
					p=e.getKey();
				}
			}
		}
		count=info.get(p).size();
		double sumaDinCont=a.getSuma();
		info.get(p).remove(a);
		//System.out
		assert(count-1==info.get(p).size());
		
		totalInBank-=sumaDinCont;
		assert(isWellFormed());
		writeData();
		return "Account deleted";
	}

	public ArrayList<Person> getPersons() {
		ArrayList<Person> persons= new ArrayList<Person>();
		for(Person p: info.keySet()){
			persons.add(p);
		}
		return persons;
	}
	
	public ArrayList<ViewAccount> getAccounts(){
		ArrayList<ViewAccount> accounts = new ArrayList<ViewAccount>();
		for (Entry<Person, List<Account>> e : info.entrySet()) {
			for (Account a : e.getValue()) {
				Person p = findPerson(e.getKey().getId());
				ViewAccount cont=new ViewAccount(p,a);
				accounts.add(cont);
			}
		}
		return accounts;
	}

	@SuppressWarnings("unchecked")
	public void readData() {
		// TODO Auto-generated method stub
		try {
	         FileInputStream fileIn = new FileInputStream("bank.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         
	         info = (HashMap<Person, List<Account>>) in.readObject();
	         in.close();
	         fileIn.close();
	         
		 }catch(IOException i) {
	         i.printStackTrace();
	         return;
	      }catch(ClassNotFoundException c) {
	         System.out.println("Person class not found");
	         c.printStackTrace();
	         return;
	      }
	}

	public void writeData() {
		// TODO Auto-generated method stub
		try{
			
			FileOutputStream fileOut = new FileOutputStream("bank.ser");
		    ObjectOutputStream out = new ObjectOutputStream(fileOut);

		    out.writeObject(info);
		    out.close();
		    fileOut.close();
		 
			}catch(IOException e) {
				e.printStackTrace();
			}

	}
	public Account findAccount(int accountID) {
		for(Entry<Person,List<Account>> e:info.entrySet()) {
			for(Account a:e.getValue()) {
				if(a.getId()==accountID)return a;
			}
		}
			
		return null;
	}
	public String addMoneyToAccount(int accountID, double suma) {
		// TODO Auto-generated method stub
		StringBuilder sb=new StringBuilder();
		assert(isWellFormed());
		Account a=findAccount(accountID);
		
		assert(a!=null);
		double sumaPrev=a.getSuma();
		Person p = null;
		for (Entry<Person, List<Account>> e : info.entrySet()) {
			for (Account a1 : e.getValue()) {
				if(a1.getId()==a.getId()) {
					p = findPerson(e.getKey().getId());
				}
				
			}
		}
		a.addObserver(p);
		sb.append(a.addMoney(suma));
		composeMessage(sb.toString());

			
		
		assert(sumaPrev<=a.getSuma());
		if(sb.toString().contains("has been added to your SAVING ACCOUNT")||
				sb.toString().contains("has been added to your SPENDING ACCOUNT")) {
			totalInBank+=suma;
		}
		
		assert(isWellFormed());
		writeData();
		return sb.toString();
	}
	public void composeMessage(String s1) {
		s=s1;
	}
	public String getS() {
		return s;
	}
	public String withdrawMoneyFromAccount(int accountID, double suma) {
		// TODO Auto-generated method stub
		StringBuilder sb=new StringBuilder();
		assert(isWellFormed());
		Account a=findAccount(accountID);
		assert(a!=null);
		double prevSuma=a.getSuma();
		Person p = null;
		for (Entry<Person, List<Account>> e : info.entrySet()) {
			for (Account a1 : e.getValue()) {
				if(a1.getId()==a.getId()) {
					p = findPerson(e.getKey().getId());
				}
				
			}
		}
		a.addObserver(p);
		sb.append(a.withdrawMoney(suma));
		composeMessage(sb.toString());

		assert(prevSuma>=a.getSuma());
		if(sb.toString().contains("You made a withdrawal")) {
			totalInBank-=suma;
		}
		
		assert(isWellFormed());
		writeData();
		return sb.toString();
	}

}
