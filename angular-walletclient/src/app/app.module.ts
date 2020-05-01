import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";
import { FormsModule } from "@angular/forms";

import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import { WalletService } from "./wallet.service";
import { HttpClientModule } from "@angular/common/http";
import { ExistingCustomerComponent } from "./wallet/existing-customer.component";
import { CreateAccountComponent } from "./wallet/create-account.component";
import { HomeComponent } from "./wallet/home.component";
import { DepositMoneyComponent } from "./wallet/deposit-money.component";
import { WithdrawMoneyComponent } from "./wallet/withdraw-money.component";
import { TransferMoneyComponent } from "./wallet/transfer-money.component";
import { ValidateCustomerComponent } from "./wallet/validate-customer.component";
import { ViewTransactionsComponent } from "./wallet/view-transactions.component";
import { ViewAccountComponent } from "./wallet/view-account.component";

@NgModule({
  declarations: [
    AppComponent,
    ExistingCustomerComponent,
    CreateAccountComponent,
    HomeComponent,
    DepositMoneyComponent,
    WithdrawMoneyComponent,
    TransferMoneyComponent,
    ValidateCustomerComponent,
    ViewTransactionsComponent,
    ViewAccountComponent,
  ],
  imports: [BrowserModule, AppRoutingModule, HttpClientModule, FormsModule],
  providers: [WalletService],
  bootstrap: [AppComponent],
})
export class AppModule {}
