package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/**
 * [Annotation]
 * @Component : 컴포넌트 스캔에서 사용
 * @Controlller : 스프링 MVC 컨트롤러에서 사용
 * @Service : 스프링 비즈니스 로직에서 사용
 * @Repository : 스프링 데이터 접근 계층에서 사용
 * @Configuration : 스프링 설정 정보에서 사용
 *
 * 부가기능
 * @Controller : 스프링 MVC 컨트롤러로 인식
 * @Repository : 스프링 데이터 접근 계층으로 인식하고, 데이터 계층의 예외를 스프링 예외로 변환해준다.
 * @Configuration : 앞서 보았듯이 스프링 설정 정보로 인식하고, 스프링 빈이 싱글톤을 유지하도록 추가 처리를 한다.
 * @Service : 사실 @Service 는 특별한 처리를 하지 않는다. 대신 개발자들이 핵심 비즈니스 로직이 여기에 있겠구나 라고 비즈니스 계층을 인식하는데 도움이 된다.
 *
 * [Filter]
 * includeFilters : 컴포넌트 스캔 대상을 추가로 지정한다.
 * excludeFilters : 컴포넌트 스캔에서 제외할 대상을 지정한다.
 */

@Configuration
@ComponentScan(
        /**
         * ComponentScan Option
        */
        //@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class) : 사용한 Annotation 중 @Configuration 을 제외 (=AppConfig.java) //excludeFilters : ComponentScan을 제외할 항목 지정
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
        // basePackages or basePackageClasses 를 지정하지않으면 Default 로 @ComponentScan을 기입한 패키지 부터 하위 패키지까지 탐색
        // basePackages = "hello.core.member", //basePackages = {"hello.core.member", "hello.core.order"} ,
        // basePackageClasses = AutoAppConfig.class //지정한 class의 패키지 부터 하위 패키지까지 탐색
)
public class AutoAppConfig {
}



