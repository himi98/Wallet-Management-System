import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { WalletService } from "../wallet.service";
import { Transaction } from "./transaction";

@Component({
  selector: "app-view-transactions",
  templateUrl: "view-transactions.component.html",
})
export class ViewTransactionsComponent implements OnInit {
  title = "Transaction History";
  accNo: number;
  transactions: Transaction[];
  ngOnInit() {
    this.accNo = this.walletService.wallet.accNo;
    this.walletService.viewTransactions(this.accNo).subscribe(
      (data) => this.storeTransactions(data),
      (error) => console.log(error)
    );
  }

  storeTransactions(data) {
    for (let transaction of data) {
      if (
        transaction.type == "Credit" ||
        transaction.type == "Debit" ||
        transaction.type == "Credit (Transfer)"
      ) {
        transaction.destAcc = "-";
      }
      //console.log(transaction);
    }
    this.transactions = data;
  }

  constructor(private router: Router, private walletService: WalletService) {}

  goToMenu() {
    this.router.navigate(["menu"]);
  }
}
