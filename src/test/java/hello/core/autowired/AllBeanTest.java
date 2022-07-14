package hello.core.autowired;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * [조회한 빈이 모두 필요할 때, List, Map]
 * 의도적으로 정말 해당 타입의 스프링 빈이 다 필요한 경우도 있다.
 * 예를 들어서 할인 서비스를 제공하는데, 클라이언트가 할인의 종류(rate, fix)를 선택할 수 있다고 가정해보자.
 * 스프링을 사용하면 소위 말하는 전략 패턴을 매우 간단하게 구현할 수 있다.
 */

/**
 * [로직 분석]
 * DiscountService는 Map으로 모든 DiscountPolicy 를 주입받는다. 이때 fixDiscountPolicy ,
 * rateDiscountPolicy 가 주입된다.
 * discount () 메서드는 discountCode로 "fixDiscountPolicy"가 넘어오면 map에서
 * fixDiscountPolicy 스프링 빈을 찾아서 실행한다. 물론 “rateDiscountPolicy”가 넘어오면
 * rateDiscountPolicy 스프링 빈을 찾아서 실행한다.
 *
 * [주입 분석]
 * Map<String, DiscountPolicy> : map의 키에 스프링 빈의 이름을 넣어주고, 그 값으로
 * DiscountPolicy 타입으로 조회한 모든 스프링 빈을 담아준다.
 * List<DiscountPolicy> : DiscountPolicy 타입으로 조회한 모든 스프링 빈을 담아준다.
 * 만약 해당하는 타입의 스프링 빈이 없으면, 빈 컬렉션이나 Map을 주입한다.
 *
 */

public class AllBeanTest {

    @Test
    void findAllBean() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);

        DiscountService discountService = ac.getBean(DiscountService.class); //bean 생성

        assertThat(discountService).isInstanceOf(DiscountService.class);

        Member member = new Member(1L, "userA", Grade.VIP); //Member 생성

        int discountPrice = discountService.discount(member, 10000, "fixDiscountPolicy"); //새로운 discount logic
        assertThat(discountPrice).isEqualTo(1000);

        int rateDiscountPolicy = discountService.discount(member, 20000, "rateDiscountPolicy");//새로운 discount logic
        assertThat(rateDiscountPolicy).isEqualTo(2000);
    }

    static class DiscountService {
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;

        @Autowired
        //Map이나 List로 DI주입을 받으면, 2개 이상의 Bean을 받을 수 있다. (단, 우선순위(@Primary) 나, 지정(@Qualifier)이 되어있는 경우에만)
        public DiscountService(Map<String, DiscountPolicy> policyMap, List< DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policies = " + policies);
        }

        public int discount(Member member, int i, String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            int result = discountPolicy.discount(member , i);
            System.out.println("result = " + result);
            return result;
        }
    }
}
