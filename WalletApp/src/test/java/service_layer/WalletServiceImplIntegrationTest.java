package service_layer;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import beans.Customer;
import beans.RandomNumberGenerator;
import beans.Transaction;
import beans.Wallet;
import beans.WalletDao;
import beans.WalletService;
import beans.WalletServiceImpl;

@RunWith(SpringRunner.class)
public class WalletServiceImplIntegrationTest {

	@TestConfiguration
	static class WalletServiceImplTestContextConfiguation {
		@Bean
		public WalletService walletService() {
			return new WalletServiceImpl();
		}
	}
	
	@Autowired
	private WalletService walletService;
	
	@MockBean
	private WalletDao walletDao;
	
	private RandomNumberGenerator randomNumberGenerator;
	private Customer cust,custB;
	private Wallet wt,wtB;
	
	@Before
	public void givenData() {
		//given
		randomNumberGenerator=new RandomNumberGenerator();
		//Customer
		cust=new Customer("Himanshu",0,"Vadodara","Male","1998-11-11");
		wt=new Wallet(123456789,"Test Bank",0,cust,null);
		//Beneficiary Customer
		custB=new Customer("Rahul",0,"Pune","Male","1997-12-09");
		wtB=new Wallet(987654321,"Test Bank",0,custB,null);
	}
	
	@Test
	public void whenCreateAccount_thenAccountCreated() {
		//when
		Customer customer=wt.getCustomer();
		customer.setCustId(randomNumberGenerator.generateRandom(7));
		wt.setCustomer(customer);
		Wallet wt1=walletDao.viewAccount(customer.getCustId());
		Customer cust1=wt1.getCustomer();
		
		//then
		assertThat(cust1.getCustId()).isEqualTo(customer.getCustId());
	}
	
	@Test
	public void whenViewAccount_thenReturnWallet() {
		//given
		Customer customer=wt.getCustomer();
		customer.setCustId(randomNumberGenerator.generateRandom(7));
		wt.setCustomer(customer);
		
		//when
		Wallet wt1=walletDao.viewAccount(customer.getCustId());
		Customer cust1=wt1.getCustomer();
		
		
		//then
		assertThat(cust1.getCustId()).isEqualTo(customer.getCustId());
	}
	
	@Test
	public void whenValidateCustomer_thenCustomerValidated( ) {
		//given
		Customer customer=wt.getCustomer();
		customer.setCustId(randomNumberGenerator.generateRandom(7));
		wt.setCustomer(customer);
				
		//when
		Wallet wt1=walletDao.validateCustomer(customer.getCustId());
		Customer cust1=wt1.getCustomer();
				
		//then
		assertThat(cust1.getCustId()).isEqualTo(customer.getCustId());
	}
	
	@Test
	public void whenDepositMoney_thenMoneyDeposited() {
		//given
		Customer customer=wt.getCustomer();
		customer.setCustId(randomNumberGenerator.generateRandom(7));
		wt.setCustomer(customer);
		long accNo=123456789;
		double amt=100;
		
		//when
		Transaction tr=new Transaction(randomNumberGenerator.generateRandom(11), accNo,null, "Credit", amt,getTimeStamp().toString(),wt.getBalance());
		walletDao.depositMoney(accNo, amt, tr);
		
		//then
		assertThat(wt.getBalance()).isEqualTo(tr.getUpdatedBalance()+amt);
	}
	
	@Test
	public void whenWithdrawMoney_thenMoneyWithdrawn() {
		//given
		Customer customer=wt.getCustomer();
		customer.setCustId(randomNumberGenerator.generateRandom(7));
		wt.setCustomer(customer);
		long accNo=123456789;
		double amt=50;
		
		//when
		Transaction tr=new Transaction(randomNumberGenerator.generateRandom(11), accNo,null, "Debit", amt,getTimeStamp().toString(),wt.getBalance());
		walletDao.withdrawMoney(accNo, amt, tr);
		Wallet wallet=walletDao.viewAccount(wt.getCustomer().getCustId());
		
		//then
		System.out.println(wt.getBalance());
		assertThat(wallet.getBalance()).isEqualTo(tr.getUpdatedBalance()-amt);
	}
	
	@Test
	public void whenTransferMoney_thenMoneyTransferred() {
		//given
		Customer customer=wt.getCustomer();
		customer.setCustId(randomNumberGenerator.generateRandom(7));
		wt.setCustomer(customer);
		Customer customer1=wtB.getCustomer();
		customer1.setCustId(randomNumberGenerator.generateRandom(7));
		wtB.setCustomer(customer1);
		long accNo=123456789;
		long accNoB=987654321;
		double amt=25;
		
		//when
		Transaction tr=new Transaction(randomNumberGenerator.generateRandom(11), accNo,accNoB, "Transfer", amt,getTimeStamp().toString(),wt.getBalance());
		Transaction trB=new Transaction(randomNumberGenerator.generateRandom(11), accNoB,null, "Credit (Transfer)", amt,getTimeStamp().toString(),wtB.getBalance());
		walletDao.transferMoney(accNo, accNoB, amt, tr, trB);
		
		//then
		assertThat(wt.getBalance()).isEqualTo(tr.getUpdatedBalance()-amt);
		assertThat(wtB.getBalance()).isEqualTo(trB.getUpdatedBalance()+amt);
	}
	
	@Test
	public void whenViewTransactions_thenReturnListOfTransactions() {
		//given
		Customer customer=wt.getCustomer();
		customer.setCustId(randomNumberGenerator.generateRandom(7));
		wt.setCustomer(customer);
		long accNo=123456789;
		
		//when
		List<Transaction> transactions=walletDao.viewTransactions(accNo);
		
		//then
		assertThat(transactions).isNotEqualTo(null);
	}
	
	public Timestamp getTimeStamp() {
		Date date = new Date();
		long time = date.getTime();
		Timestamp ts = new Timestamp(time);
		return ts;
	}
}
