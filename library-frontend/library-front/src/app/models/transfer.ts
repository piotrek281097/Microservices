export class Transfer {
  transferId: number;
  sendingAccount: Account;
  targetAccount: Account;
  money: number;
  currency: string;
  dataOpenTransfer: string;
  dataFinishTransfer: string;
  transferStatus: string;

  constructor() {}
}
