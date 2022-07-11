package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.*;

public class OrderServiceImpl implements OrderService{

    //회원정보
    //MemberServiceImpl 이 MemberRepository 객체를 받아서 쓰는데 memberService 로 하면 안되는 이유는?
    //답변 https://www.inflearn.com/questions/94907
    //결론 큰 프로젝트가 아니면 상관없다.

    //private final MemberService memberService = new MemberServiceImpl();
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    //할인정책
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
