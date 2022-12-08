package chap5.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ContentDAO {
	@Autowired
	JdbcTemplate template;
	
	public void addArticle(ContentVO contentVO) {
		String sql = "insert into article_content(article_no, content) "
				+ "values(?, ?)";
		
		try {
			template.update(sql,
					contentVO.getAid(), contentVO.getContent());
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
		}
	}
	
	public ContentVO getArticleById(int aid) {
		String sql = "select article_no, content "
				+ "from article_content where article_no = ?";
		
		try {
			return template.queryForObject(sql, (rs, rowNum) ->
						new ContentVO(rs.getInt(1), rs.getString(2)),
						new Object[] {aid});
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void updateArticle(String content, int aid) {
		String sql = "update article_content "
				+ "set content = ? "
				+ "where article_no = ?";
		
		try {
			template.update(sql, content, aid);
		} catch (Exception e) {
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
