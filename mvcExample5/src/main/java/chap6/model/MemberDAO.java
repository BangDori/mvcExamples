package chap6.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDAO {
	
	List<MemberVO> listMembers();
	MemberVO getMember(int id);
	MemberVO getMemberByEmail(String email);
	int addMember(MemberVO member);
	int updateMember(MemberVO member);
	int deleteMember(int id);
}