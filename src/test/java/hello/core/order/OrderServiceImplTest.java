package hello.core.order;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderServiceImplTest {

    @Test
    void createOrder() {

        //생성자 DI 주입을 진행해야만 간결하고 순수한 Java Test 코드를 짤 수 있다.
        //수정자(Setter) DI를 사용하면 컴파일 오류가 발생할 확률이 굉장히 높다.

        MemberServiceImpl memberService = new MemberServiceImpl(new MemoryMemberRepository());
        memberService.join(new Member(1L, "MemberA", Grade.VIP));

        OrderServiceImpl orderService = new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
        Order order = orderService.createOrder(1L, "itemA", 10000);

        assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
