package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig {

            /*관심사 분리를 위한 AppConfig class
            여기서는 어떤 기능을 할당할지? 에대한 정보
            serviceImpl에서 할당? dip,ocp 위반
            생성한 객체 인스턴스의 참조(레퍼런스)를 생성자를 통해 주입 하는 외부 클래스임 AppConfig
            */


    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository()); //생성자 주입
    }

    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    private MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }

    public DiscountPolicy discountPolicy(){
        return new FixDiscountPolicy();
    }

}
