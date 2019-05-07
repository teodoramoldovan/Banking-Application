package BankClients;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Observable;
import java.util.Observer;

@SuppressWarnings("serial")
public class Person implements Observer,Serializable {
	
	
	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	private long id;
	private String nume;
	private String prenume;
	private LocalDate birthday;
	private long cnp;
	
	
	
	public Person( String nume, String prenume, LocalDate birthday,long cnp) {
		
		this.id = (long)cnp%1000;
		this.nume = nume;
		this.prenume = prenume;
		this.birthday = birthday;
		this.cnp=cnp;
	}
	
	public long getId() {
		return id;
	}
	public String getNume() {
		return nume;
	}
	public void setNume(String nume) {
		this.nume = nume;
	}
	public String getPrenume() {
		return prenume;
	}
	public void setPrenume(String prenume) {
		this.prenume = prenume;
	}
	public LocalDate getBirthday() {
		return birthday;
	}
	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}
	
	public long getCnp() {
		return cnp;
	}

	public void setCnp(long cnp) {
		this.cnp = cnp;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int)cnp%1000;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (cnp != other.cnp)
			return false;
		return true;
	}
	public String message(String s) {
		StringBuilder sb=new StringBuilder();
		sb.append(s);
		return sb.toString();
	}
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		StringBuilder sb=new StringBuilder();
		sb.append("Client "+nume+" "+prenume+","+arg.toString());
		message(sb.toString());
		System.out.println(sb.toString());
	}
	
	
	
	

}
