package hello.core.singleton;

public class SingletonService {

    //static 영역에 객체 1개만 생성해두기
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance(){
        return instance;
    }

    private SingletonService(){
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}

