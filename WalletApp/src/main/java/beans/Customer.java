package beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "Customer")
public class Customer {
	@Column(name = "Name")
	private String custName;
	@Id
	@Column(name = "Id", length = 7)
	private long custId;
	@Column(name = "Address")
	private String address;
	@Column(name = "Gender")
	private String gender;
	@Column(name = "Dob")
	private String dob;

}
