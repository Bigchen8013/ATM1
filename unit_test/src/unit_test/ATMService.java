package unit_test;

public class ATMService {
	
	private IDBConnection dbconn;
	
	public ATMService(IDBConnection db)
	{
		this.dbconn=db; 
	}
	
	//�ṩȡ��
	public void withDraw(String cardNo,int amount)
	{
		Account a=dbconn.getAccount(cardNo); 
		double balance=a.getBalance()-amount; 
		if(balance<0) {
			double couldWithDraw=a.balance;
			System.out.println("����������������ȡ"+couldWithDraw);
		}
		else {
			a.setBalance(balance); 
		}
		dbconn.updateAccount(a); 
	}
	
	//���
	public void deposit(String cardNo, int amount)
	{
		Account a=dbconn.getAccount(cardNo); 
		/*
		 *Ӧ����+amount
		int balance=a.getBalance()-amount; 
		*/
		double balance=a.getBalance()+amount; 
		a.setBalance(balance); 
		dbconn.updateAccount(a); 
	}
	
	//ת��from a to b
	public void transfer(String fromCardNo, String toCardNo, int amount)
	{
		Account a=dbconn.getAccount(fromCardNo);
		Account b=dbconn.getAccount(toCardNo); 
		
		double aBalance=a.getBalance()-amount; 
		double bBalance=b.getBalance()+amount; 
		
		if(aBalance<0) {
			double couldTransfer=a.balance;
			System.out.println("ת�˽���������������ת��"+couldTransfer);
		}
		else {
			a.setBalance(aBalance); 
			b.setBalance(bBalance); 
		}
		
		dbconn.updateAccount(a); 
		dbconn.updateAccount(a);
	} 
	
	//��ѯ����
	public double inquiry(String cardNo)
	{ 
		Account a=dbconn.getAccount(cardNo); 
		return a.getBalance(); 
	}
	

}


