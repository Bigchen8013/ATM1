package unit_test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestATMService {
	
	//添加一个新的账户
	Account a1=new Account("3","333333",500);
	//与已存在的1账户卡号相同，看是否能添加此账户
	Account a2=new Account("1", "1212", 300);
	
	MockDbConnection mDbConnection=new MockDbConnection();
	
	ATMService atm1=new ATMService(mDbConnection);

	
	//取款
	@Test
	void testWithDraw() {
		atm1.withDraw("1", 100);
		assertEquals(200,atm1.inquiry("1") );
		
		//取款超出余额的情况操作失败
		atm1.withDraw("1", 201);
		assertEquals(200, atm1.inquiry("1"));
		
		atm1.withDraw("2", 1000);
		assertEquals(1000, atm1.inquiry("2"));
		
		//取款超出余额的情况操作失败
		atm1.withDraw("2", 1001);
		assertEquals(1000, atm1.inquiry("2"));
	}

	//存款
	@Test
	void testDeposit() {
		atm1.deposit("1", 100);
		assertEquals(400,atm1.inquiry("1"));
		
		atm1.deposit("2", 200);
		assertEquals(2200, atm1.inquiry("2"));
	}

	//转账
	@Test
	void testTransfer() {
		
		//转账超过余额的情况，转账失败
		atm1.transfer("1", "2", 301);
		assertEquals(300, atm1.inquiry("1"));
		assertEquals(2000, atm1.inquiry("2"));
		
		//转账超过余额的情况，转账失败
		atm1.transfer("2", "1", 2001);
		assertEquals(300, atm1.inquiry("1"));
		assertEquals(2000, atm1.inquiry("2"));
		
		atm1.transfer("1","2", 300);
		assertEquals(0, atm1.inquiry("1"));
		assertEquals(2300, atm1.inquiry("2"));
		
		atm1.transfer("2", "1", 2000);
		assertEquals(2000, atm1.inquiry("1"));
		assertEquals(300, atm1.inquiry("2"));
		
		
	}

	//查询账户余额
	@Test
	void testInquiry() {
		assertEquals(300, atm1.inquiry("1"));
		assertEquals(2000, atm1.inquiry("2"));
	}

	//添加账户
	@Test
	void testAddAccount() {
		mDbConnection.addAccount(a1);
		assertEquals(500, atm1.inquiry("3"));
		
		mDbConnection.addAccount(a2);
		assertEquals(300, atm1.inquiry("1"));
		
	}
	
	
}
