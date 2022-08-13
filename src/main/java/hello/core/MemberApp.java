package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {
        //MemberService memberService = new MemberServiceImpl();
        /*AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        */
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        //Appconfig의 환경들을 가지고
        // spring이 bean붙은거를 spring 컨테이너에 appconfig에서 객체생성한것을 관리함
        MemberService memberService = applicationContext.getBean("memberService",MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);
        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());
    }
}
