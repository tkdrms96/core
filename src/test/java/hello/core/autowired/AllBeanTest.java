package hello.core.autowired;

import hello.core.AppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AllBeanTest {

    /*
    * Spring Bean을 Map과 List로 받는 방법 Map와 List는 모든 DiscountPolicy 주입 키는 해당 생성 객체의 코드
    * discountService에 getBean으로 ApplicationContext의 DiscountService의 bean을 받아옴
    * discount 메서드는 파라미터로 가격, 이름, discountCode discountcode.get ( 키 ) 값을 주면 AppCoinfig 클래스 에서 해당 스프링 bean을 찾아서 실행시킴
    * */


    /* spring 자동 등록 빈 과 수동 등록 빈의 의사 결정 하는 방법
        스프링 다형성과 업무 로직을 고려한 의사 결정

    * AOP, 자동 LOG 파일 생성 등 기술 관련 로직의 경우 갯수가 적고 로직 전반에 영향을 주는 경우가 많음 이 경우 Bean을 수동으로 등록 하는것을 고려
    * 업무 관련 로직의 경우 갯수가 많고 공통 모듈이 아닐 경우 @Component , @Configuration 어노테이션 사용을 고려 해보자
    */
    @Test
    void findAllBean() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class, DiscountService.class);

        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "userA", Grade.VIP);
        int discountPrice = discountService.discount(member, 10000, "fixDiscountPolicy");

        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(discountPrice).isEqualTo(1000);

        int rateDiscountPrice = discountService.discount(member, 20000, "rateDiscountPolicy");
        assertThat(rateDiscountPrice).isEqualTo(2000);

        int rateDiscountPrice2 = discountService.discount(member, 25000,"fixDiscountPolicy");
        assertThat(rateDiscountPrice2).isEqualTo(2500);
    }

    static class DiscountService {
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;

        @Autowired
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policies = " + policies);
        }

        public int discount(Member member, int price, String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            return discountPolicy.discount(member, price);
        }
    }
}