package hello.core.scan.filter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.assertj.core.api.Assertions.assertThat;


public class ComponentFilterAppConfigTest {

    @Test
    void filterScan(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfigTest.class);
        BeanA beanA = ac.getBean("beanA", BeanA.class);

        assertThat(beanA).isNotNull();

        ac.getBean("beanB", BeanB.class);

        //excludeFilters 스프링 빈에 제외됨 includeFilters는 스프링 빈 등록

        //만약 bean 등록할때 , 충돌 및 중복의 경우 에러남
        // 프로퍼티 spring.main.allow-bean-definition-overriding=true 를 줘서 에러막기 가능 !

    }
    @Configuration
    @ComponentScan(
            includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
            excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)
            //ANNOTATION 필터타입은 기본값으로 제외 가능함
            //ASSIGNABLE_TYPE 지정한 타입과 자식 타입을 인식해 동작
            //AspectJ AspectJ 패턴 사용 *
            //REGEX 정규표현식
            //CUSTOM TypeFilter이라는 인터페이스를 구현해 처리
    )
    static class ComponentFilterAppConfig{
    }
}
