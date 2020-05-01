package beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "Transaction")
public class Transaction {
	@Id
	@Column(name = "TransactionId", length = 11)
	private long transactionId;
	@Column(name = "SourceAcc")
	private long sourceAcc;
	@Column(name = "DestAcc")
	private Long destAcc;
	@Column(name = "Type")
	private String type;
	@Column(name = "Amount")
	private double amount;
	@Column(name = "TimeStamp")
	private String timeStamp;
	@Column(name = "UpdatedBalance")
	private double updatedBalance;
	
	public Transaction(long transactionId, long sourceAcc, Long destAcc, String type, double amount, String timeStamp,
			double updatedBalance) {
		super();
		this.transactionId = transactionId;
		this.sourceAcc = sourceAcc;
		this.destAcc = destAcc;
		this.type = type;
		this.amount = amount;
		this.timeStamp = timeStamp;
		this.updatedBalance = updatedBalance;
	}

	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}



}
