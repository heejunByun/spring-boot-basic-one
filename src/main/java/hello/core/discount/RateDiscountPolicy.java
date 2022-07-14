package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary //같은 역할을 구현한 Component가 2개 이상일 때 원래는 오류가 나지만, 우선순위 지정해서 사용할 수 있다.
//@Qualifier("mainDiscountPolicy")
//@MainDiscountPolicy
public class RateDiscountPolicy implements DiscountPolicy {

    private int rateDiscountAmt = 10;

    @Override
    public int discount(Member member, int price) {

        //OrderService orderService = new OrderServiceImpl();

        if (member.getGrade() == Grade.VIP) {
            return price * rateDiscountAmt / 100;
        } else {
            return 0;
        }
    }
}
