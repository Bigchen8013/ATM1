package unit_test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestATMService {
	
	//���һ���µ��˻�
	Account a1=new Account("3","333333",500);
	//���Ѵ��ڵ�1�˻�������ͬ�����Ƿ�����Ӵ��˻�
	Account a2=new Account("1", "1212", 300);
	
	MockDbConnection mDbConnection=new MockDbConnection();
	
	ATMService atm1=new ATMService(mDbConnection);

	
	//ȡ��
	@Test
	void testWithDraw() {
		atm1.withDraw("1", 100);
		assertEquals(200,atm1.inquiry("1") );
		
		//ȡ��������������ʧ��
		atm1.withDraw("1", 201);
		assertEquals(200, atm1.inquiry("1"));
		
		atm1.withDraw("2", 1000);
		assertEquals(1000, atm1.inquiry("2"));
		
		//ȡ��������������ʧ��
		atm1.withDraw("2", 1001);
		assertEquals(1000, atm1.inquiry("2"));
	}

	//���
	@Test
	void testDeposit() {
		atm1.deposit("1", 100);
		assertEquals(400,atm1.inquiry("1"));
		
		atm1.deposit("2", 200);
		assertEquals(2200, atm1.inquiry("2"));
	}

	//ת��
	@Test
	void testTransfer() {
		
		//ת�˳������������ת��ʧ��
		atm1.transfer("1", "2", 301);
		assertEquals(300, atm1.inquiry("1"));
		assertEquals(2000, atm1.inquiry("2"));
		
		//ת�˳������������ת��ʧ��
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

	//��ѯ�˻����
	@Test
	void testInquiry() {
		assertEquals(300, atm1.inquiry("1"));
		assertEquals(2000, atm1.inquiry("2"));
	}

	//����˻�
	@Test
	void testAddAccount() {
		mDbConnection.addAccount(a1);
		assertEquals(500, atm1.inquiry("3"));
		
		mDbConnection.addAccount(a2);
		assertEquals(300, atm1.inquiry("1"));
		
	}
	
	
}
