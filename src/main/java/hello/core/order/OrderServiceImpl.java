package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;

public class OrderServiceImpl implements OrderService{
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    //    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
/*
    Fix -> 정액 할인 Rate -> 정률 할인
    같은 할인이지만 serviceImpl에서 dip와 ocp를 위반
    why? 기존코드를 사용하게되면 정률, 정액 수정할때 impl을 수정해야함
        private final DiscountPolicy(할인정책을) new객체로 생성하지말고
         추상화인 interface에만 의존하게 코드를 변경

    test -> nullpointexception 발생 -> 해결방안 ? discountpolicy의 구현객체를 생성 하고 주입
        */
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }


}
