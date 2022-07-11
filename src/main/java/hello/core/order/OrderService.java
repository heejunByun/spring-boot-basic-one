package hello.core.order;

public interface OrderService {
    //최종 주문결과를 반환한다.
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
