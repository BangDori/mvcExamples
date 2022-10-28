package product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProductDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	String jdbc_driver = "com.mysql.cj.jdbc.Driver";
	String jdbc_url = "jdbc:mysql://localhost/spring4fs";
	
	void connect() {
		try {
			Class.forName(jdbc_driver);
			conn = DriverManager.getConnection(jdbc_url, "spring4", "spring4");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	void disconnect() {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (Exception e) {
				System.out.println(e);
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
	
	public ArrayList<ProductVO> getProductList() {
		connect();
		
		ArrayList<ProductVO> list = new ArrayList<>();
		String sql = "select * from product order by id;";
		
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ProductVO productVO = new ProductVO();
				
				productVO.setId(rs.getInt("id"));
				productVO.setName(rs.getString("name"));
				productVO.setModel(rs.getString("model"));
				productVO.setMadein(rs.getString("madein"));
				productVO.setPrice(rs.getInt("price"));
				productVO.setReadcnt(rs.getInt("readcnt"));
				
				list.add(productVO);
			}
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		} finally { disconnect(); }
		
		return list;
	}
	
	public void updateReadCnt(int id, int readCnt) {
		connect();
		
		String sql = "update product set readcnt = ? where id = ?;";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (readCnt+1));
			pstmt.setInt(2, id);
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally { disconnect(); }
	}
	
	public ProductVO getProductById(int id) {
		connect();
		
		ProductVO productVO = new ProductVO();
		String sql = "select * from product where id = ?;";
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				productVO.setId(rs.getInt("id"));
				productVO.setName(rs.getString("name"));
				productVO.setModel(rs.getString("model"));
				productVO.setMadein(rs.getString("madein"));
				productVO.setPrice(rs.getInt("price"));
				productVO.setReadcnt(rs.getInt("readcnt"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally { disconnect(); }
		
		return productVO;
	}

}
