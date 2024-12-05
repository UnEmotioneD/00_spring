package kr.or.iei;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.or.iei.member.model.vo.Member;

// Component : Spring 컨테이너에서 관리되는 특정 기능을 담당하는 객체
// - Controller : Spring MVC 에서 웹 요청을 처리하는 컨틀롤러

// Component 가 작성되서 자동으로 생성됨 그리고 ioc container 에 저장해둔다
// servlet-context.xml 에서 component-scan 이 패캐지 밑에 있는걸 전부 스캔해서 Bean 으로 생성해둔다
@Controller
public class TestController {

  public TestController() {
    System.out.println("TestController constructed");
  }

  // 서블릿 파일대신에 메소드를 이용해서 메모리 자원 절약

  // GetMapping : 클라이언트 Get 요청을 처리할 수 있는 어노테이션
  @GetMapping("/getTest.kh")
  public void getTest() {
    System.out.println("getTest 메소드 실행");
  }

  // PostMapping : 클라이언트 Post 요청을 처리할 수 있는 어노테이션
  @PostMapping("/postTest.kh")
  public void postTest() {
    System.out.println("postTest 메소드 실행");
  }

  // 기존 서블릿 방식의 요청 파라미터 처리
  @GetMapping("/servletParamTest.kh")
  public void servletParamTest(HttpServletRequest req) {
    String testId = req.getParameter("testId");
    String testPw = req.getParameter("testPw");

    System.out.println("testId : " + testId);
    System.out.println("testPw : " + testPw);
  }

  // input 태그 name 속성 값과 컨트롤러 매개변수 명칭과 일치 시킨다
  @PostMapping("/springParamTest1.kh")
  // index.jsp 에서 name 값과 매개변수 값을 일치 시켜야 한다
  public void springParamTest1(String testId, String testPw) {
    System.out.println("testId : " + testId);
    System.out.println("testPw : " + testPw);
  }

  // input 태그 name 속성 값과 컨트롤러 매개변수 클래스의 변수명과 일치시킨다
  @PostMapping("/springParamTest2.kh")
  public void springParamTest2(Member member) {
    System.out.println("memberId : " + member.getMemberId());
    System.out.println("memberPw : " + member.getMemberPw());
    System.out.println("memberName : " + member.getMemberName());
    System.out.println("memberPhone : " + member.getMemberPhone());
  }

  // 기존 서블릿 방식의 데티어 응답
  @GetMapping("/servletResponseTest.kh")
  public void servletResponseTest(HttpServletRequest req, HttpServletResponse res) {
    req.setAttribute("resTest1", "servlet responseData 1");
    req.setAttribute("resTest2", "servlet responseData 2");

    RequestDispatcher view = req.getRequestDispatcher("/WEB-INF/views/resTest/servletResponse.jsp");

    try {
      view.forward(req, res);
    } catch (ServletException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // Spring 방식의 데이터 응답 - 1
  @GetMapping("/springResponseTest1.kh")
  public String springResponseTest1(Model model) { // Model : 데이터만을 등록하고 응답할 떄 사용 되는 클래스
    model.addAttribute("resTest1", "spring responseData 1");
    model.addAttribute("resTest2", "spring responseData 2");
    // servlet-context.xml 파일 내부에
    // prefix : /WEB-INF/views/
    // suffix : .jsp
    // prefix, suffix 을 반환하는 문자열 앞뒤에 붙여 준다
    // /WEB-INF/view/ + resTest/springResponse + .jsp
    return "resTest/springResponse";
  }

  // ModelAndView : 응답 데이터와 페이지 경로를 같이 등록할 때 사용되는 클래스
  @GetMapping("/springResponseTest2.kh")
  public ModelAndView springResponseTest2() {
    ModelAndView mdv = new ModelAndView("resTest/springResponse2");
    mdv.addObject("resTest1", "spring responseDate 1");
    mdv.addObject("resTest2", "spring responseDate 2");

    return mdv;
  }

  @PostMapping("/springReqAndResTest.kh")
  public String springReqAndResTest(Member member, Model model) {
    System.out.println("memberId : " + member.getMemberId());
    System.out.println("memberPw : " + member.getMemberPw());

    // 응답 페이지에서 멤버 정보를 출력하기 위해, Model 객체에 "member" 이라는 키 값으로 등록
    model.addAttribute("member", member);

    // 반환하는 문자열 앞뒤로 prefix, suffix 가 붙어서 경로가 완성된다
    return "resTest/springReqAndRes";
  }

}
