package hello.core.lifecycle;

public class SpringBeanLifeCycle {


    /* 스프링 Bean의 lifecycle을 결정하는 클래스 스프링 빈의 객체 소멸과 생성을 결정할 수 있다.
    *
    * 1. InitializingBean, DisposableBean 인터페이스
    *      현재 사용하지 않는다. 외부 라이브러리에 대해 적용이 불가능함 초기화 메서드명 변경 불가능
    * 2. 설정 정보에 초기화 메서드와 종료 메서드 지정
    *      spring bean의 옵션을 줘서 관리하는 방법
    *      bean(initMethod="init", destoryMethod="close") 해당 클래스 명을 줘서 해당 bean 을 distory 할 수 있음
    * 3. @PostConstruct, @PreDestroy
    *      최근 많이 사용하는 어노테이션 외부 라이브러리에 적용 할 수 있음
    * */


}
