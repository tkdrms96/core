package hello.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoreApplication.class, args);
	}
/*
	회원 서비스 (회원 가입 회원 조회) -> 회원저장소 (인터페이스) 구현체 메모리, 외부시스템, DB시스템
*/

}
