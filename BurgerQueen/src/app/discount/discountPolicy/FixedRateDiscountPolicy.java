package app.discount.discountPolicy;

public class FixedRateDiscountPolicy implements DiscountPolicy{
    private int discountRate; //할인 비율
    
    public FixedRateDiscountPolicy(int discountRate){ //생성자
        this.discountRate = discountRate;
    }

    public int calculateDiscountedPrice(int price){ //할인율 적용 매서드
        return price - (price * discountRate / 100);
    }
}
