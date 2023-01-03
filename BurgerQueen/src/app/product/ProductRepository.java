package app.product;

import app.product.subproduct.Drink;
import app.product.subproduct.Hamburger;
import app.product.subproduct.Side;

public class ProductRepository {

    private final Product[] products = { //필드로 배열 변수를 정의해서 모든 상품을 이 배열 안에 포함시켜 관리
            new Hamburger(1, "새우버거", 3500, 500, false, 4500), //각각 인스턴스들 생성(기존에 만들었던 생성자로)
            new Hamburger(2, "치킨버거", 4000, 600, false, 5000),
            new Side(3, "감자튀김", 1000, 300, 1),
            new Side(4, "어니언링", 1000, 300, 1),
            new Drink(5, "코카콜라", 1000, 200, true),
            new Drink(6, "제로콜라", 1000, 0, true),
    }; //다형성으로 Product안에 묶고, 배열생성

    public Product[] getAllProducts() { //이거 메뉴에서 쓸거거든
        return products;
    }

    public Product findById(int productId) {
        for (Product product : products) {
            if (product.getId() == productId) return product;
        }
        return null;
    }
}
