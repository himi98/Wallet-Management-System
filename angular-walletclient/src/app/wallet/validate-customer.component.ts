import { Component, OnInit } from "@angular/core";
import { Router, ActivatedRoute } from "@angular/router";
import { WalletService } from "../wallet.service";
import { Wallet } from "./wallet";

@Component({
  selector: "app-validate",
  templateUrl: "validate-customer.component.html",
})
export class ValidateCustomerComponent implements OnInit {
  ngOnInit() {}

  title = "Login";
  cid: number;
  data1: Wallet;

  constructor(
    private walletService: WalletService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  onSubmit() {
    this.walletService.validateCustomer(this.cid).subscribe(
      (data) => {
        this.menu(data);
      },
      (error) => {
        this.error(error);
        console.log(error);
      }
    );
  }

  error(error) {
    alert(
      "Customer with entered Customer ID doesn't exist. Please enter a Valid Customer ID."
    );
  }

  menu(data) {
    this.data1 = data;
    if (this.cid == this.data1.customer.custId) {
      this.walletService.wallet = this.data1;
      this.walletService.getAccountList().subscribe(
        (data) => {
          this.walletService.accountList = data;
        },
        (error) => {
          console.log(error);
        }
      );
      this.router.navigate(["menu"]);
    }
  }
}
