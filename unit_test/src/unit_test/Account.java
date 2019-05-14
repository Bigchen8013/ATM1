package unit_test;

public class Account {
	
	String cardNo; 
	String password;
	double balance;
	
	public Account(String cardNo,String password,double balance)
	{
		this.cardNo=cardNo; 
		this.password=password; 
		this.balance=balance;
	}
	
	//���Ų����Լ�����
	public void setCardNo(String cardNo)
	{
		this.cardNo=cardNo; 
	}
	
	public void setPassword(String ps)
	{
		this.password=ps;
	} 
	
	public void setBalance(double amount)
	{
		this.balance=amount; 
	}
	
	public String getCardNo()
	{
		return this.cardNo; 
	} 
	
	public String getPassword()
	{
		return this.password; 
	}
	
	public double getBalance()
	{
		return this.balance; 
	}
	
}
