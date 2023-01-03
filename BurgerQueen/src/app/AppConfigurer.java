package app;

import app.discount.Discount;
import app.discount.discountCondition.CozDiscountCondition;
import app.discount.discountCondition.DiscountCondition;
import app.discount.discountCondition.KidDiscountCondition;
import app.discount.discountPolicy.FixedAmountDiscountPolicy;
import app.discount.discountPolicy.FixedRateDiscountPolicy;
import app.product.ProductRepository;

public class AppConfigurer {
    //AppConfigurer는 프로그램의 동작에 필요한 모든 객체들을 생성역할, 각 객체의 동작에 필요한 다른 객체들을 결정해주는 역할도 수행
    /*새로운 할인 이벤트를 진행하는 경우

    XXXXDiscountCondition 클래스만 새롭게 정의한 다음, AppConfigurer의 discount() 메서드의 return문 내 DiscountCondition 배열에 새로운 클래스의 인스턴스를 추가하기만 해주면 됩니다.

    새로운 클래스를 필요에 따라 새롭게 만들었을지언정, 기존에 작성한 코드들을 수정하지 않아도 됩니다.



    할인 조건에 따른 할인 정책을 바꾸는 경우

    즉 코드스테이츠 수강생에게 고정 금액 할인을, 청소년에게 고정 비율 할인을 적용하는 경우, AppConfigurer의 discount() 메서드 내에서 new FixedRateDiscountPolicy(10)와 new FixedAmountDiscountPolicy(500)의 위치만 서로 바꿔주면 됩니다.

    할인 정책과 관련된 클래스를 사용하는 CozDiscountCondition과 KidDiscountCondition의 코드를 변경하지 않아도 됩니다.

    */
    private Cart cart = new Cart(productRepository(), menu()); //카트는 싱글톤 패턴. 이전엔 오더앱의 카트와 오더의 카트가 달랐었음.
    public Cart cart() {
        return cart;
    }
    public ProductRepository productRepository() {
        return new ProductRepository();
    }

    public Menu menu() {
        return new Menu(productRepository().getAllProducts());
    }


    public Discount discount() {
        return new Discount(
                new DiscountCondition[]{
                        new CozDiscountCondition(new FixedRateDiscountPolicy(10)),
                        new KidDiscountCondition(new FixedAmountDiscountPolicy(500))
                });
    }

    public Order order() {
        return new Order(cart(), discount());
    }
}
