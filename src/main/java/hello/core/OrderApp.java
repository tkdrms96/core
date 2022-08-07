package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class OrderApp {

    public static void main(String[] args) {
//        MemberService memberService = new MemberServiceImpl();
//        OrderService orderService = new OrderServiceImpl(memberRepository, discountPolicy);
        AppConfig appConfig = new AppConfig();
        OrderService orderService = appConfig.orderService();
        MemberService memberService = appConfig.memberService();

//        AppConfig를 설계 한 후엔
//        직접 객체를 생성하지않고 AppConfig에서 객체를 생성해줌 interface에만 의존

        Long memberId = 1L;

        Member member = new Member(memberId, "memberA", Grade.VIP);

        Order order = orderService.createOrder(memberId, "itemA", 100000);
    }
}