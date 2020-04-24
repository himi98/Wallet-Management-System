import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { CreateAccountComponent } from "./wallet/create-account.component";
import { HomeComponent } from "./wallet/home.component";
import { DepositMoneyComponent } from "./wallet/deposit-money.component";
import { WithdrawMoneyComponent } from "./wallet/withdraw-money.component";
import { TransferMoneyComponent } from "./wallet/transfer-money.component";
import { ViewAccountComponent } from "./wallet/view-account.component";
import { ViewTransactionsComponent } from "./wallet/view-transactions.component";
import { ValidateCustomerComponent } from "./wallet/validate-customer.component";
import { ExistingCustomerComponent } from "./wallet/existing-customer.component";

const routes: Routes = [
  { path: "", redirectTo: "home", pathMatch: "full" },
  { path: "add", component: CreateAccountComponent },
  { path: "home", component: HomeComponent },
  { path: "validate", component: ValidateCustomerComponent },
  { path: "menu", component: ExistingCustomerComponent },
  { path: "menu/deposit", component: DepositMoneyComponent },
  { path: "menu/withdraw", component: WithdrawMoneyComponent },
  { path: "menu/transfer", component: TransferMoneyComponent },
  { path: "menu/viewAccount", component: ViewAccountComponent },
  { path: "menu/viewTransactions", component: ViewTransactionsComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
