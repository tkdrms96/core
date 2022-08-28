package hello.core.scan.filter;

import java.lang.annotation.*;

@Target(ElementType.TYPE) //Type, 필드 등 어노테이션 붙일 수 있는 대상을 지정
@Retention(RetentionPolicy.RUNTIME)  // 컴포넌트 스캔에 추가시킬것.
@Documented
public @interface MyExcludeComponent {
}
