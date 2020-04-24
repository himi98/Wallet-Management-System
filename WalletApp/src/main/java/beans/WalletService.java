package beans;

import java.util.List;

public interface WalletService {
	public Wallet createAccount(Wallet wt);

	public void depositMoney(long accNo, double amt);

	public void withdrawMoney(long accNo, double amt);

	public void transferMoney(long accNo, long accNoB, double amt);

	public Wallet viewAccount(long custId);

	public List<Transaction> viewTransactions(long accNo);

	public Wallet validateCustomer(long cid);

}
