package kr.or.iei.member.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.iei.member.model.service.MemberService;
import kr.or.iei.member.model.vo.Member;

@Controller // 이 컴포넌트를 작성하면 서버 구동 시 자동으로 객체를 생성하여 IoC 컨테이너에 등록함

//공통 url 을 적용할 수 있음
// 현제 컨트롤러 내부에 작성된 메소드로 요청시 모두 시작 url 이 동일함으로 공통 url 설정
@RequestMapping("/member/")
public class MemberController {

  // IoC 컨테이너에 등록된 객체(Bean) 중에 일치하는 타입을 자동으로 주입한다
  @Autowired
  @Qualifier("memberService") // IoC 컨테이너에 등록된 객체(Bean) 중에 명칭이 동일한 객체(Bean)을 등록함
  private MemberService memberService;

  public MemberController() {
    System.out.println("MemberController Constructed!");
  }

  // "/member/login.kh" 에서 @RequestMapping 을 설정하고 나서는 "login.kh" 로 할 수 있다
  @PostMapping("login.kh")
  public String memberLogin(Member member, HttpSession session) {
    // 1. 인코딩 : - web.xml 에서 인코딩 클래스를 제공
    // 2. 값추출 : 매개변수로 전달
    // 3. 비즈니스 로직 :

    // Controller 컴포넌트를 통해 자동으로 생성되어 IoC 컨테이너 등록된 객체를
    // @Autowired 가 작성된 변수에 주입 받았으므로 객체의 주소값이 출력된다
    // System.out.println(memberService);
    Member loginMember = memberService.memberLogin(member);

    // 4. 결과 처리
    if (loginMember != null) {
      session.setAttribute("loginMember", loginMember);
      return "member/loginSuccess";
    } else {
      return "member/loginFail";
    }
  }

  @GetMapping("joinFrm.kh")
  public String joinFrm() {
    // prefix : /WEB-INF/views/
    // suffix : .jsp
    return "member/join";
  }

  @PostMapping("join.kh")
  public String join(Member member) {
    int result = memberService.join(member);

    if (result > 0) {
      return "member/joinSuccess";
    } else {
      return "member/joinFail";
    }
  }

  @GetMapping("logout.kh")
  // 이미 생성되어있는 세션을 주거나 없으면 만들어줌
  public String logout(HttpSession session) {
    session.invalidate();
    // 서블릿 프로젝트에서 response.sendRedirect 와 같은 역할
    return "redirect:/";
  }

  @GetMapping("mypage.kh")
  public String mypage() {
    return "member/mypage";
  }

  @GetMapping("delete.kh")
  // url 에서 ?memberId=${} 으로 보내줬기 때문에 String 으로 받음
  public String delete(String memberId) {
    int result = memberService.delete(memberId);

    if (result > 0) {
      return "redirect:/member/logout.kh";
    } else {
      return "member/deleteFail";
    }
  }

  @GetMapping("allMemberList")
  public String allMemberList(Model model) { // 데이터를 등록하고 응답할 때 사용되는 객체
    ArrayList<Member> list = memberService.allMemberList();
    // memberList 라는 key 로 list 를 등록
    model.addAttribute("memberList", list);

    return "member/allMember";
  }

  @GetMapping("idDuplicationCheck.kh")
  @ResponseBody
  public String idDuplicationCheck(String memberId) {
    int cnt = memberService.idDuplicationCheck(memberId);

    return String.valueOf(cnt);
  }

}
