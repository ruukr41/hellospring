package field.hellospring.service;

import field.hellospring.domain.Member;
import field.hellospring.repository.MemberRepository;
import field.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService{


    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        this.memberRepository=memberRepository;
    }

    /**
     * 회원가입
     */

    public long join(Member member){
        //같은 이름의 중복 회원 안됨X
        memberRepository.findByName(member.getName())
        .ifPresent(m->{
            throw new IllegalStateException("이미 존재하는 회원입니다.");});
                                //멤버리포지토리에서 파인드바이 네임 쓸건데 멤버객체중 겟네임으로 나온 네임들 중 조사할거다.
        //*개꿀팁*  Ctrl+Alt+v하면 옵셔널로 "=" 이전. 그러니까 위에 Optional<member> result를 알아서 적어줌.
//        Member member1=result.get();
        //요렇게 직접 걍 꺼낼 수 있지만 ifPresent 로 꺼내보자.


        //if present(){}는.그러니까 리졀트 값이 나오면(m 으로 지정.),throw new illegalStateException() 해준다는 뜻.
        //이 기능은 (member)를 Optional<>로 감싸줘야 쓸 수 있다.
        //과거엔 Null 에 대비책으로 if 문을 썻지만 요즘은 [orElseGet();:겟으로 뭐 나왔는데 '널'이면 다른거 result 해라 등 ]을쓰거나 그냥 optional<>로 감싸서 반환을 해주고 그 감싼 덕에 result.ifPresent(){}를 쓸 수 있다.


        //그리고 ifPresent 를 쓴다면 Optional로 감싸지 않고. 아래 처럼 코드를 짤 수도 있다. 더 간결하고 직관적으로 보인다.
        validateDuplicateMember(member);//중복회원 검증!
        //findByName()해서 로직이 쭉 나왔다. 이런 경우에는 메서드로 뽑는게 좋다. 커서로 find 로직 전체 잡고 잡고 단축키 ctrl+Alt+shift+T 누르면 댐.
        //그리고 메서드 추출[Extract method] 하면 Extracted(member,result); 라는 개멋있는 메서드가 밑에 하나 생긴다.
        //그리고 그 메서드와 위에 Extracted()를 validateDuplicateMember(){}로 메서드명을 바꿔준다.
        memberRepository.save(member);//중복회원 검증 통과하면 저장한다!
        //memberRepository.에 save()하겠다 (member)를
        return member.getId();
        //멤버에서 아이디값을 리턴값으로 가져오겠다.
    }

    private void validateDuplicateMember(Member member){
        memberRepository.findByName(member.getName())
                .ifPresent(m->{
        throw new IllegalStateException("이미 존재하는 회원입니다.");
    });
    }

    /**
     * 전체 회원 조회!
     * /회원가입등의 서비스부분은 비지니스용어로 명명하는게 좋다. 그래야 개발자든 기획자든 알아보기 좋다.
     * \repository 같은 기능 들은 비지니스적인용어보단 개발스럽게 명명한다.
     */




    //리스트와 관련된 비지니스 로직은 엄청 쉽다.findAll 과 findOne 을 쓰게 되는데
    //기능 자체가 단순하기 때문에 코드도 간결하고 단순하다.
        public List<Member> findMembers(){
        return memberRepository.findAll();

        }

        public Optional<Member> findOne(Long memberId) {
            return memberRepository.findById(memberId);
        }









}
