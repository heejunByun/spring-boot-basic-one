package hello.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

public class PrototypeTest {


    @Test
    void protoTypeBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        //출력 순서 확인할 것
        System.out.println("find prototypeBean1"); // 1
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        System.out.println("find prototypeBean2"); // 3
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2);

        assertThat(prototypeBean1).isNotSameAs(prototypeBean2);

        //destroy 함수를 직접 실행해야한다.
        prototypeBean1.destroy();
        prototypeBean2.destroy();
        ac.close();
    }

    @Scope("prototype") //만들기만 해주고, 종료는 해주지 않는다.
    static class PrototypeBean {
        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init"); // 2 4
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy"); // 호출되지 않음
        }

    }
}
