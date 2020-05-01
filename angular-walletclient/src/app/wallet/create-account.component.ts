import { Component, OnInit } from "@angular/core";
import { Wallet } from "./wallet";
import { WalletService } from "../wallet.service";
import { Router } from "@angular/router";

@Component({
  selector: "app-create",
  templateUrl: "create-account.component.html",
})
export class CreateAccountComponent implements OnInit {
  title = "Create Account";
  ngOnInit() {}

  constructor(private walletService: WalletService, private router: Router) {}

  wallet: Wallet = new Wallet();
  data1: Wallet = new Wallet();
  submitted = false;

  newWallet(data) {
    this.data1 = data;
    this.walletService.wallet = data;
    if (this.data1 != null) {
      alert(
        "Account created Successfully !! \n Your Customer ID is " +
          this.data1.customer.custId +
          ".\nPlease take a note of your Customer ID, it will be used as password for Login.\nNOTE : Don't share it with others !!"
      );
    }
    this.router.navigate(["validate"]);
  }

  save() {
    //console.log(this.wallet);
    this.walletService.createAccount(this.wallet).subscribe(
      (data) => this.newWallet(data),
      (error) => console.log(error)
    );
  }

  onSubmit() {
    this.submitted = true;
    this.save();
  }
}
