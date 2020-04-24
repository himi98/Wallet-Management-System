package mvc;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import beans.Transaction;
import beans.Wallet;
import beans.WalletService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api/v1/wallets")
public class WalletController {

	@Autowired
	WalletService wtServ;

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<Wallet> createAccount(@Valid @RequestBody Wallet wt) {
		Wallet wallet = wtServ.createAccount(wt);
		return ResponseEntity.ok().body(wallet);
	}

	@RequestMapping(value = "/validate", method = RequestMethod.POST)
	public ResponseEntity<Wallet> validateCustomer(@Valid @RequestBody String cid) throws EntityNotFoundException {
		Wallet wallet = wtServ.validateCustomer(Long.parseLong(cid));
		if (wallet == null) {
			throw new EntityNotFoundException();
		}
		return ResponseEntity.ok().body(wallet);
	}

	@RequestMapping(value = "/deposit", method = RequestMethod.POST)
	public ResponseEntity<HttpStatus> depositMoney(@Valid @RequestBody String[] details) {
		wtServ.depositMoney(Long.parseLong(details[0]), Double.parseDouble(details[1]));
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@RequestMapping(value = "/withdraw", method = RequestMethod.POST)
	public ResponseEntity<HttpStatus> withdrawMoney(@Valid @RequestBody String[] details) {
		wtServ.withdrawMoney(Long.parseLong(details[0]), Double.parseDouble(details[1]));
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@RequestMapping(value = "/transfer", method = RequestMethod.POST)
	public ResponseEntity<HttpStatus> transferMoney(@Valid @RequestBody String[] details) {
		wtServ.transferMoney(Long.parseLong(details[0]), Long.parseLong(details[1]), Double.parseDouble(details[2]));
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@RequestMapping(value = "/viewAccount", method = RequestMethod.POST)
	public ResponseEntity<Wallet> viewAccount(@Valid @RequestBody String cid) {
		Wallet wallet = wtServ.viewAccount(Long.parseLong(cid));
		return ResponseEntity.ok().body(wallet);
	}

	@RequestMapping(value = "/viewTransactions", method = RequestMethod.POST)
	public ResponseEntity<List<Transaction>> viewTransactions(@Valid @RequestBody String accNo) {
		List<Transaction> transactions = wtServ.viewTransactions(Long.parseLong(accNo));
		return ResponseEntity.ok().body(transactions);
	}

}
