import { Component, OnInit } from "@angular/core";
import { Router, ActivatedRoute } from "@angular/router";
import { WalletService } from "../wallet.service";
import { Wallet } from "./wallet";

@Component({
  selector: "app-view-account",
  templateUrl: "view-account.component.html",
})
export class ViewAccountComponent implements OnInit {
  title = "Account Details";
  wallet: Wallet = new Wallet();
  cid: number;
  ngOnInit() {
    this.cid = this.walletService.wallet.customer.custId;
    this.walletService.viewAccount(this.cid).subscribe(
      (data) => this.displayData(data),
      (error) => console.log(error)
    );
  }

  displayData(data) {
    this.wallet = new Wallet();
    this.wallet = data;
  }

  constructor(private router: Router, private walletService: WalletService) {}

  goToMenu() {
    this.router.navigate(["menu"]);
  }
}
