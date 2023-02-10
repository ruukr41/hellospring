package field.hellospring.Controller;

import field.hellospring.domain.Member;
import field.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController{
    private MemberService memberService;

    @Autowired // 구동시 @service로 구동된 MemberService를 가져와 넣어준다
    public MemberController(MemberService memerService){
        this.memberService=memerService;
    }
    @GetMapping("/members/new") // url 접근시 members/createMemberForm 페이지
    public String createForm(){
        return "members/createMemberForm";
    }
    @PostMapping("/members/new")//포스트매핑
    public String create(MemberForm form){//멤버폼에서 폼이라는 객체에받겠다.
                Member member = new Member();//member 객체 생성
                member.setName(form.getName());//멤버객체 내에서 Name 받고 폼에서 받은 겟으로 name 꺼낸다.
                //위에 멤버폼과 컨트롤러가 네임을 받고 주고 다 하면 밑에 회원가입 메서드 실행
                memberService.join(member);//멤버서비스에서 조인하면 멤버에 저장하겠다는거.
                return "redirect:/";//회원 가입 다 하면 다시 홈 화면으로 띄우는거.
    }
    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);//매개변수로 전달받은 model.addAttribute("key", "value"); 메소드를 이용해서 view에 전달할 데이터를 key, value 쌍으로 전달함.
        return "members/memberList";

    }

}