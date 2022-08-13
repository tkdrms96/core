package hello.core.beanfind;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextExtendsFindTest {

    AnnotationConfigApplicationContext ac
            = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("부모 조회 시 자식이 둘 이상이면 중복 오류 발생")
    void findBeanByParentTypeDuplication(){
        DiscountPolicy bean = ac.getBean(DiscountPolicy.class);

        assertThrows(NoUniqueBeanDefinitionException.class,
                () -> ac.getBean(DiscountPolicy.class));
        //부모타입 조회시 자식이 둘 이상 있으면 빈 이름으로 지정하면됌
    }
    @Test
    @DisplayName("부모 타입으로 모두 조회")
    void findBeanBySubType(){
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        assertThat(beansOfType.size()).isEqualTo(2);
        for(String key : beansOfType.keySet()){
            System.out.println("키 = " + key + " 값 = " + beansOfType.get(key));
        }
    }
    @Configuration
    static class TestConfig{

        public DiscountPolicy rateDiscountPolicy(){
            return new RateDiscountPolicy();
        }
        public DiscountPolicy FixDiscountPolicy(){
            return new FixDiscountPolicy();
        }
    }
}
