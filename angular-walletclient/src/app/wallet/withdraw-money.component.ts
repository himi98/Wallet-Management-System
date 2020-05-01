import { Component, OnInit } from "@angular/core";
import { Router, ActivatedRoute } from "@angular/router";
import { WalletService } from "../wallet.service";

@Component({
  selector: "app-withdraw",
  templateUrl: "withdraw-money.component.html",
})
export class WithdrawMoneyComponent implements OnInit {
  title = "Withdraw Money";
  accNo: number;
  amount: number;
  balance: number;
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
    if (this.amount <= 0) {
      alert("Enter a Valid Amount !!");
      this.amount = 0;
    } else if (this.balance < this.amount) {
      alert(
        "Sufficient Balance not Available !!\nAvailable Balance : " +
          this.balance +
          "/-"
      );
      this.amount = 0;
    } else {
      this.walletService
        .withdrawMoney(this.accNo, this.amount)
        .subscribe((error) => console.log(error));
      this.walletService.wallet.balance -= this.amount;
      alert("Amount Withdrawn Successfully !!");
      this.router.navigate(["menu"]);
    }
  }

  goToMenu() {
    this.router.navigate(["menu"]);
  }
}
