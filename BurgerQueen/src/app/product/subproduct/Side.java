package app.product.subproduct;

import app.product.Product;

public class Side extends Product {
    private int ketchup;

    public Side(int id, String name, int price, int kcal, int ketchup){
        super(id, price, kcal, name);
        this.ketchup = ketchup;
    }

    public Side(Side side) { //애드투 카트 매서드때문에 생성자 오버로딩한것(깊은복사)
        super(side.getName(), side.getPrice(), side.getKcal());
        this.ketchup = side.getKetchup();
    }

    public int getKetchup() {
        return ketchup;
    }

    public void setKetchup(int ketchup) {
        this.ketchup = ketchup;
    }
}
