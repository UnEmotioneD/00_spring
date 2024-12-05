package kr.or.iei.member.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import kr.or.iei.member.model.vo.Member;

public class MemberRowMapper implements RowMapper<Member> {

  @Override
  public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
    Member m = new Member();
    m.setMemberId(rs.getString("member_id"));
    m.setMemberPw(rs.getString("member_pw"));
    m.setMemberName(rs.getString("member_name"));
    m.setMemberPhone(rs.getString("member_phone"));
    m.setMemberGender(rs.getString("member_gender"));
    m.setMemberAge(rs.getString("member_age"));
    m.setEnrollDate(rs.getString("enroll_date"));
    return m;
  }

}
