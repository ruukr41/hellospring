package field.hellospring.repository;

import field.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    //HashMap은 Map 인터페이스를 구현한 대표적인 Map 컬렉션입니다. Map 인터페이스를 상속하고 있기에 Map의 성질을 그대로 가지고 있습니다. Map은 키와 값으로 구성된
    // Entry객체를 저장하는 구조를 가지고 있는 자료구조입니다. 여기서 키와 값은 모두 객체입니다. 값은 중복 저장될 수 있지만 키는 중복 저장될 수 없습니다. 만약 기존에 저장된 키와 동일한 키로 값을 저장하면 기존의 값은 없어지고 새로운 값으로 대치됩니다. HashMap은 이름 그대로 해싱(Hashing)을 사용하기 때문에 많은 양의 데이터를 검색하는 데 있어서뛰어난 성능을 보입니다.

    private static long sequence = 0l;

//일단 클래스로 메모리멤버리포지터리를 만들었고 impliments(구현한다)로 멤버리포지터리라는 추상메서드에 구현하겠다고 선언을 하니
//빨간 줄 뜨고 alt+enter 누르니까 Override(상위 클래스에서 정의된 변수와 메소드의 내용을,
//     - 하위 클래스에서 변경하여 재정의하는 것) 된 기능들이 생겼다.이건 인터페이스 멤버리포지터리 클래스에서 가져 온 것이다.
//이후 private static Map<Long, Member>|| Map에 저장을하겠다는거다. Map<Long, Member> ||Long을 key로 지정한다. id를 Long으로 했으니까.
//값은 멤버. 그리고 arlt enter로 임포트 해주고, store에 담겠다고 한다.
//member를 save할 때 셋아이디 하면서 시퀀스 값을 올려주고.
//이러면 스토어에 넣기 전에 멤버에 아이디값을 세팅해주고,아이디 값 세팅 전에 이름은 일단 넘어온 상태.
//멤버는 회원가입 할 때 적는 이름이다. 아이디는 시스템에 저장 할 때 시스템이 정해서 등록시켜주는 것.


    @Override
    public Member save(Member member){


//long sequence (: 순차적으로 정수값을 자동으로 생성하는 객체)  ==  시퀀스는 ex>0.1.2 이런 키값을 생성해주는애다.
        member.setId(++sequence); //아이디 세팅
        store.put(member.getId(), member); //스토어에 저장. 그럼 먑에 저장이 됨.
        return member; //스펙에 따라서 맵에 멤버가 반환 됨
    }


    @Override
    public Optional<Member> findById(Long id){
        return Optional.ofNullable(store.get(id));
        //findById 스토어에서 아이디를 꺼내보자.
        //store.get(id);로 꺼내보자. 근데 get했는데 Null을 가져오면 안되니까 optional로 감싸자 -> optional.ofNullable


    }


    @Override
    public Optional<Member> findByName(String name){
        return store.values().stream().filter(member -> member.getName().equals(name))
                .findAny();

        // findbyname은 어케 하느냐: 스토어에서 필터 끼우고 루프 돌릴거다.
        //루프 돌리다가 member.getname이 파라미터로 넘어온 네임과 같은지 확인하는거임.
        //같은 경우에만 필터링이 됨, 그렇게 맴에서 하나라도 찾게 되면. findany:뭐든 반환하겠다.
        //근데 끝까지 돌렸는데 없으면 Optional로 감싸진 Null이 반환 될것이다.
    }

    @Override
    public List<Member> findAll(){
        return new ArrayList<>(store.values());

    //findAll 알아보자: 실무에선 자바로  list를 많이 쓰는데, 루프 돌리기도 편하고 해서....

    //new로 어레이리스트 하나 만들어서 Map에 store.values를 반환해주겠다.(멥에 멤버스=벨류스)
    }

    public void clearStore() {
        store.clear();
    }
}




//구현 설정 끝! 이제 동작해 봐야지!검증 해봐야지!? 이제 테스트 케이스 만들어보자.

//<h1>회원 리포지토리 테스트케이스</h1>

//개발한 기능을 실행해서 테스트 할 때 보통 main 메서드를 통해서 실행하거나,
//웹 어플의 컨트롤러를 통해서 해당 기능을 실행하거나....근데 이러한 방법의 치명적인 단점 세가지가 있다.
// 1.준비하고 실행하는데 너무 오래걸림
// 2.반복 실행하기 어려움
// 3.여러 테스트를 한번에 실행하기 어려움.
// 이걸 해결하는게 JUniy이라는프레임워크로 테스트코드를 만들어서 실행한다.말그대로 코드를 검증하는 코드를 만드는거다.
