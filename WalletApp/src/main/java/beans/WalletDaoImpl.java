package beans;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import lombok.Data;

@Repository
@EnableTransactionManagement
@Data
public class WalletDaoImpl implements WalletDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public Wallet createAccount(Wallet wt) {
		entityManager.persist(wt);
		Wallet wallet = viewAccount(wt.getCustomer().getCustId());
		return wallet;
		// TODO Auto-generated method stub
	}

	@Override
	public Wallet viewAccount(long custId) {
		// TODO Auto-generated method stub
		TypedQuery<Wallet> q2 = entityManager
				.createQuery("SELECT wallet FROM Wallet wallet where customer_id=" + custId, Wallet.class);
		Wallet wallet = q2.getSingleResult();
		return wallet;
	}

	@Override
	public Wallet validateCustomer(long cid) {
		// TODO Auto-generated method stub
		TypedQuery<Wallet> q2 = entityManager.createQuery("SELECT wallet FROM Wallet wallet where customer_id=" + cid,
				Wallet.class);
		Wallet wallet = q2.getSingleResult();
		return wallet;
	}

	@Override
	public void depositMoney(long accNo, double amt, Transaction tr) {
		// TODO Auto-generated method stub
		Wallet wallet = entityManager.find(Wallet.class, accNo);
		wallet.setBalance(wallet.getBalance() + amt);
		tr.setUpdatedBalance(wallet.getBalance());
		entityManager.persist(tr);
	}

	@Override
	public void withdrawMoney(long accNo, double amt, Transaction tr) {
		// TODO Auto-generated method stub
		Wallet wallet = entityManager.find(Wallet.class, accNo);
		wallet.setBalance(wallet.getBalance() - amt);
		tr.setUpdatedBalance(wallet.getBalance());
		entityManager.persist(tr);
	}

	@Override
	public void transferMoney(long accNo, long accNoB, double amt, Transaction tr, Transaction trB) {
		// TODO Auto-generated method stub
		Wallet wallet = entityManager.find(Wallet.class, accNo);
		wallet.setBalance(wallet.getBalance() - amt);
		Wallet walletB = entityManager.find(Wallet.class, accNoB);
		walletB.setBalance(walletB.getBalance() + amt);
		tr.setUpdatedBalance(wallet.getBalance());
		trB.setUpdatedBalance(walletB.getBalance());
		entityManager.persist(tr);
		entityManager.persist(trB);
	}

	@Override
	public List<Transaction> viewTransactions(long accNo) {
		// TODO Auto-generated method stub
		Wallet wallet = entityManager.find(Wallet.class, accNo);
		TypedQuery<Transaction> q2 = entityManager
				.createQuery("SELECT tr FROM Transaction tr where source_acc=" + wallet.getAccNo(), Transaction.class);
		List<Transaction> transactions = q2.getResultList();
		return transactions;
	}

	@Override
	public List<BigDecimal> getAccountList() {
		// TODO Auto-generated method stub
		Query q2 = entityManager
				.createNativeQuery("SELECT wt.Acc_No FROM Wallet wt");
		List<BigDecimal> accountList = q2.getResultList();
		return accountList;
	}

}
