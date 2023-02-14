package field.hellospring.repository;

import field.hellospring.domain.Member;

import java.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class jdbcMemberRepository implements MemberRepository{

    private final Datesource dateSource;

    @Override
    public Member save(Member member){

        return null;
    }

    @Override
    public Optional<Member> findById(Long id){
        return Optional.empty();
    }

    @Override
    public Optional<Member> findByName(String name){
        return Optional.empty();
    }

    @Override
    public List<Member> findAll(){
        return null;
    }
}

