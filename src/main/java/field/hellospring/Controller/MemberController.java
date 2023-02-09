package field.hellospring.Controller;

import org.springframework.stereotype.Controller;

@Controller
public class MemberController{
    //이렇게 컨트롤러를 만들어 놓으면 스프링 컨테이너가 생기면서 스프링컨테이너라는 통이 생김 거기에 컨트롤러 어노테이션이 있으면
    // 멤버 컨트롤러 객체를 생성해서 스프링에 넣어둠.그리고 스프링이 관리를 한다.
    //컨트롤러라는 어노테이션을 보고,스프링이 동작 할 때, (헬로컨트롤러,멤버컨트롤러 등)컨트롤러라는 객체를 생성해서 들고있는다.
    //이렇게 만들어진 컨트롤러로 빈이 관리 된다.
//    private final MemberService mem
}
