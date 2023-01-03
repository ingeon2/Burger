package app;

import app.discount.Discount;
import app.discount.discountCondition.CozDiscountCondition;
import app.discount.discountCondition.DiscountCondition;
import app.discount.discountCondition.KidDiscountCondition;
import app.discount.discountPolicy.FixedAmountDiscountPolicy;
import app.discount.discountPolicy.FixedRateDiscountPolicy;
import app.product.Product;
import app.product.ProductRepository;

import java.util.Scanner;

public class OrderApp {

    private ProductRepository productRepository;
    private Menu menu;
    private Cart cart;
    private Order order;

    public OrderApp(ProductRepository productRepository, Menu menu, Cart cart, Order order) {
        this.productRepository = productRepository;
        this.menu = menu;
        this.cart = cart;
        this.order = order;
    }
    public void start() {

        Scanner scanner = new Scanner(System.in);

        /*ProductRepository productRepository = new ProductRepository(); //프로덕트리파지토리 생성하고
        Product[] products = productRepository.getAllProducts(); // 그 생성한놈 product[]리턴값 매서드 사용해서 products 생성
        Menu menu = new Menu(products); // 생성한 products 로 메뉴 생성
        Cart cart = new Cart(productRepository, menu);
        Scanner scanner = new Scanner(System.in);

        Order order = new Order(cart, new Discount(
                new DiscountCondition[]{
                        new CozDiscountCondition(new FixedRateDiscountPolicy(10)),
                        new KidDiscountCondition(new FixedAmountDiscountPolicy(500))
                })
        );;*/



        System.out.println("\uD83C\uDF54 BurgerQueen Order Service");


        while (true) {

            menu.printMenu(); //메뉴 출력
            /*사용자 입력 받기*/
            String input = scanner.nextLine();

            if (input.equals("+")) {
                order.makeOrder();
                break;
            }
            else {
                int menuNumber = Integer.parseInt(input);

                if (menuNumber == 0) cart.printCart();
                else if (1 <= menuNumber && menuNumber <= productRepository.getAllProducts().length) cart.addToCart(menuNumber);
            }
        }
    }
}
