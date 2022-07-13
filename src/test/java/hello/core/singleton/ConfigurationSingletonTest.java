package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberServiceImpl memberService = ac.getBean("memberService" , MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        //@Configuration 안에 3번의 MemberRepository 객체가 생성되지만, 같은 주소의 객체가 생성된다 Spring Bean..의 싱글톤 보장..
        System.out.println("memberService -> memberRepository = " + memberRepository1);
        System.out.println("orderService ->  memberRepository = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);

        assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);

    }

    @Test
    void configurationDeep() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig appConfig = ac.getBean(AppConfig.class);
        System.out.println("appConfig.getClass() = " + appConfig.getClass());
        //appConfig.getClass() = class hello.core.AppConfig 이 조회가 되어야하지만,
        //Spring Container 에서 CGLIB라는 바이코드의 클래스가 AppConfig를 상속받아서 AppConfig가 아닌 싱글톤을 보장하는 AppConfig-CGLIB를 등록한다.
        //appConfig.getClass() = class hello.core.AppConfig$$EnhancerBySpringCGLIB$$6e6a9b38
    }
}
