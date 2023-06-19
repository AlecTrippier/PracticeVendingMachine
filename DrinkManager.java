import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class DrinkManager {

    // classファイルと同じ階層にdrink.csvが配置されている状態
    private static final String FILE = "drink.csv";

    private static final int NAME = 0;
    private static final int PRICE = 1;
    private static final int STOCK = 2;

    public static Map<Integer, Drink> getDrinks() {
        Map<Integer, Drink> drinks = new HashMap<Integer, Drink>();

        // CSVファイルの読み込み
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(FILE), Charset.forName("UTF-8")))) {
            int count = 1;
            String line;
            // readLineメソッドで一行ずつ取り出す
            while ((line = br.readLine()) != null) {
                // splitを使用しコンマ区切りで配列化してdrinkを生成
                String[] values = line.split(",");
                Drink drink = new Drink(
                        values[NAME],
                        Integer.parseInt(values[PRICE]),
                        Integer.parseInt(values[STOCK]));
                drinks.put(count, drink);
                count++;
            }
        } catch (NumberFormatException | IOException e) {
            System.out.println("ファイルの読み込みに失敗しました");
        }
        return drinks;
    }
}