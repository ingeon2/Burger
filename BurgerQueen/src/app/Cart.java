package app;

import app.product.Product;
import app.product.ProductRepository;
import app.product.subproduct.BurgerSet;
import app.product.subproduct.Drink;
import app.product.subproduct.Hamburger;
import app.product.subproduct.Side;

import java.util.Scanner;

public class Cart {
    /*장바구니 담기 → addToCart()

옵션 고르게 하기 → chooseOption()

햄버거 세트 구성하기 → composeSet()

장바구니 출력하기 → printCart()

 각각 플러스 알파 매서드 존재 가능*/
    private Product[] items = new Product[0]; //Cart 객체는 장바구니 역할을 하는 객체로, 장바구니에 담긴 상품들을 저장하고 있어야 함.
                                                //이따 겟카트 매서드로 (Product들) 크기 늘려주면서 여기 배열에 넣어줄 예정
    private Scanner scanner = new Scanner(System.in);


    private ProductRepository productRepository;

    public Cart(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    private Menu menu;

    public Cart(ProductRepository productRepository, Menu menu) {
        this.productRepository = productRepository;
        this.menu = menu;
    }

    //초깃값 설정




    //아래부터 매서드 아래부터 매서드 아래부터 매서드 아래부터 매서드 아래부터 매서드 아래부터 매서드 아래부터 매서드 아래부터 매서드
    public void printCart() {
        System.out.println("🧺 장바구니");
        System.out.println("-".repeat(60));

        //여기에 장바구니 상품들을 옵션 정보와 함께 출력 = 매서드 아래에 작성(프린트 카 아이템 디테일스)
        printCartItemDetails();

        System.out.println("-".repeat(60));
        System.out.printf("합계 : %d원\n", calculateTotalPrice()/*아래 매서드 존재*/);

        System.out.println("이전으로 돌아가려면 엔터를 누르세요. ");
        scanner.nextLine();
    }

    protected int calculateTotalPrice() {
        int totalPrice = 0;
        for (Product product : items) {
            totalPrice += product.getPrice();
        }
        return totalPrice;
    }


    protected void printCartItemDetails() {

        for (Product product : items) {
            if (product instanceof BurgerSet) {
                BurgerSet burgerSet = (BurgerSet) product; // 아래 설명 참고
                System.out.printf(
                        "  %s %6d원 (%s(케첩 %d개), %s(빨대 %s))\n",
                        product.getName(),
                        product.getPrice(),
                        burgerSet.getSide().getName(),
                        burgerSet.getSide().getKetchup(),
                        burgerSet.getDrink().getName(),
                        burgerSet.getDrink().hasStraw() ? "있음" : "없음"
                );
            }
            else if (product instanceof Hamburger) {
                System.out.printf(
                        "  %-8s %6d원 (단품)\n",
                        product.getName(),
                        product.getPrice()
                );
            }
            else if (product instanceof Side) {
                System.out.printf(
                        "  %-8s %6d원 (케첩 %d개)\n",
                        product.getName(),
                        product.getPrice(),
                        ((Side) product).getKetchup()  // 다운캐스팅, 겟 캐첩은 사이드만 사용할 수 있는 매서드라서.
                );
            }
            else if (product instanceof Drink) {
                System.out.printf(
                        "  %-8s %6d원 (빨대 %s)\n",
                        product.getName(),
                        product.getPrice(),
                        ((Drink) product).hasStraw() ? "있음" : "없음"  // 다운캐스팅.
                );
            }
        }
    }
    //여기까지 세개 한세트 매서드

    public void addToCart(int productId) {

        //애드카트 매서드 상품찾기, 옵션설정, 세트구성여부, 아이템에 프로덕트 추가

        /*Product product; //프로덕트 생성하고
        for (Product element : productRepository.getAllProducts()) { //productId를 통해 productId를 id로 가지는 프로덕트 할당해주기
            if (element.getId() == productId) product = element;
        }

        위의 매서드보다, 아래의 매서드가 객체지향에 더 어울림 (위는 레파지토리 고치면 여기서도 고쳐야하지만, 아래는 레파지토리 클래스에서 감당가능)*/


        //위 주석이나 아래 한줄이나 결과는 같음 (결합도는 낮아졌다고 할 수 있음.) = 캡슐화
        Product product = productRepository.findById(productId); //프로덕트아이디는 매개변수


        //상품 옵션 설정, 아래에 매서드 존재
        chooseOption(product); //여기 이후에 상품, 세트 여부 결정.

        if (product instanceof Hamburger) {
            Hamburger hamburger = (Hamburger) product;
            if (hamburger.isburgerset()) product = composeSet(hamburger); //아래에 존재 매서드
        }

        Product newProduct;
        if (product instanceof Hamburger) newProduct = new Hamburger((Hamburger) product);
        else if (product instanceof Side) newProduct = new Side((Side) product);
        else if (product instanceof Drink) newProduct = new Drink((Drink) product);
        else newProduct = new BurgerSet((BurgerSet) product);



        //items에 product 추가, 배열 새롭게 만들어주기
        Product[] newItems = new Product[items.length + 1];
        System.arraycopy(items, 0, newItems, 0, items.length);
        newItems[newItems.length - 1] = product;
        items = newItems;

        System.out.println("[📣] " + product.getName() +"를(을) 장바구니에 담았습니다.\n");
    }

    private BurgerSet composeSet(Hamburger hamburger) {
        System.out.println("사이드를 골라주세요");
        menu.printSides(false);

        String sideId = scanner.nextLine();
        Side side = (Side) productRepository.findById(Integer.parseInt(sideId));
        chooseOption(side);

        System.out.println("음료를 골라주세요.");
        menu.printDrinks(false);

        String drinkId = scanner.nextLine();
        Drink drink = (Drink) productRepository.findById(Integer.parseInt(drinkId));
        chooseOption(drink);

        String name = hamburger.getName() + "세트";
        int price = hamburger.getSetprice();
        int kcal = hamburger.getKcal() + side.getKcal() + drink.getKcal();

        return new BurgerSet(name, price, kcal, hamburger, side, drink);
    }

    private void chooseOption(Product product) {

        String input;

        if (product instanceof Hamburger) {
            System.out.printf(
                    "단품으로 주문하시겠어요? (1)_단품(%d원) (2)_세트(%d원)\n",
                    product.getPrice(),
                    ((Hamburger) product).getSetprice()
            );
            input = scanner.nextLine();
            if (input.equals("2")) ((Hamburger) product).setIsburgerset(true);
        }
        else if (product instanceof Side) {
            System.out.println("케첩은 몇개가 필요하신가요?");
            input = scanner.nextLine();
            ((Side) product).setKetchup(Integer.parseInt(input));
        }
        else if (product instanceof Drink) {
            System.out.println("빨대가 필요하신가요? (1)_예 (2)_아니오");
            input = scanner.nextLine();
            if (input.equals("2")) ((Drink) product).setHasStraw(false);
        }
    }




}
