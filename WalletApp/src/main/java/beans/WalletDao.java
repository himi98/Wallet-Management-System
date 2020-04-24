package beans;

import java.util.List;

public interface WalletDao {
	public Wallet createAccount(Wallet wt);

	public void depositMoney(long accNo, double amt, Transaction tr);

	public void withdrawMoney(long accNo, double amt, Transaction tr);

	public void transferMoney(long accNo, long accNoB, double amt, Transaction tr, Transaction trB);

	public Wallet viewAccount(long custId);

	public List<Transaction> viewTransactions(long accNo);

	public Wallet validateCustomer(long cid);
}
