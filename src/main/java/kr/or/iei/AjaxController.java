package kr.or.iei;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import kr.or.iei.member.model.service.MemberService;
import kr.or.iei.member.model.vo.Member;

// Bean 으로 등록하기 위해서 component 를 작성해준다
@Controller
public class AjaxController {

  @GetMapping("/ajax/movePage.kh")
  public String movePage() {
    return "ajax/ajaxTest";
  }

  // 1. 기존 서블릿 방식
  @GetMapping("/ajax/ajaxTest1.kh")
  public void ajaxTest1(HttpServletResponse response, String str, int num) throws IOException {
    String resData = "Ajax 요청 시 전달 문자열 : " + str + ", 전달 숫자 : " + num;

    response.setContentType("text/html; charset=utf-8");
    response.getWriter().print(resData);
  }

  // 2. 스프링 방식
  // value : 클라이언트 요청 URL, produces : 응답 데이터 형식
  @GetMapping(value = "/ajax/ajaxTest2.kh", produces = "text/html; charset=utf-8")
  @ResponseBody
  public String ajaxTest2(String str, int num) {
    String resData = "Ajax 요청 시 전달 문자열 : " + str + ", 전달 숫자 : " + num;
    // 반환하는 String 을 페이지 url 로 인식한다
    // 응답하는 데이터라는 걸 알려야함 @ResponseBody
    // String encoding 이 안되있음 @GetMapping 을 수정
    return resData;
  }

  // 3. 기존 서블릿 방식 - 응답 데이터 다수 처리
  @GetMapping("/ajax/ajaxTest3.kh")
  public void ajaxTest3(HttpServletResponse response, String str, int num) throws IOException {
    String resData1 = "Ajax 요청 시 전달 문자열 : " + str;
    String resData2 = "Ajax 요청 시 전달 숫자 : " + num;

    response.setContentType("text/html; charset=utf-8");
    // 따로 보내도 하나의 문자열로 합쳐진다
    // response.getWriter().print(resData1);
    // response.getWriter().print(resData2);

    // Maven Repository 검색 json 검색 json.simple 에서 1.1.1 버젼
    // gson 검색 2.11.1(최신 버젼)
//		JSONArray jsonArr = new JSONArray();
//		jsonArr.add(resData1);
//		jsonArr.add(resData2);

    // ajax 에서 배열 형태로 출력된다
    // 근데 자료형은 String 이다
//		response.setContentType("text/html; charset=utf-8");
//		response.getWriter().print(jsonArr);

    JSONObject jsonObj = new JSONObject();
    jsonObj.put("res1", resData1);
    jsonObj.put("res2", resData1);

    response.setContentType("text/html; charset=utf-8");
    response.getWriter().print(jsonObj);
  }

  // 4. 스프링 방식
  // produces 를 application/json 으로 하면 object 타입으로 반환됨
  @GetMapping(value = "/ajax/ajaxTest4.kh", produces = "application/json; charset=utf-8")
  @ResponseBody
  public String ajaxTest4(String str, int num) {

    JSONObject jsonObj = new JSONObject();
    jsonObj.put("res1", str);
    jsonObj.put("res2", num);

    // JOSNObject 형식 문자열로 만들어 준다
    return jsonObj.toJSONString();
  }

  @GetMapping(value = "/ajax/ajaxTest5.kh", produces = "application/json; charset=utf-8")
  @ResponseBody
  public String ajaxTest5() {
    Member member = new Member("daniel", "1234", "김찬희", "010-0000-0000", "25", "M", "24/12/03");

    // 변경사항이 있을때 마다 바꿔줘야 할께 많음
//		JSONObject jsonObj = new JSONObject();
//		jsonObj.put("memberId", member.getMemberId());
//		jsonObj.put("memberPw", member.getMemberPw());
//		jsonObj.put("memberName", member.getMemberName());
//		jsonObj.put("memberPhone", member.getMemberPhone());
//		jsonObj.put("memberAge", member.getMemberAge());
//		jsonObj.put("memberGender", member.getMemberGender());
//		jsonObj.put("enrollDate", member.getEnrollDate());
//		return jsonObj.toJSONString();

    // Gson 을 쓰면 자동으로 해준다
    return new Gson().toJson(member);
  }

  @Autowired
  @Qualifier("memberService")
  private MemberService memberService;

  @GetMapping(value = "/ajax/ajaxTest6.kh", produces = "application/json; charset=utf-8")
  @ResponseBody
  public String ajaxTest6() {
    ArrayList<Member> list = memberService.allMemberList();

    return new Gson().toJson(list);
  }

}
