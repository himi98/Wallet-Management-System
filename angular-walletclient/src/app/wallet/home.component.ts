import { Component, OnInit } from "@angular/core";
import { templateSourceUrl } from "@angular/compiler";
import { Router } from "@angular/router";
import { WalletService } from "../wallet.service";

@Component({
  selector: "app-home",
  templateUrl: "home.component.html",
})
export class HomeComponent implements OnInit {
  title = "Welcome to Wallet Management System";
  ngOnInit() {
    this.walletService.wallet = null;
  }

  constructor(private router: Router, private walletService: WalletService) {}
}
