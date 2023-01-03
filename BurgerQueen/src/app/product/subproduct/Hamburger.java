package app.product.subproduct;

import app.product.Product;

public class Hamburger extends Product {

    private boolean isburgerset;
    private int setprice;
    public Hamburger(int id, String name, int price, int kcal, boolean isburgerset, int setprice){
        super(id, price, kcal, name);
        this.isburgerset = isburgerset;
        this.setprice =setprice;
    }

    public Hamburger(Hamburger hamburger) { //애드투 카트 매서드때문에 생성자 오버로딩한것(깊은복사)
        super(hamburger.getName(), hamburger.getPrice(), hamburger.getKcal());
        this.isburgerset = hamburger.isburgerset();
        this.setprice = getSetprice();
    }

    public boolean isburgerset() {
        return isburgerset;
    }

    public void setIsburgerset(boolean isburgerset) {
        this.isburgerset = isburgerset;
    }

    public int getSetprice() {
        return setprice;
    }

    public void setSetprice(int setprice) {
        this.setprice = setprice;
    }
}
