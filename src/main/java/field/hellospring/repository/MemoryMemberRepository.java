package field.hellospring.repository;

import field.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;


public class MemoryMemberRepository implements MemberRepository{
    private static final Map<Long,Member> store=new HashMap<>();
    private static long sequence=0L;
    @Override
    public Member save(Member member){
        member.setId(++sequence);
        store.put(member.getId(),member);
        return member;
    }//회원 저장 및 조회
    @Override
    public Optional<Member> findById(Long id){
        return Optional.ofNullable(store.get(id));
    }//ID로 조회
    @Override
    public Optional<Member> findByName(String name){
        return store.values().stream().filter(member->member.getName().equals(name)).findAny();//Name 으로 조회
    }
    @Override
    public List<Member> findAll(){
        return new ArrayList<>(store.values());
    }//전체 회원 조회
    public void clearStore(){
        store.clear();
    }//테스트용 코드. 용도는 갱신.
}

