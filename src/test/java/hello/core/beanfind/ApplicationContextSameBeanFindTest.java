package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Iterator;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextSameBeanFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("타입으로 조회 시 같은 타입이 둘 이상있을경우 중복오류")
    void findBeanByNames (){
        ac.getBean(SameBeanConfig.class); //타입만 조회하는데 현재 같은 타입이 두개
        // 여러개잖아 나 뭘로 조회해야함? 예외 NoUniqueBeanException
        assertThrows(NoUniqueBeanDefinitionException.class, // 예외 처리
                () -> ac.getBean(MemberRepository.class));
        //해결방법 bean 타입만 조회 하지말고 이름 조건도 같이 조회
    }

    @Test
    @DisplayName("특정 타입을 모두 조회")
    void findAllBeanByType (){
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
        //향상된 for문으로 값
        for(String key : beansOfType.keySet()){
            System.out.println("key = " + key + "value = " + beansOfType.values());
        }

        //Iterator로 자료 뽑기
        Iterator<String> key = beansOfType.keySet().iterator();

        while (key.hasNext()){
            String keys = key.next();
            System.out.println("히히히 = " + keys + "히히히2 = " +  beansOfType.get(keys));
        }

        /*
        * 부모 타입으로 조회 시 자식 타입으로 함께 조회
        * Object 타입으로 조회시 모든 스프링 빈 조회
        * 자식타입의 스프링 빈이 둘 이상 있으면 중복 오류 발생
        *
        * */
    }


    @Configuration
    static class SameBeanConfig {

        @Bean
        public MemberRepository memberRepository1(){
         return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2(){
         return new MemoryMemberRepository();
        }

    }
}
