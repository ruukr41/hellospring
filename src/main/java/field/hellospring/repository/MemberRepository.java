package field.hellospring.repository;

import field.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository{
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();


}
//멤버리포지토리에  인터페이스 해서
//멤버 객체를 만들고 save 기능으로 멤버를 저장 하면 멤버를 반환하는 기능을 쓸거다.

//그리고 리포지토리에 4가지 기능을 만드었다.
//save 하면 회원 리퍼지토리에 회원이 저장 되는것.
//ㄱ그 다음부터는 이 저장소에서 finbyId나 findBuname으로 찾아올 수 있다.
//findAll해서 저장 된 모든 회원리스트를 반환 할 수 있다.

//추상 메서드(interface:클래스들이 구현해야 하는 동작을 지정하는데 사용되는 추상 자료형)를 만들었으니 이제 구현체를 만들어보자
// optional은 findBy~~해서 나온 값이 null일 경우 optianl로 감싸서 반환 하는 기능이라고 한다.



