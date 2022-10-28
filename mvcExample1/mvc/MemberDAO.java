package com.yu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MemberDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	private String jdbc_driver = "com.mysql.cj.jdbc.Driver";
	private String jdbc_url = "jdbc:mysql://localhost/spring4fs";
	
	public void connect() {
		try {
			Class.forName(jdbc_driver);
			conn = DriverManager.getConnection(jdbc_url, "spring4", "spring4");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void disconnect() {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public ArrayList<MemberVO> getMembers() {
		connect();
		
		ArrayList<MemberVO> memberList = new ArrayList<>();
		String sql = "select * from member order by id";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				MemberVO memberVO = new MemberVO();
				memberVO.setId(rs.getInt("id"));
				memberVO.setName(rs.getString("name"));
				memberVO.setPassword(rs.getString("password"));
				memberVO.setEmail(rs.getString("email"));
				memberVO.setRegdate(rs.getString("regdate"));
				
				memberList.add(memberVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally { disconnect(); }
		
		return memberList;
	}
	
	public void insertMember(MemberVO memberVO) {
		connect();
		
		String sql = "insert into member(name, password, email, regdate) values(?, ?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberVO.getName());
			pstmt.setString(2, memberVO.getPassword());
			pstmt.setString(3, memberVO.getEmail());
			pstmt.setString(4, memberVO.getRegdate());
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally { disconnect(); }
	}
	
	public MemberVO getMember(int id) {
		connect();
		
		MemberVO memberVO = new MemberVO();
		String sql = "select * from member where id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				memberVO.setId(rs.getInt("id"));
				memberVO.setName(rs.getString("name"));
				memberVO.setPassword(rs.getString("password"));
				memberVO.setEmail(rs.getString("email"));
				memberVO.setRegdate(rs.getString("regdate"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally { disconnect(); }
		
		return memberVO;
	}
	
	public void updateMember(MemberVO memberVO) {
		connect();
		
		String sql = "update member set name = ?, password = ?, email = ?, regdate = ? where id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberVO.getName());
			pstmt.setString(2, memberVO.getPassword());
			pstmt.setString(3, memberVO.getEmail());
			pstmt.setString(4, memberVO.getRegdate());
			pstmt.setInt(5, memberVO.getId());
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally { disconnect(); }
	}
	
	public void deleteMember(int id) {
		connect();
		
		String sql = "delete from member where id = ?";
		
		try {
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setInt(1, id);
			 
			 pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally { disconnect(); }
	}
}
