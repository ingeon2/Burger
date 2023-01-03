package app;

public class Main {
    public static void main(String[] args) {
        //AppConfigurer를 인스턴스화한 후, 아래와 같이 OrderApp의 인자를 채워준것
        // 앱컨피규어의 역할 → 전체적인 클래스들 관리. 이전엔 오더에서 관리했는데 오더는 오더의 역할만 하면 된다는 뜻.
        AppConfigurer appConfigurer = new AppConfigurer();

        OrderApp orderApp = new OrderApp(
                appConfigurer.productRepository(),
                appConfigurer.menu(),
                appConfigurer.cart(),
                appConfigurer.order()
        );
        
        
        
        orderApp.start(); //main 메서드에 모든 프로그램 로직을 작성하면 Main 클래스에 정의한 모든 인스턴스 필드들을 활용할 수 없다 
                          //그래서 OrderApp 클래스 만든것
    }
}