package field.hellospring;

import field.hellospring.repository.MemberRepository;
import field.hellospring.repository.MemoryMemberRepository;
import field.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig{

    @Bean//중복검사 옵션 달린 회원가입기능
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }
    @Bean//구현체
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}



//스프링이 동작하면 @Configuration 를 보고 "이건 스프링 빈에 등록하라는 뜻이네"하고 인식한다.
//그러면서 밑에 로직 @Bean MemberService();를 스프링 빈에 등록해준다.
//그리고 memberRepository():[구현체]도 빈에 같은 방식으로 등록하고 MemberService(memberRepository()) 이렇게 멤버서비스에 연결해준다.




//-********DI 의 세가지 방법
//
//
//        스프링 조립이라고 하는데.
//        스프링 동작돼고 컨테이너에 레고마냥 컨트롤러,레파지토리,서비스 등이 조립이 되는것.
//        1.생성자를 통해서 들어오는 방법
//        2.생성자를 빼고 아예 필드에다가 오토와이어드로 하는거 [필드주입]
//        별로 안좋음
//        3.세터주입. 세터 생성해주고 @Autowired 생성은 생성대로,세터는 나중에 호출돼서 들어옴 [세터인젝션방식]
//        누군가가 멤버 호출 할 때 서비스가 퍼블릭으로 열려있어야함. 그니까 퍼블릭하게 노출이 되는거임.
//
//        어플 로딩시점에 조립할 때나 바꾸지 그 담엔 바꿀 일이 잘 없음.
//
//
//
//        요즘 권장 스타일은 여러가지가 있는데,
//        생성자 통해서 주입을 하는게 요즘 트렌드임.
//        예를 들어서
//        *예를 들어선 final빼야댐.
//        조립 하는 시점에 딱 생성자로 한번만 조립 해 놓고 끝을 내버려야함
//        생성만 딱 하고 이후엔 변경은 못하도록. 생성자 주입은 그렇게 막을 수 있다.
//
//        결론:생성자주입이 젤 좋다.
//
//        ------------------------------------------------------------------------------------------------------------------
//
//        ******참고:실무에서는 주로 정형화된 컨트롤러,서비스,리포지토리 같은 코드는 컴포넌트 스캔을 사용한다.
//
//        그리고 정형화 되지 않거나,상황에 따라 구현클래스를 변경해야하면 설정을 통해 스프링 빈으로 등록한다.
//        설정을 통해 스프링 빈으로 등록한다는게 뭔말이냐면
//        메모리 멤버리포지토리가 있으면 일단 지금 가상의 디비로서 메모리멤버리포지토리를 만들어놨잖음.
//        여기서 이제 손쉽게 코드 변경 하나 안하고 디비를 갖다 박을 수 있음.
//        메모리멤버리포지토리를 실제 데이터 저장소로 바꿀거임
//        그걸 기존 멤버서비스나 나머지 코드에 일절 손대지 않고 바꿔끼울 수 있음.
//        그걸 하면 구현체를 바꿔치기 해야겠지.
//
//        그렇게 하면
//        상황에 따라 구현 클래스를 변경해야 스프링빈으로 등록함.
//
//        나중에 그래서 콘피그래이션에서
//        MemoryMemberRepository를 DBMemberRepository로 코드만 띡 바꾸면 댐.
//
//        ------------------------------------------------------------------------------------------------------------------
//        ********************주의:@Autowired를 통한 DI는 'helloController','MemberService'등과 같이 스프링이 관리하는 객체에서만 동작한다.
//        스프링 빈으로 등록하지 않고 내가 직접 생성한 객체에서는 동작하지 않는다.
//        예를들어 서비스랑 메모리 리포지터리 빈에 등록 안해놓으면 컨테이너에 뭐 없는데 컨트롤러만 있음 뭘함
//        리모컨은 있는데 TV가 없는거나 마찬가지임.
//        ------------------------------------------------------------------------------------------------------------------
