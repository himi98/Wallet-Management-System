package persistence_layer;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.WalletApp.WalletApplication;

import beans.Customer;
import beans.Transaction;
import beans.Wallet;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ContextConfiguration(classes= {WalletApplication.class})
public class WalletDaoImplIntegrationTest {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	@PersistenceContext
	EntityManager entityManager;
	
	private Customer cust,custB;
	private Wallet wt,wtB;
	
	@Before
	public void givenData() {
		//given
		//Customer
		cust=new Customer("Himanshu",3425673,"Vadodara","Male","1998-11-11");
		wt=new Wallet(123456789,"Test Bank",0,cust,null);
		//Beneficiary Customer
		custB=new Customer("Rahul",2673827,"Pune","Male","1997-12-09");
		wtB=new Wallet(987654321,"Test Bank",0,custB,null);
	}
	
	@Test
	public void whenCreateAccount_thenAccountCreated() {
		//when
		entityManager.persist(wt);
		Wallet wt1=viewAccount(cust.getCustId());
		Customer cust1=wt1.getCustomer();
		entityManager.flush();
		
		//then
		assertThat(cust1.getCustId()).isEqualTo(cust.getCustId());
	}
	
	@Test
	public void whenViewAccount_thenReturnWallet() {
		//given
		entityManager.persist(wt);
		
		//when
		TypedQuery<Wallet> q2 = entityManager
				.createQuery("SELECT wallet FROM Wallet wallet where customer_id=" + cust.getCustId(), Wallet.class);
		Wallet wallet = q2.getSingleResult();
		Customer customer=wallet.getCustomer();
		entityManager.flush();
		
		//then
		assertThat(customer.getCustId()).isEqualTo(cust.getCustId());
	}
	
	@Test
	public void whenValidateCustomer_thenCustomerValidated( ) {
		//given
		entityManager.persist(wt);
				
		//when
		TypedQuery<Wallet> q2 = entityManager
			.createQuery("SELECT wallet FROM Wallet wallet where customer_id=" + cust.getCustId(), Wallet.class);
		Wallet wallet = q2.getSingleResult();
		Customer customer=wallet.getCustomer();
		entityManager.flush();
				
		//then
		assertThat(customer.getCustId()).isEqualTo(cust.getCustId());
	}
	
	@Test
	public void whenDepositMoney_thenMoneyDeposited() {
		//given
		entityManager.persist(wt);
		long accNo=123456789;
		double amt=100;
		Transaction tr=new Transaction(12345678912L, accNo,null, "Credit", amt,"Test Timestamp",wt.getBalance());
		
		//when
		Wallet wallet=entityManager.find(Wallet.class, accNo);
		wallet.setBalance(wallet.getBalance()+amt);
		tr.setUpdatedBalance(wallet.getBalance());
		entityManager.persist(tr);
		entityManager.flush();
		
		//then
		Transaction test=entityManager.find(Transaction.class,tr.getTransactionId());
		assertThat(test.getUpdatedBalance()).isEqualTo(wallet.getBalance());
	}
	
	@Test
	public void whenWithdrawMoney_thenMoneyWithdrawn() {
		//given
		entityManager.persist(wt);
		long accNo=123456789;
		double amt=50;
		Transaction tr=new Transaction(12345678912L, accNo,null, "Debit", amt,"Test Timestamp",wt.getBalance());
		
		//when
		Wallet wallet=entityManager.find(Wallet.class, accNo);
		wallet.setBalance(wallet.getBalance()-amt);
		tr.setUpdatedBalance(wallet.getBalance());
		entityManager.persist(tr);
		entityManager.flush();
		
		//then
		Transaction test=entityManager.find(Transaction.class,tr.getTransactionId());
		assertThat(test.getUpdatedBalance()).isEqualTo(wallet.getBalance());
	}
	
	@Test
	public void whenTransferMoney_thenMoneyTransferred() {
		//given
		entityManager.persist(wt);
		entityManager.persist(wtB);
		long accNo=123456789;
		long accNoB=987654321;
		double amt=25;
		Transaction tr=new Transaction(12345678912L, accNo,accNoB, "Transfer", amt,"Test Timestamp",wt.getBalance());
		Transaction trB=new Transaction(98765432198L, accNoB,null, "Credit (Transfer)", amt,"Test Timestamp",wtB.getBalance());
		
		//when
		Wallet wallet=entityManager.find(Wallet.class, accNo);
		Wallet walletB=entityManager.find(Wallet.class, accNoB);
		wallet.setBalance(wallet.getBalance()-amt);
		walletB.setBalance(walletB.getBalance()+amt);
		tr.setUpdatedBalance(wallet.getBalance());
		trB.setUpdatedBalance(walletB.getBalance());
		entityManager.persist(tr);
		entityManager.persist(trB);
		entityManager.flush();
		
		//then
		Transaction test=entityManager.find(Transaction.class,tr.getTransactionId());
		Transaction testB=entityManager.find(Transaction.class, trB.getTransactionId());
		assertThat(test.getUpdatedBalance()).isEqualTo(wallet.getBalance());
		assertThat(testB.getUpdatedBalance()).isEqualTo(walletB.getBalance());
	}
	
	@Test
	public void whenViewTransactions_thenReturnListOfTransactions() {
		//given
		entityManager.persist(wt);
		long accNo=123456789;
		
		//when
		Wallet wallet=entityManager.find(Wallet.class, accNo);
		TypedQuery<Transaction> q2 = entityManager
				.createQuery("SELECT tr FROM Transaction tr where source_acc=" + wallet.getAccNo(), Transaction.class);
		List<Transaction> transactions = q2.getResultList();
		entityManager.flush();
		
		//then
		assertThat(transactions).isNotEqualTo(null);
	}
	
	public Wallet viewAccount(long custId) {
		// TODO Auto-generated method stub
		TypedQuery<Wallet> q2 = entityManager
				.createQuery("SELECT wallet FROM Wallet wallet where customer_id=" + custId, Wallet.class);
		Wallet wallet = q2.getSingleResult();
		return wallet;
	}

}
