package hello.core.annotation;


import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE, ElementType.METHOD}) //어노테이션 만들 수 있는 타입을 지정
@Retention(RetentionPolicy.RUNTIME) //애노테이션의 라이프 사이클 즉, 애노테이션이 언제까지 살아 남아 있을지를 정하는 것
@Inherited
@Documented
@Qualifier("mainDiscountPolicy") //mainDiscountPolicy 라는 애너테이션을 만든것 해당 애너테이션을 호출 하면 위에 동작들이 작동
public @interface MainDiscountPolicy {

    /*
    * 기존 생성자 주입, 세터주입, 롬복 주입 하면서 생성된 spring bean 두개 이상일 때, 오류가 날 수 있다.
    * 이럴경우 Qualifier를 사용해서 생성될 bean을 정해 줄 수 있음
    * 이 방법은 MainDiscountPolicy 클래스에애너테이션을 직접 만드는 방법
    */
}
