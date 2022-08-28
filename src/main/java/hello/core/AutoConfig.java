package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "hello.core.member",
        //어떤 패키지부터 스캔할지 시작위치를 지정해줌 이 패키지를 포함한 하위패키지를 탐색
        excludeFilters = @ComponentScan.Filter(type= FilterType.ANNOTATION ,classes = AppConfig.class)
        //설정 정보(AppConfig.class)자동 bean 등록되지 않게 filter
)
public class AutoConfig {
}
