package hello.core.singleton;

public class SingletonService {

    //자기 자신을 static 으로 선언하면 class 레벨로 올라가기 때문에 하나만 존재하게된다.
    private static final SingletonService instance = new SingletonService();

    private int globalVal = 0;

    public static SingletonService getInstance() {
        return instance;
    }

    //다른 class 파일에서 SingletonService 객체를 생성하지 못하도록 private 으로 생성자를 하나 만든다.
    private SingletonService() {
    }

    public void logic() {
        globalVal++;
        System.out.println("싱글톤 객체 로직 호출");
    }

    public int globalVal() {
        return globalVal;
    }

    public int localVal() {
        int localVal = 0;
        localVal++;
        return localVal;
    }
}
