package beans;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.Data;

@Service
@Transactional
@Data
public class WalletServiceImpl implements WalletService {

	@Autowired
	WalletDao wtDao;

	private RandomNumberGenerator randomNumberGenerator;

	private Transaction tr;

	private Timestamp timeStamp;

	@Override
	public Wallet createAccount(Wallet wt) {
		randomNumberGenerator = new RandomNumberGenerator();
		long customerId = randomNumberGenerator.generateRandom(7);
		Customer cust = wt.getCustomer();
		cust.setCustId(customerId);
		wt.setCustomer(cust);
		Wallet wallet = wtDao.createAccount(wt);
		return wallet;
		// TODO Auto-generated method stub
	}

	@Override
	public Wallet viewAccount(long custId) {
		// TODO Auto-generated method stub
		Wallet wallet = wtDao.viewAccount(custId);
		return wallet;
	}

	@Override
	public Wallet validateCustomer(long cid) {
		// TODO Auto-generated method stub
		Wallet wallet = wtDao.validateCustomer(cid);
		return wallet;
	}

	@Override
	public void depositMoney(long accNo, double amt) {
		// TODO Auto-generated method stub
		randomNumberGenerator = new RandomNumberGenerator();
		long tid = randomNumberGenerator.generateRandom(11);
		tr = new Transaction();
		timeStamp = getTimeStamp();
		tr.setTransactionId(tid);
		tr.setSourceAcc(accNo);
		tr.setDestAcc(null);
		tr.setType("Credit");
		tr.setAmount(amt);
		tr.setTimeStamp(timeStamp.toString());
		wtDao.depositMoney(accNo, amt, tr);
	}

	@Override
	public void withdrawMoney(long accNo, double amt) {
		// TODO Auto-generated method stub
		randomNumberGenerator = new RandomNumberGenerator();
		long tid = randomNumberGenerator.generateRandom(11);
		tr = new Transaction();
		timeStamp = getTimeStamp();
		tr.setTransactionId(tid);
		tr.setSourceAcc(accNo);
		tr.setDestAcc(null);
		tr.setType("Debit");
		tr.setAmount(amt);
		tr.setTimeStamp(timeStamp.toString());
		wtDao.withdrawMoney(accNo, amt, tr);
	}

	@Override
	public void transferMoney(long accNo, long accNoB, double amt) {
		// TODO Auto-generated method stub
		randomNumberGenerator = new RandomNumberGenerator();
		long tid = randomNumberGenerator.generateRandom(11);
		long tidB = randomNumberGenerator.generateRandom(11);
		tr = new Transaction();
		Transaction trB = new Transaction();
		timeStamp = getTimeStamp();
		tr.setTransactionId(tid);
		tr.setSourceAcc(accNo);
		tr.setDestAcc(accNoB);
		tr.setType("Transfer");
		tr.setAmount(amt);
		tr.setTimeStamp(timeStamp.toString());
		trB.setTransactionId(tidB);
		trB.setSourceAcc(accNoB);
		trB.setDestAcc(null);
		trB.setType("Credit (Transfer)");
		trB.setAmount(amt);
		trB.setTimeStamp(timeStamp.toString());
		wtDao.transferMoney(accNo, accNoB, amt, tr, trB);
	}

	@Override
	public List<Transaction> viewTransactions(long accNo) {
		// TODO Auto-generated method stub
		List<Transaction> transactions = wtDao.viewTransactions(accNo);
		return transactions;
	}

	public Timestamp getTimeStamp() {
		Date date = new Date();
		long time = date.getTime();
		Timestamp ts = new Timestamp(time);
		return ts;
	}

	@Override
	public List<BigDecimal> getAccountList() {
		// TODO Auto-generated method stub
		return wtDao.getAccountList();
	}
	
}
