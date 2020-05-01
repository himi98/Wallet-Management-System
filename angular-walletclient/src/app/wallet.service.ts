import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Observable } from "rxjs";
import { Wallet } from "./wallet/wallet";

@Injectable({
  providedIn: "root",
})
export class WalletService {
  private baseUrl = "http://localhost:8080/Wallet-App-rest/api/v1/wallets";
  wallet: Wallet;
  accountList;

  constructor(private http: HttpClient) {}

  createAccount(wallet: Object): Observable<any> {
    return this.http.post(`${this.baseUrl}/create`, wallet);
  }

  validateCustomer(cid: number): Observable<any> {
    return this.http.post(`${this.baseUrl}/validate`, cid);
  }

  depositMoney(accNo: number, amount: number): Observable<any> {
    return this.http.post(`${this.baseUrl}/deposit`, [accNo, amount]);
  }

  withdrawMoney(accNo: number, amount: number): Observable<any> {
    return this.http.post(`${this.baseUrl}/withdraw`, [accNo, amount]);
  }

  transferMoney(
    accNo: number,
    accNoB: number,
    amount: number
  ): Observable<any> {
    return this.http.post(`${this.baseUrl}/transfer`, [accNo, accNoB, amount]);
  }

  viewAccount(cid: number): Observable<any> {
    return this.http.post(`${this.baseUrl}/viewAccount`, cid);
  }

  viewTransactions(accNo: number): Observable<any> {
    return this.http.post(`${this.baseUrl}/viewTransactions`, accNo);
  }

  getAccountList(): Observable<any> {
    return this.http.get(`${this.baseUrl}/getAccountList`);
  }
}
