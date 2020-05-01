import { Component, OnInit } from "@angular/core";
import { Router, ActivatedRoute } from "@angular/router";
import { WalletService } from "../wallet.service";

@Component({
  selector: "app-existing",
  templateUrl: "existing-customer.component.html",
})
export class ExistingCustomerComponent implements OnInit {
  title = "Customer Menu";
  ngOnInit() {}

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private walletService: WalletService
  ) {}

  goToDeposit() {
    this.router.navigate(["menu/deposit"]);
  }

  goToWithdraw() {
    this.router.navigate(["menu/withdraw"]);
  }

  goToTransfer() {
    this.router.navigate(["menu/transfer"]);
  }

  goToViewAccount() {
    this.router.navigate(["menu/viewAccount"]);
  }

  goToViewTransactions() {
    this.router.navigate(["menu/viewTransactions"]);
  }
}
