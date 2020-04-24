package beans;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "Wallet")
public class Wallet {
	@Id
	@Column(name = "AccNo", length = 15)
	private long accNo;
	@Column(name = "BankName")
	private String bankName;
	@Column(name = "Balance")
	private double balance;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "CustomerId", referencedColumnName = "Id")
	private Customer customer;

	@OneToMany
	private List<Transaction> transactions;
}
