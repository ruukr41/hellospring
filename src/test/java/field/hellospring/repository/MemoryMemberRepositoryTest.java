package field.hellospring.repository;

import field.hellospring.domain.Member;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest{
    @AfterEach
    public void afterEach(){
        repository.clearStore();
        //위에 메모리멤버 리포지토리 클래스에 public void clearStore(){store.clear();}
        //라는 clearStore라는 메서드를 만들고,store.clear라는 기능을 넣었다.
//        그리고 여기에 애프터이치라는 어노테이션으로 감싸고 리포지터리에서 클리어스토어 기능을 구현시켰다.
//        이건 테스트케이스를 만들면 여러번 실행시키기 위해 꼭 해야하는거다.
//실행 돌릴 때 마다 저장 된 값들을 삭제시켜주는거다.
//clearStore.를 실험해보고싶으면 전에 실행했던걸 다시 실행 시켜주는 단축키 alt+shift+f10
    }

MemoryMemberRepository repository = new MemoryMemberRepository();
//일단 memoryMemberRepository 를 담을 객체(repository)를 만들어준다.
@Test //(세이브 된 멤버값으로 찾기 )테스트
public void save(){
Member member = new Member();
member.setName("spring");

repository.save(member);
Member result = repository.findById(member.getId()).get();

// save()메서드를 써볼거다.
//@Test 를 넣어서 실험해보자.  org.jupiter.api.tes t가 임포트 되고, save()를 실행 할 수 있게 됐다.
//이제 public void save()를 실행해보자. 녹색불 뜨고 실행이 됐다.
//이제 저장이 되는지를 봐보자. Member member = new member()로 member 오브젝트를 만들어준다.
//repository.save(member); <<리포지토리에다가 세이브 멤버를 해주겠다 뜻.
//repository.findById((member.getId()); <<리포지토리에 세이버 된 멤버의 ID를 find하겠다.
// 그 다음 Member result =*****************.get();으로 꺼내겠다.

assertThat(member).isEqualTo(result);
//    Assertions.assertEquals(member, result);
//Assertions jupiter api 임포트. import org.junit.jupiter.api.Assertions;

//(Assertions:표명).(assertEquals:같음을 주장하다)(멤버,결과) <멤버와 결과가 같다는걸 주장,표명하겠다.
// (기댓값, 결과값); 두 값이 동일하면 참, 다르면 false


}

@Test //이름으로 찾기 테스트
public void findByName(){
Member member1 = new Member();
//클래스(자료형)[member] 객체변수(참조값저장,인스턴스 핸들)[member1] = 메모리(Headp)할당,인스턴스 생성,참조값 리턴(->객체)[new]   클래스 생성자 호출[member();]
member1.setName("spring1");//member1에 setName()  spring1 넣어주고
repository.save(member1);//repository에 스프링1을 save한다.


//shift +F6누르면 커서에 있는 키워드와 같은 키워드들이 다중선택 됨. 복붙,이름바꾸기 꿀팁.
Member member2 = new Member();
member2.setName("spring2");//member2에 setName() spring2 넣어주고
repository.save(member2);//repository에 스프링2을 save한다.

//스프링1과 스프링2가 가입이 됐다는 전제이다..

Member result = repository.findByName("spring1").get();
//멤버를 result로 반환 받겠다. = repository에서 spring1이라는 name을 find하겠다.
//그리고 get();으로 꺼내겠다 뜻.
assertThat(result).isEqualTo(member1);
//주장한다.결과를. 이건 같다 memeber1과.

//위에 멤버2에는 스프링1 말고 2를 저장했었으니까 스프링 2를 찾기 위해선
//isEquarTo에서 member2로 바꿔야 한다.
//repository에 멤버1에는 스프링1이 저장 됐었으니까, 멤버 2에선 스프링1은 없다. 스프링2는 있다.
}

@Test
public void findAll(){
    Member member1 = new Member();
    //클래스(자료형)[member] 객체변수(참조값저장,인스턴스 핸들)[member1] = 메모리(Headp)할당,인스턴스 생성,참조값 리턴(->객체)[new]   클래스 생성자 호출[member();]
    member1.setName("spring3");
    //참조값이 저장 될 인스턴스 핸들 member1에다가 spring3이라는 Name을 받겠다.는 뜻.
    repository.save(member1);
    //repository에 참조값을 저장할 객체변수(memver1)를 넣겠다.
    Member member2 = new Member();
    //클래스,객체변수 = 힙생성 생성자(); 호출
    member2.setName("spring4");
    //멤버2에 spring4라는 Name을 Set.받을거야
    repository.save(member2);
    //repository에.멤버2 저장할거야
    List<Member> result = repository.findAll();
    //리포지토리에서. findAll돌렸는데 거기서 뭐나옴? List로 감싼 멤버 의 반환값이랑 같음?리스트로 감쌋으니까 몇개 나왔는지 알려줘.
    assertThat(result.size()).isEqualTo(2);
    //주장한다. 뭘?That을. Taht->반환값.의 size를. 그 사이즈 is 같다.(2) 와;
    //위에 멤버 1이랑 2를 리포지토리에 넣었으니까 2개가 맞음.

}

}

//검증 방법 1 (비추천) 로그에 프린트하기.
// System.out.println("result ="+(result==member));

// System.out.println("result ="+(result==member));//프린트 하겠다. ("결과 =")+ 결과가 멤버와 같은 '참'일 경우에.



//검증 방법 2 (추천) 주피터 라이브러리에 있는 Assertion
// * import org.junit.jupiter.api.Assertions;

//Assertions.assertEquals(result,member)기능을 활용해보자.
// 이제 검증하는 방법으로서     System.out.println("result ="+(result==member)); 를 써서 (result:결과)로 검증 할 수 있다.
// member 나 result 대신 다른 값  null같은거 넣어보면 검증이 안된다는 걸 알 수 있다.

//검증 방법 3 (대세) 코어 api 라이브러리에있는 Assertion
// * import org.assertj.core.api.Assertions;



//   Assertions.assertThat(member).isEqualTo(result);
//  2번 방법과 다른거 없어보이긴 하는데. 읽히는 그대로이다.
// 표명하다. assert. that member is equal to result  주장한다. 기댓값인 멤버의 값과 결과값이 같다는걸.
//2번과 같은 방법으로 검증하며 같은 결과를 만든다.




//arlt enter 로 스테틱 임포트 해주면 import static org.assertj.core.api.Assertions.*; 이런 스태틱 임포트가 생긴다 그다음 위에 임포트 코어 삭제해주면 오류 해결.


//실무에선 빌드툴과 엮어서 빌드할 때 오류 테스트 케이스애서 통과 하지 않으면 다음 단계로 못넘어가게 막아버리게 한다.
//TDD:테스트 주도 개발이다. 여기서 한건 그냥 테스트케이스 만들어서 돌려본거고
//뭔가 만들기 전에 먼저 테스트코딩 먼저 다 해놓은다음 다 돌려 보고 만들기 시작하는 것.
//이게 TDD다.
//테스트코드가 없이 개발을 하는건. 나 혼자 개발하면 크게 상관 없는데
//여러명이서 개발 할땐 소스코드가 막 몇만 라인 넘어가면 골치아파진다.
//테스트 관련한 공부는 꼭 깊이 있게 공부해야한다.!
