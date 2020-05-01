import { Customer } from "./customer";
import { Transaction } from "./transaction";

export class Wallet {
  accNo: number;
  bankName: string;
  balance: number;
  customer: Customer = new Customer();
  transactions: Transaction[];
}
