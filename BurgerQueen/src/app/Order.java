package app;

import app.discount.Discount;
import app.discount.discountCondition.CozDiscountCondition;
import app.discount.discountCondition.DiscountCondition;
import app.discount.discountCondition.KidDiscountCondition;
import app.discount.discountPolicy.FixedAmountDiscountPolicy;
import app.discount.discountPolicy.FixedRateDiscountPolicy;

public class Order {
    private Cart cart;

    private Discount discount;


    public Order(Cart cart){
        this.cart = cart;
    }

    public Order(Cart cart, Discount discount) {
        this.cart = cart;
        this.discount = discount;
    }

    public void makeOrder() { //주문 매서드
        
        //할인하려고 체크하는매서드 불러옴
        //CozDiscountCondition cozDiscountCondition = new CozDiscountCondition(new FixedRateDiscountPolicy(10));
        // 바꿔주려면? CozDiscountCondition cozDiscountCondition = new CozDiscountCondition(new FixedAmountDiscountPolicy(500)); 이렇게만 바꾸면 된다 왜? 인터페이스 사용때문에
        //KidDiscountCondition kidDiscountCondition = new KidDiscountCondition(new FixedAmountDiscountPolicy(500));

        //cozDiscountCondition.checkDiscountCondition();
        //kidDiscountCondition.checkDiscountCondition();

        //int totalprice = cart.calculateTotalPrice();

        //
        //int finalPrice = totalprice;

        //if (cozDiscountCondition.isSatisfied()) finalPrice = cozDiscountCondition.applyDiscount(finalPrice);//위에서 할인 적용된다면 디스카운트 매서드 적용.
        //if (kidDiscountCondition.isSatisfied()) finalPrice = kidDiscountCondition.applyDiscount(finalPrice);

        discount.checkAllDiscountConditions();

        int totalPrice = cart.calculateTotalPrice();
        int finalPrice = discount.discount(totalPrice);

        //for (DiscountCondition discountCondition : discountConditions) {
        //    discountCondition.checkDiscountCondition();
        //    if (discountCondition.isSatisfied()) finalPrice = discountCondition.applyDiscount(finalPrice);
        //}

        System.out.println("[📣] 주문이 완료되었습니다. ");
        System.out.println("[📣] 주문 내역은 다음과 같습니다. ");
        System.out.println("-".repeat(60));

        cart.printCartItemDetails();

        System.out.println("-".repeat(60));
        System.out.printf("금액 합계      : %d원\n", totalPrice);
        System.out.printf("할인 적용 금액 : %d원\n", finalPrice);
    }

}
