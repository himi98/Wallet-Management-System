import { Component, OnInit } from "@angular/core";
import { Router, ActivatedRoute } from "@angular/router";
import { WalletService } from "../wallet.service";

@Component({
  selector: "app-deposit",
  templateUrl: "deposit-money.component.html",
})
export class DepositMoneyComponent implements OnInit {
  title = "Deposit Money";
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
    } else {
      this.walletService
        .depositMoney(this.accNo, this.amount)
        .subscribe((error) => console.log(error));
      this.walletService.wallet.balance += this.amount;
      alert("Amount Deposited Successfully !!");
      this.router.navigate(["menu"]);
    }
  }

  goToMenu() {
    this.router.navigate(["menu"]);
  }
}
