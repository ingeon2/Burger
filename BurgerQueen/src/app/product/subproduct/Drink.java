package app.product.subproduct;

import app.product.Product;

public class Drink extends Product {
    public boolean hasStraw;

    public Drink(int id, String name, int price, int kcal, boolean hasstraw){
        super(id, price, kcal, name);
        this.hasStraw = hasstraw;
    }

    public Drink(Drink drink) { //애드투 카트 매서드때문에 생성자 오버로딩한것(깊은복사)
        super(drink.getName(), drink.getPrice(), drink.getKcal());
        this.hasStraw = drink.hasStraw();
    }

    public boolean hasStraw() {
        return hasStraw;
    }

    public void setHasStraw(boolean hasstraw) {
        this.hasStraw = hasstraw;
    }


}
