public class Drink {

    private String name;
    private int price;
    private int stocks;

    public Drink(String name, int price, int stocks) {
        this.name = name;

        this.price = price;

        this.stocks = stocks;
    }



    public String getName(){
        return name;
    }

    public int getPrice(){
        return price;
    }

    public int getStocks(){
        return stocks;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setStocks(int stocks) {
        this.stocks = stocks;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    
}
