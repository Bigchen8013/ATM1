package unit_test;

public class ATMService {
	
	private IDBConnection dbconn;
	
	public ATMService(IDBConnection db)
	{
		this.dbconn=db; 
	}
	
	//提供取款
	public void withDraw(String cardNo,int amount)
	{
		Account a=dbconn.getAccount(cardNo); 
		double balance=a.getBalance()-amount; 
		if(balance<0) {
			double couldWithDraw=a.balance;
			System.out.println("金额超出余额！！您最多可以取"+couldWithDraw);
		}
		else {
			a.setBalance(balance); 
		}
		dbconn.updateAccount(a); 
	}
	
	//存款
	public void deposit(String cardNo, int amount)
	{
		Account a=dbconn.getAccount(cardNo); 
		/*
		 *应该是+amount
		int balance=a.getBalance()-amount; 
		*/
		double balance=a.getBalance()+amount; 
		a.setBalance(balance); 
		dbconn.updateAccount(a); 
	}
	
	//转账from a to b
	public void transfer(String fromCardNo, String toCardNo, int amount)
	{
		Account a=dbconn.getAccount(fromCardNo);
		Account b=dbconn.getAccount(toCardNo); 
		
		double aBalance=a.getBalance()-amount; 
		double bBalance=b.getBalance()+amount; 
		
		if(aBalance<0) {
			double couldTransfer=a.balance;
			System.out.println("转账金额超过余额！！您最多可以转账"+couldTransfer);
		}
		else {
			a.setBalance(aBalance); 
			b.setBalance(bBalance); 
		}
		
		dbconn.updateAccount(a); 
		dbconn.updateAccount(a);
	} 
	
	//查询服务
	public double inquiry(String cardNo)
	{ 
		Account a=dbconn.getAccount(cardNo); 
		return a.getBalance(); 
	}
	

}


