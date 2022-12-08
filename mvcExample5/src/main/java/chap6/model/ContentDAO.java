package chap6.model;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ContentDAO {
	
	public void addArticle(ContentVO contentVO);
	ContentVO getArticleById(int aid);
	void updateArticle(Map map);
	void deleteArticle(int aid);
}
