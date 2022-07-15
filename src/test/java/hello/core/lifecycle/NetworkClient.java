package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean; //초기화 빈

public class NetworkClient implements InitializingBean, DisposableBean {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url : " + url);
        // connect();
        // call("초기화 연결 메세지");
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작 시 호출
    public void connect() {
        System.out.println("connect : " + url);
    }

    public void call(String message) {
        System.out.println("call : " + url + " message : " + message);
    }

    //서비스 종료 시 호출
    public void disconnect() {
        System.out.println("close : " + url);
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        //Spring에서 Properties 셋팅이(의존관계) 끝나면 실행되는 함수
        System.out.println("NetworkClient.afterPropertiesSet");
        connect();
        call("초기화 연결 메세지");
    }

    @Override
    public void destroy() throws Exception {
        //모든 작업이 종료되기 전에 실행
        System.out.println("NetworkClient.destroy");
        disconnect();
    }
}
