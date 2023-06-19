public class DepositManager {
    private int deposit = 0;

    // 入金額登録
    public void setDeposit(int deposit) {
        this.deposit += deposit;
    }

    // 入金額取得
    public int getDeposit() {
        return this.deposit;
    }

    // お釣りの返却、入金額のリセット
    public int getChangeAndInit(int change) {
        this.deposit = 0;
        return change;
    }
}