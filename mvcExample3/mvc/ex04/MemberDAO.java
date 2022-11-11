package ch05.ex04;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO {
	@Autowired
	private JdbcTemplate template;
	
	public List<MemberVO> listMembers() {
		String sql = "select * from member order by id";
		List<MemberVO> list = template.query(sql,
				new BeanPropertyRowMapper<MemberVO>(MemberVO.class));
		
		return list;
	}
	
	public MemberVO getMember(int id) {
		String query = "select * from member where id = ?";
		
		return template.queryForObject(query,
				(rs, rowNum) -> new MemberVO(rs.getInt("id"), rs.getString("email")
						, rs.getString("password"), rs.getString("name"), rs.getString("regdate"))
				, new Object[] {id});
	}
	
	public int addMember(MemberVO member) {
		String query = "insert into member(name, password, email, regdate) values(?, ?, ?, ?)";
		
		return template.update(query, member.getName(), member.getPassword()
				, member.getEmail(), member.getRegdate());
	}
	
	public int updateMember(MemberVO member) {
		String query = "update member set email = ?, password = ?, name = ?, regdate = ? where id = ?";
		
		return template.update(query, member.getEmail(), member.getPassword()
				, member.getName(), member.getRegdate(), member.getId());
	}
	
	public int deleteMember(int id) {
		String query = "delete from member where id = ?";
		
		return template.update(query, id);
	}
}
