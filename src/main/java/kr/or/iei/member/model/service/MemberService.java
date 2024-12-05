package kr.or.iei.member.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import kr.or.iei.member.model.dao.MemberDao;
import kr.or.iei.member.model.vo.Member;

// id 값을 지정해주는 느낌
@Service("memberService")
public class MemberService {

  @Autowired
  @Qualifier("memberDao") // @Service("memberDao") 로 지정한 식별자를 가진 객체를 주입받기 위함
  private MemberDao memberDao;

  public MemberService() {
    System.out.println("MemberService Constructed!");
  }

  public Member memberLogin(Member member) {
    return memberDao.memberLogin(member);
  }

  public int join(Member member) {
    return memberDao.join(member);
  }

  public int delete(String memberId) {
    return memberDao.delete(memberId);
  }

  public ArrayList<Member> allMemberList() {
    return (ArrayList<Member>) memberDao.allMemberList();
  }

  public int idDuplicationCheck(String memberId) {
    return memberDao.idDuplicationCheck(memberId);
  }
}
