package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름 조회")
    void findBeanByName(){
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        assertThat(memberService)
                .isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("탑입으로만 조회")
    void findBeanByType(){
        //같은 타입이 여러개 일 경우도 있으니 이름으로 조회하는게 좋다함
        MemberService memberService = ac.getBean(MemberService.class); //파라미터 타입으로만 조회
        assertThat(memberService)
                .isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByName2(){
        //역할을 구분하기 위해 좋은 코드는아님
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        assertThat(memberService)
                .isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름으로 조회X")
    void findBeanByNameX(){
        //빈이 없을경우 테스트
        ac.getBean("xxxx", MemberService.class);
        // 결과 NoSuchBeanDefinitionException xxxx 라는 빈이 없다 ! . 라는 에러 뜸
        assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("xxxx", MemberService.class));

    }

}
