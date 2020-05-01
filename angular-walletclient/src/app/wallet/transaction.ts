import { Wallet } from "./wallet";

export class Transaction {
  transactionId: number;
  sourceAcc: number;
  destAcc: number;
  type: string;
  amount: number;
  timeStamp: string;
  updatedBalance: string;
}
