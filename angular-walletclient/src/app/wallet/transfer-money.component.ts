import { Component, OnInit } from "@angular/core";
import { Router, ActivatedRoute } from "@angular/router";
import { WalletService } from "../wallet.service";

@Component({
  selector: "app-transfer",
  templateUrl: "transfer-money.component.html",
})
export class TransferMoneyComponent implements OnInit {
  title = "Transfer Money";
  accNo: number;
  accNoB: number;
  amount: number;
  balance: number;
  error1: {};

  ngOnInit() {
    this.accNo = this.walletService.wallet.accNo;
    this.balance = this.walletService.wallet.balance;
  }

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private walletService: WalletService
  ) {}

  onSubmit() {
    if (
      this.walletService.accountList.includes(this.accNoB) == false ||
      this.accNoB <= 0
    ) {
      alert("Enter a Valid Beneficiary Account Number !!");
      this.accNoB = 0;
    } else if (this.amount <= 0) {
      alert("Enter a Valid Amount !!");
    } else if (this.balance < this.amount) {
      alert(
        "Sufficient Balance not Available !!\nAvailable Balance : " +
          this.balance +
          "/-"
      );
      this.amount = 0;
    } else {
      this.walletService
        .transferMoney(this.accNo, this.accNoB, this.amount)
        .subscribe((error) => console.log(error));
      this.walletService.wallet.balance -= this.amount;
      alert("Amount Transferred Successfully !!");
      this.router.navigate(["menu"]);
    }
  }

  goToMenu() {
    this.router.navigate(["menu"]);
  }
}
