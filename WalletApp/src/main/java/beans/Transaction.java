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

}
