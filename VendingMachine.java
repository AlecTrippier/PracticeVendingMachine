import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class VendingMachine {

    private Map<Integer, Drink> drinks = new HashMap<Integer, Drink>(); // 飲み物情報

    public VendingMachine() {
        this.drinks.put(1, new Drink("コーラ", 100, 2));
        this.drinks.put(2, new Drink("お茶", 150, 1));
        this.drinks.put(3, new Drink("コーヒー", 120, 0));
    }

    public void showDrink() {
        // ---------------------------
        // 1: コーラ( 100 円 )
        // 2: お茶( 150 円 )
        // 3: コーヒー( 売切 )
        // ---------------------------
        System.out.println("---------------------------");
        this.drinks.forEach((key, value) -> {

            String name = value.getName();
            int price = value.getPrice();
            int stocks = value.getStocks();

            String msg;

            if (stocks == 0) {
                msg = key + ": " + name + "( 売切 )"; // 商品が売切な場合
            } else {
                msg = key + ": " + name + "( " + price + " 円 )"; // 商品の在庫がある場合
            }

            System.out.println(msg);

        });
        System.out.println("---------------------------");
        System.out.println("商品を選択してください");

    }

    public boolean checkStocks(int inputInt) { // 在庫チェック

        do {

            int stocks = this.drinks.get(inputInt).getStocks();

            if (stocks > 0) {
                return true;
            }

            System.out.println("売り切れです");

            showDrink(); // 商品一覧を再表示して選択をやり直す
            inputInt = getInputInt(); // 新たな選択肢を取得
        } while (true);

    }

    public void selectDrink() {

        int select = getInputInt();

    }

    public int getInputInt() {
        // 外部入力のメソッドを使用するためにはAPIのインスタンス化が必要
        Scanner scanner = new Scanner(System.in);
        // 整数値を代入する変数を初期化
        int input = 0;
        // ループ処理の判定材料にfalseを代入しておく
        boolean isValidInput = false;

        // 変数のisValidInputがfalseの場合はループし続ける
        while (!isValidInput) {

            // 例外処理
            try {
                // エラーが起こりそうなコードを記述する
                input = scanner.nextInt(); // 整数値の外部入力を求める

                switch (input) {
                    case 1:
                    case 2:
                    case 3:

                        isValidInput = true; // エラーが発生しなかった場合はtrueでループを止める
                        break;
                    default:
                        System.out.println("無効な選択です。");
                        break;
                }

            } catch (InputMismatchException e) {// エラーが発生した場合の処理を記述する
                // InputMismatchException 期待したデータ型を一致しなかった場合にスローされるエラー
                // ユーザーが整数以外の文字列を入力した場合など
                System.out.println("整数を入力してください。");
                scanner.nextLine(); // 改行文字をクリアするために次の行を読み飛ばす。入力エラーを処理するために必要です
            }
        }

        return input;

    }

    // public void getDrink() {
    //     boolean checkDeposit = checkDeposit(deposit);
    // }

    public int checkDeposit(int deposit,int inputInt) {

        int price = this.drinks.get(inputInt).getPrice(); //商品の価格情報取得
        while (deposit < price) { // 投入金額が100円未満の場合は100円以上になるまでループする
            System.out.println("入金額が足りません 現在の投入金額 " + deposit + " 円");


            deposit += Deposit();
        }
        return deposit;

    }

    public int Deposit() { // お金を入れる
        System.out.println("お金を入れてください");
        // 外部入力のメソッドを使用するためにはAPIのインスタンス化が必要
        Scanner scanner = new Scanner(System.in);
        // 整数値を代入する変数を初期化
        int input = 0;
        // ループ処理の判定材料にfalseを代入しておく
        boolean isValidInput = false;

        // 変数のisValidInputがfalseの場合はループし続ける
        while (!isValidInput) {

            // 例外処理
            try {
                // エラーが起こりそうなコードを記述する
                input = scanner.nextInt(); // 整数値の外部入力を求める
                isValidInput = true;
            } catch (InputMismatchException e) {// エラーが発生した場合の処理を記述する

                // InputMismatchException 期待したデータ型を一致しなかった場合にスローされるエラー
                // ユーザーが整数以外の文字列を入力した場合など
                System.out.println("金額を入力してください。");
                scanner.nextLine(); // 改行文字をクリアするために次の行を読み飛ばす。入力エラーを処理するために必要です
            }

        }

        return input;

    }

    public void buyDrink(int inputInt) {
        System.out.println(this.drinks.get(inputInt).getName() + "を購入しました");
        int stocks = this.drinks.get(inputInt).getStocks();
        this.drinks.get(inputInt).setStocks(--stocks);
    }

    public void getChange(int checkDeposit, int inputInt) {
        int price = this.drinks.get(inputInt).getPrice(); //商品の価格情報取得
        if (checkDeposit > 100) {
            System.out.println((checkDeposit - price) + " 円のお返しです");
            checkDeposit = 0;
        }

    }

     public boolean checkAllStocksEmpty() {
        for (Drink drink : drinks.values()) {
            if (drink.getStocks() > 0) {
                return false; // 在庫が1つ以上ある場合はfalseを返す
            }
        }
        return true; // 全ての在庫が0の場合はtrueを返す
    }


    public void execution() {

        showDrink(); // 選択肢メッセージを表示

        int inputInt = getInputInt(); // 1~3の番号を外部入力で代入

        boolean stocks = checkStocks(inputInt); // 売り切れの場合 falseが入る

        int deposit = Deposit(); // 入金額を外部入力する

        int checkDeposit = this.checkDeposit(deposit,inputInt); //入金額をチェックする 100円に足りない場合は追加投入できる

        buyDrink(inputInt);

        getChange(checkDeposit,inputInt);

        boolean checkAllStocksEmpty = checkAllStocksEmpty();

        if (checkAllStocksEmpty) {
            System.out.println("全て売り切れました");
        } else {
            execution();
        }

        

    }
}