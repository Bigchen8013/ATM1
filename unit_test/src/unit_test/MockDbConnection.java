package unit_test;

import java.util.HashMap;

public class MockDbConnection implements IDBConnection{
	
	private HashMap<String,Account> mdb;
	
	public MockDbConnection()
	{
		mdb=new HashMap<String,Account>(); 
		Account a=new Account("1","11111",300); 
		Account b=new Account("2","22222",2000);

		
	   mdb.put(a.getCardNo(),a);
	   mdb.put(b.getCardNo(),b);
	}
	public Account getAccount(String cardNo)
	{
		//ĞèÌî³ä±àĞ´´úÂë
		Account account=null;
		for(String keyString:mdb.keySet())
		{
			if(cardNo==keyString)
			{
				account=mdb.get(keyString);
			}
		}
		return account;
	}
	public void updateAccount(Account a)
	{
		//ĞèÌî³ä±àĞ´´úÂë 
		for(String keyString:mdb.keySet()) {
			if(a.cardNo==keyString) {
				mdb.get(keyString).cardNo=a.cardNo;
				mdb.get(keyString).password=a.password;
				mdb.get(keyString).balance=a.balance;
			}
		}
	} 
	public void addAccount(Account a)
	{ 
		//ĞèÌî³ä±àĞ´´úÂë 
		boolean flag=true;
		for(String keyString:mdb.keySet()) {
			if(keyString==a.cardNo) {
				flag=false;
			}
		}
		if(flag) {
			mdb.put(a.getCardNo(), a);
		}
	}

}
