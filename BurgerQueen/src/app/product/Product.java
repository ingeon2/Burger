package app.product;

public class Product {
    private int id; //인스턴스 변수들
    private int price;
    private int kcal;
    private String name;

    public Product(int id, int price, int kcal, String name){ //생성자
        this.id = id;
        this.price = price;
        this.kcal = kcal;
        this.name = name;
    }

    public Product(String name, int price, int kcal) { //버거셋 생성자때문에 오버로딩해준것
        this.name = name;
        this.price = price;
        this.kcal = kcal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getKcal() {
        return kcal;
    }

    public void setKcal(int kcal) {
        this.kcal = kcal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    } //여기까지 겟셋매서드


}
