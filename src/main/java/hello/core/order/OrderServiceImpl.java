package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderServiceImpl implements OrderService{
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    /*
    * 수정자 주입 - 필드에 final을 제외시키고 p v setXXX
    * 생성자와 수정자 차이점 생성자는 컴파일 시점에 바로 Bean을 찾아 스프링컨테이너에 등록 됌
    * 필드주입 @Autowired 어노테이션을 줘서 스프링컨테이너에 등록
    * 필드주입이 가장 쉬운 방법이나, 문제점이 있다.
    * 단일 책임의 원칙 위반할 수 있고 의존성을 책임지지 않을 수 있다
    *
    * 상황에 따라 맞는 의존관계를 주입시켜야함 수정자 주입의 경우 required = false를 줘도댐
    * */


/*
    옵션처리

    스프링 빈에 주입 대상이 없다면 오류가 터진다. 하지만 옵션으로 주입 대상이 없는 경우도 처리할 수있는 3가지 방법이 있다.

    @Autowired(required=false)
    주입 대상이 없으면 메서드 자체가 호출이 안된다. 에러X
    파라미터 앞에 @Nullable을 붙이면 주입 대상이 없을 때 호출을 하나 null이 주입된다.
            ex) void setNoBean(@Nullable Member member)
    Optional<>을 사용하면 주입 대상이 없을 때 Optional.empty가 주입된다.
    ex) void setNoBean(Optional< Member > member)
    */
    @Autowired
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
