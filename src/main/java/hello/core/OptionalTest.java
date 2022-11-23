package hello.core;


import java.util.*;

public class OptionalTest {

    public static void main(String[] args) {
        //Optional<T>은 NPE(NullPointException)의 위험을 다룰수있음
        //Null 체크를 하기위해선 if문등을 사용해서 코드가 복잡해지는데
        // 래퍼 클래스란? 래퍼 = LONG,Integer 타입을 가지고있는 클래스
        //Optional<T> T 모든 타입의 객체를 다룰 수 있음
        List list = new ArrayList<>();
        list.add("optList1");
        //자바 8버전 이전의 null 체크 S
        List<String> nameList1 =
                list != null ? new ArrayList<>(list) : Arrays.asList("");
        //자바 8버전 이전의 null 체크 E

        System.out.println(nameList1);

        Optional<List> opTest = Optional.empty();//Optional 객체 생성
        //Optional<List> opTest2 = Optional.of(null); null 을 직접 넣으면 NPE
        Optional<List> opTest2 = Optional.ofNullable(null); //OK


        String str1 = String.valueOf(opTest.get());
        String str2 = String.valueOf(opTest.orElse(Collections.singletonList(""))); // orElse == Null 일때 "" 반환
        String str3 = String.valueOf(opTest.orElse(Arrays.asList(""))); // orElse == Null 일때 반환

        //isPresent() null 아닐경우 false 아니면 true 반환 -> if(opTest.isPresent()){}

    }

}
