package chap5.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ArticleDAO {
	@Autowired
	JdbcTemplate template;
	
	public List<ArticleVO> getAllArticles() {
		String sql = "select article_no, writer, name, title, a.regdate, moddate, read_cnt "
				+ "from article a, member m where a.writer = m.id";
		
		List<ArticleVO> articles = template.query(sql, (rs, rowNum) -> 
					new ArticleVO(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),
							rs.getString(5), rs.getString(6), rs.getInt(7)));
		
		return articles;
	}
	
	public void addArticle(ArticleVO articleVO) {
		String sql = "insert into article(writer, title, regdate, read_cnt) "
				+ "values(?, ?, ?, ?)";
		
		try {
			template.update(sql,
					articleVO.getWriter(), articleVO.getTitle(),
					articleVO.getRegdate(), articleVO.getReadcnt());
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
		}
	}
	
	public int getArticleNo(String title) {
		String sql = "select article_no from article where title = ?";
		
		try {
			return template.queryForObject(sql, Integer.class, title);		
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public ArticleVO getArticleById(int aid) {
		String sql = "select article_no, writer, name, title, a.regdate, moddate, read_cnt "
				+ "from article a, member m "
				+ "where a.writer = m.id and article_no = ?";
		
		try {
			return template.queryForObject(sql, (rs, rowNum) ->
						new ArticleVO(rs.getInt(1), rs.getInt(2),
								rs.getString(3), rs.getString(4),
								rs.getString(5), rs.getString(6),
								rs.getInt(7)), new Object[] {aid});
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void updateReadcnt(int aid) {
		String sql = "update article "
				+ "set read_cnt = read_cnt + 1 "
				+ "where article_no = ?";
		try {
			template.update(sql, aid);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateArticle(String title, int aid, String moddate) {
		String sql = "update article "
				+ "set title = ?, moddate = ?"
				+ "where article_no = ?";
		
		try {
			template.update(sql, title, moddate, aid);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteArticle(int aid) {
		String sql = "delete from article "
				+ "where article_no = ?";
		
		try {
			template.update(sql, aid);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
