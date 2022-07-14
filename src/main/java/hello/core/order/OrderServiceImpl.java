package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor // Lombok -> final이 붙은 변수들을 가지고 기본 생성자를 만들어준다.
public class OrderServiceImpl implements OrderService{

    //회원정보
    //MemberServiceImpl 이 MemberRepository 객체를 받아서 쓰는데 memberService 로 하면 안되는 이유는?
    //답변 https://www.inflearn.com/questions/94907
    //결론 큰 프로젝트가 아니면 상관없다.

    //private final MemberService memberService = new MemberServiceImpl();
    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    //할인정책
    // private final DiscountPolicy discountPolicy = new FixDiscountPolicy(); // DIP , OCP 위반
    // private final DiscountPolicy rateDiscountPolicy = new RateDiscountPolicy(); // DIP , OCP 위반

    private final DiscountPolicy discountPolicy;

    //Lombok (RequiredArgsConstructor) 가 아래 주석 코드를 만들어줌 Ctrl + F12 눌러서 확인 가능
    //@Qualifier : public OrderServiceImpl(MemberRepository memberRepository, @Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy) {
    //@MainDiscountPolicy : @Qualifier을 그냥 사용하게되면 실행은 되지만, 컴파일 시 문자가 들어가기 때문에 에러가 날 수 있다?? 그래서 직접 어노테이션을 만들어서 사용하느것이 바람직하다.
    //-> public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //Test 용
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}


