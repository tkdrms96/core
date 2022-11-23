/*
package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {

    @Test
    void statefulServiceTest() {
        //Spring singleton 방식의 주의점
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // ThreadA : 사용자A가 10000원 주문
        statefulService1.order("userA", 10000);
        // ThreadB : 사용자B가 20000원 주문
        statefulService2.order("userB", 20000);

        // ThreadA : 사용자가A가 주문 금액 조회
        int price = statefulService1.getPrice();
        System.out.println("price = " + price);
        // 만원이 아니라 2만원이 씀 중간에 사용자 B가 가격을 바꿔버림

        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);

    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }

}*/
