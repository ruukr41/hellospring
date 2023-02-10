package field.hellospring.service;

import field.hellospring.domain.Member;
import field.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
//테스트 클래스 생성 단축키: MemberService에서 커서 아무데나 놓고 ctrl+shift+T 누르면 테스트클래스  패키지와 클래스를 자동 생성할 수 있다.


//여긴 회원가입 기능을 테스트 하는 클래스다.
class MemberServiceTest{

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    //MemberService memberService = new MemberService();
    //MemoryMemberRepository memberRepository = new MemoryMemberRepository;

    //위에 주석처리한 코드는 new로 새로 생성한 리포지토리를 사용하면 테스트 클래스에 있는 (repository) 쓰던 메서드와 자바파일에있는 (repository)가 다른 저장소이다..
    //근데 new 를 지우고 위와 같은 코드로 외부에 생성 됐었던 객체를 넣어주면 멤버서비스와 똑같은 멤버리포지토리가 실행되고 테스트해야 할 파일을 제대로 테스트 할 수 있다.
    //이 것을 디펜더즈 인젝션  {의존성 주입} 코딩이라고 한다.
    //디펜더즈인젝션은 표준을 정의 할 수 있고, 정의된 표준을 바탕으로 같은 설계를 하게 하여줍니다.
    //디펜더즈인젝션 장점 1.재사용성을 높여줍니다.
    //디펜더즈인젝션 장점 2.테스트에 용이하죠.
    //디펜더즈인젝션 장점 3.코드도 단순화 시켜줍니다.
    //디펜더즈인젝션 장점 4.종속적이던 코드의 수도 줄여줍니다.
    //디펜더즈인젝션 장점 5.왜 사용하는 지 파악하기가 수월합니다. 코드를 읽기 쉬워지는 점이 있습니다.
    //디펜더즈인젝션 장점 6.종속성이 감소합니다. 구성 요소의 종속성이 감소하면, 변경에 민감하지 않습니다.
    //디펜더즈인젝션 장점 7.결합도(coupling)는 낮추면서 유연성과 확장성은 향상시킬 수 있습니다.
    //디펜더즈인젝션 장점 8.객체간의 의존관계를 설정할 수 있습니다.
    //디펜더즈인젝션 장점 9.객체간의 의존관계를 없애거나 줄일 수 있습니다.
    //..

    @BeforeEach
    public void beforeEach() {
    memberRepository = new MemoryMemberRepository();
    //비포이치 테스트 순서:1.메모리멤버리포지토리를 만들고 멤버리포지토리에 넣는다.

    memberService = new MemberService(memberRepository);
    //비포이치 테스트 순서:2.멤버리포지토리를 멤버 서비스에 넣는다.

    }
    //이렇게 비포이치해주면 테스트가 각각 독립적으로 실행 된다.)
    @AfterEach
    public void AfterEach(){
        memberRepository.clearStore();
    }
//애프터이치라는 어노테이션으로 memberRepository.에서 클리어스토어 기능을 구현시켰다.
//이건 테스트케이스를 만들면 여러번 실행시키기 위해 꼭 해야하는거다.
//실행 돌릴 때 마다 저장 된 값들을 삭제시켜주는거다.
//clearStore.를 실험해보고싶으면 전에 실행했던걸 다시 실행 시켜주는 단축키 alt+shift+f10


    @Test//given,when,then 기법을 써보자.,

    void /*join*/회원가입(){

        //given:뭔가가 주어졌는데
        Member member = new Member();
        member.setName("hello");

        //when:이것을 실행 했을 때
        long saveId = memberService.join(member);

        //then:결과.
        Member findMember=memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }
    //--실행 돌려보고 오류 안나면 회원가입 성공!--


    //이제 중복회원 검증 로직 짯던것도 실험해보자
@Test
public void 중복회원예외(){

    //given
    Member member1= new Member();
    member1.setName("회원1");

    Member member2= new Member();  //shift +f6
    member2.setName("회원1");
    //똑같이 회원1로 멤버2에 저장해서 중복 회원을 만들어봄.

    //when
    memberService.join(member1);
//    try {
//     memberService.join(member2);
//     fail();
//    }catch(Exception e){
//        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//    }

    //예외 처리 확인 방법 1.트라이&캐치.
    IllegalStateException e=assertThrows(IllegalStateException.class,()->memberService.join(member2));
    assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    //예외 처리 확인 방법 2.assertThrows 장점: 메세지가 반환이 됨.
    //then
}

    @Test
    void /*fincByName*/회원찾기(){
    }

    @Test
    void /*회원 조회*/findOne(){
    }
}