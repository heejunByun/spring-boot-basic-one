package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor // LogDemoController 기본 생성자에 private final 객체들이 자동주입된다.
public class LogDemoController {

    private final LogDemoService logDemoService;

    //private final MyLogger myLogger; //MyLogger 를 Spring Bean 에 바로 주입한다.
    private final ObjectProvider<MyLogger> myLoggerProvider; //ObjectProvider 를 감싸게되면 Spring Bean 에 바로 DI 하는것이 아니라 MyLogger 를 찾을 수 있는 DL(LookUp)할 수 있게 된다.

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {
        String requestURL = request.getRequestURL().toString();
        MyLogger myLogger = myLoggerProvider.getObject();
        myLogger.setRequestURL(requestURL);

        myLogger.log("Controller Test");
        logDemoService.logic("testId");
        return "SUC";
    }

}
