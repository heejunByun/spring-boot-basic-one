package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.stereotype.Component;

@Component
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
