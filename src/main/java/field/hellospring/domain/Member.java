package field.hellospring.domain;
//도메인(domain)이란 단어는 영토, 분야, 영역, 범위를 뜻하는 단어
public class Member{

    private  Long id;
    private String name;
    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id=id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
}
