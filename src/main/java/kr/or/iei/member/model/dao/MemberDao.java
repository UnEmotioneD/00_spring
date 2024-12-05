package kr.or.iei.member.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.iei.member.model.vo.Member;

@Repository("memberDao")
public class MemberDao {

  // application.xml 에서 JdbcTemplate 를 IoC에서 Bean 으로 등록해 놨다
  @Autowired
  // 식별자 역할을 해준다
  @Qualifier("jdbcTemplate")
  private JdbcTemplate jdbcTemplate;

  public MemberDao() {
    System.out.println("MemberDao constructed!");
  }

  public Member memberLogin(Member member) {
    // 1. 쿼리 작성
    String query = "select * from tbl_member where member_id = ? and member_pw = ?";

    // 2. 파라미터 작성
    Object[] paramArr = {member.getMemberId(), member.getMemberPw()};

    // 3. SQL 수행
    // MemberRowMapper : DB 조회 결과를 JAVA 객체로 변환하는 역할을 담당
    Member loginMember = jdbcTemplate.queryForObject(query, paramArr, new MemberRowMapper());

    // 4. 결과 반환
    return loginMember;
  }

  public int join(Member member) {
    String query = "insert into tbl_member values (?, ?, ?, ?, ?, ?, sysdate)";

    Object[] paramArr = {member.getMemberId(), member.getMemberPw(), member.getMemberName(),
        member.getMemberPhone(), member.getMemberAge(), member.getMemberGender()};

    // insert, update, delete 는 모두 update() 메소드 사용
    return jdbcTemplate.update(query, paramArr);
  }

  public int delete(String memberId) {
    String query = "delete from tbl_member where member_id = ?";

    Object[] paramArr = {memberId};

    return jdbcTemplate.update(query, paramArr);
  }

  public List<Member> allMemberList() {
    String query = "select * from tbl_member";

    return jdbcTemplate.query(query, new MemberRowMapper());
  }

  public int idDuplicationCheck(String memberId) {
    String query = "select count(*) from tbl_member where member_id = ?";

    Object[] paramArr = {memberId};

    return jdbcTemplate.queryForObject(query, paramArr, Integer.class);
  }
}
